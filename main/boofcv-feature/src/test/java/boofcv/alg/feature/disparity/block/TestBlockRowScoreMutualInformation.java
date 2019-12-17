/*
 * Copyright (c) 2011-2019, Peter Abeles. All Rights Reserved.
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

package boofcv.alg.feature.disparity.block;

import boofcv.alg.feature.disparity.sgm.SgmDisparityCost;
import boofcv.alg.feature.disparity.sgm.cost.StereoMutualInformation;
import boofcv.struct.image.GrayU8;
import boofcv.struct.image.ImageType;
import org.junit.jupiter.api.Nested;

/**
 * @author Peter Abeles
 */
class TestBlockRowScoreMutualInformation {

	@Nested
	class U8 extends ChecksBlockRowScore.ArrayIntI<GrayU8> {
		StereoMutualInformation smi = new StereoMutualInformation();

		U8() {super(255, ImageType.single(GrayU8.class));
		smi.diagonalHistogram(1.0, SgmDisparityCost.MAX_COST);}

		@Override
		public BlockRowScore<GrayU8, int[]> createAlg(int radiusWidth, int radiusHeight) {
			return new BlockRowScoreMutualInformation.U8(smi);
		}

		@Override
		protected int computeError(int a, int b) {
			return smi.costScaled(a,b);
		}
	}
}