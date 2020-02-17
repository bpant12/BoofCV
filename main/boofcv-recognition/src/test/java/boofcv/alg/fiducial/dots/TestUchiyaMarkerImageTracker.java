/*
 * Copyright (c) 2011-2020, Peter Abeles. All Rights Reserved.
 *
 * This file is part of BoofCV (http://boofcv.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package boofcv.alg.fiducial.dots;

import boofcv.abst.distort.FDistort;
import boofcv.alg.distort.impl.DistortSupport;
import boofcv.alg.shapes.ellipse.BinaryEllipseDetectorPixel;
import boofcv.factory.filter.binary.FactoryThresholdBinary;
import boofcv.struct.image.GrayU8;
import boofcv.testing.BoofTesting;
import georegression.struct.ConvertFloatType;
import georegression.struct.affine.Affine2D_F32;
import georegression.struct.affine.Affine2D_F64;
import georegression.struct.point.Point2D_F64;
import georegression.transform.affine.AffinePointOps_F64;
import org.ddogleg.struct.FastQueue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Peter Abeles
 */
class TestUchiyaMarkerImageTracker {

	Random rand = BoofTesting.createRandom(0);

	List<List<Point2D_F64>> documents = new ArrayList<>();

	public TestUchiyaMarkerImageTracker() {
		for (int i = 0; i < 20; i++) {
			documents.add( UchiyaMarkerGeneratorImage.createRandomMarker(rand,20,90,15));
		}
	}

	@Test
	void rotating() {
		int targetID = 3;

		var generator = new UchiyaMarkerGeneratorImage();
		generator.setRadius(5);
		generator.configure(200,200,50);
		generator.render(documents.get(targetID));

		List<Point2D_F64> centers = generator.getDotsAdjusted().toList();

		var pattern = generator.getImage();
		var rotated = pattern.createSameShape();

		// Create and configure the tracker
		var inputToBinary = FactoryThresholdBinary.globalOtsu(0,255,1.0,true, GrayU8.class);
		var ellipseDetector = new BinaryEllipseDetectorPixel();
		var tracker = TestUchiyaMarkerTracker.createTracker();
		for( var doc : documents ) {
			tracker.llahOps.createDocument(doc);
		}
		var trackerImage = new UchiyaMarkerImageTracker<>(inputToBinary,ellipseDetector,tracker);

		// Test it on rotated images
		for (int trial = 0; trial <= 4; trial++) {
			double angle = 0.2*trial;
			new FDistort(pattern,rotated).rotate(angle).apply();

			trackerImage.detect(rotated);

			// There should be one detected dot for each true dot. They should also be close to each other
			List<Point2D_F64> foundCenters = trackerImage.getFoundDots();
			assertEquals(centers.size(),foundCenters.size());
			compareDots(centers, foundCenters, angle, rotated.width, rotated.height);

			// Sanity check. This is an easy situation
			assertEquals(20, tracker.ransac.getMatchSet().size());

			FastQueue<UchiyaMarkerTracker.Track> found =  trackerImage.getTracks();

			assertEquals(1,found.size);

			UchiyaMarkerTracker.Track track = found.get(0);
			assertEquals(targetID,track.globalDoc.documentID);

			// Check extracted points to see if they are at the expected location
			compareDots(centers, track.predicted.toList(), angle, rotated.width, rotated.height);

//			ShowImages.showBlocking(rotated,"Rotating", 1_000);
		}
	}

	private List<Point2D_F64> rotate( List<Point2D_F64> src , double yaw , int width , int height) {
		Affine2D_F32 affine32 = DistortSupport.rotateCenterAffine(width/2,height/2,width/2,height/2, (float)yaw);
		Affine2D_F64 affine = ConvertFloatType.convert(affine32,null);

		var dst = new ArrayList<Point2D_F64>();
		for (int i = 0; i < src.size(); i++) {
			Point2D_F64 s = src.get(i);
			Point2D_F64 d = new Point2D_F64();
			AffinePointOps_F64.transform(affine, s.x, s.y, d);
			dst.add(d);
		}
		Collections.shuffle(dst);
		return dst;
	}

	private void compareDots(List<Point2D_F64> expected, List<Point2D_F64> found,
							 double angle , int width , int height )
	{
		Affine2D_F32 affine32 = DistortSupport.rotateCenterAffine(width/2,height/2,width/2,height/2, (float)angle);
		Affine2D_F64 affine = ConvertFloatType.convert(affine32,null);

		Point2D_F64 adj = new Point2D_F64();
		for (int i = 0; i < expected.size(); i++) {
			Point2D_F64 e = expected.get(i);
			double bestDistance = Double.MAX_VALUE;
			for (int j = 0; j < found.size(); j++) {
				Point2D_F64 f = found.get(j);
				AffinePointOps_F64.transform(affine,f.x,f.y,adj);
				double d = e.distance(adj);
				if( d < bestDistance ) {
					bestDistance = d;
				}
			}
			assertEquals(0,bestDistance,1.5);
		}
	}
}