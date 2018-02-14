/*
 * Copyright (c) 2011-2018, Peter Abeles. All Rights Reserved.
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
package boofcv.alg.filter.binary;

import boofcv.alg.InputSanityCheck;
import boofcv.alg.filter.blur.BlurImageOps;
import boofcv.struct.ConfigLength;
import boofcv.struct.image.*;

/**
 * <p>
 * Operations for thresholding images and converting them into a binary image.
 * </p>
 *
 * <p>
 * WARNING: Do not modify.  Automatically generated by GenerateThresholdImageOps.
 * </p>
 *
 * @author Peter Abeles
 */
public class ThresholdImageOps {

	/**
	 * Applies a global threshold across the whole image.  If 'down' is true, then pixels with values <=
	 * to 'threshold' are set to 1 and the others set to 0.  If 'down' is false, then pixels with values >
	 * to 'threshold' are set to 1 and the others set to 0.
	 *
	 * @param input Input image. Not modified.
	 * @param output (Optional) Binary output image. If null a new image will be declared. Modified.
	 * @param threshold threshold value.
	 * @param down If true then the inequality <= is used, otherwise if false then &gt; is used.
	 * @return Output image.
	 */
	public static GrayU8 threshold( GrayF32 input , GrayU8 output ,
										float threshold , boolean down )
	{
		output = InputSanityCheck.checkDeclare(input,output,GrayU8.class);

		if( down ) {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;

				for( int i = input.width; i>0; i-- ) {
					output.data[indexOut++] = (byte)((input.data[indexIn++]) <= threshold ? 1 : 0);
				}
			}
		} else {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;

				for( int i = input.width; i>0; i-- ) {
					output.data[indexOut++] = (byte)((input.data[indexIn++]) > threshold ? 1 : 0);
				}
			}
		}

		return output;
	}

	/**
	 * Applies a global threshold across the whole image.  If 'down' is true, then pixels with values <=
	 * to 'threshold' are set to 1 and the others set to 0.  If 'down' is false, then pixels with values >
	 * to 'threshold' are set to 1 and the others set to 0.
	 *
	 * @param input Input image. Not modified.
	 * @param output (Optional) Binary output image. If null a new image will be declared. Modified.
	 * @param threshold threshold value.
	 * @param down If true then the inequality <= is used, otherwise if false then &gt; is used.
	 * @return Output image.
	 */
	public static GrayU8 threshold( GrayF64 input , GrayU8 output ,
										double threshold , boolean down )
	{
		output = InputSanityCheck.checkDeclare(input,output,GrayU8.class);

		if( down ) {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;

				for( int i = input.width; i>0; i-- ) {
					output.data[indexOut++] = (byte)((input.data[indexIn++]) <= threshold ? 1 : 0);
				}
			}
		} else {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;

				for( int i = input.width; i>0; i-- ) {
					output.data[indexOut++] = (byte)((input.data[indexIn++]) > threshold ? 1 : 0);
				}
			}
		}

		return output;
	}

	/**
	 * Applies a global threshold across the whole image.  If 'down' is true, then pixels with values <=
	 * to 'threshold' are set to 1 and the others set to 0.  If 'down' is false, then pixels with values >
	 * to 'threshold' are set to 1 and the others set to 0.
	 *
	 * @param input Input image. Not modified.
	 * @param output (Optional) Binary output image. If null a new image will be declared. Modified.
	 * @param threshold threshold value.
	 * @param down If true then the inequality <= is used, otherwise if false then &gt; is used.
	 * @return Output image.
	 */
	public static GrayU8 threshold( GrayU8 input , GrayU8 output ,
										int threshold , boolean down )
	{
		output = InputSanityCheck.checkDeclare(input,output,GrayU8.class);

		if( down ) {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;

				for( int i = input.width; i>0; i-- ) {
					output.data[indexOut++] = (byte)((input.data[indexIn++]& 0xFF) <= threshold ? 1 : 0);
				}
			}
		} else {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;

				for( int i = input.width; i>0; i-- ) {
					output.data[indexOut++] = (byte)((input.data[indexIn++]& 0xFF) > threshold ? 1 : 0);
				}
			}
		}

		return output;
	}

	/**
	 * Applies a global threshold across the whole image.  If 'down' is true, then pixels with values <=
	 * to 'threshold' are set to 1 and the others set to 0.  If 'down' is false, then pixels with values >
	 * to 'threshold' are set to 1 and the others set to 0.
	 *
	 * @param input Input image. Not modified.
	 * @param output (Optional) Binary output image. If null a new image will be declared. Modified.
	 * @param threshold threshold value.
	 * @param down If true then the inequality <= is used, otherwise if false then &gt; is used.
	 * @return Output image.
	 */
	public static GrayU8 threshold( GrayS16 input , GrayU8 output ,
										int threshold , boolean down )
	{
		output = InputSanityCheck.checkDeclare(input,output,GrayU8.class);

		if( down ) {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;

				for( int i = input.width; i>0; i-- ) {
					output.data[indexOut++] = (byte)((input.data[indexIn++]) <= threshold ? 1 : 0);
				}
			}
		} else {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;

				for( int i = input.width; i>0; i-- ) {
					output.data[indexOut++] = (byte)((input.data[indexIn++]) > threshold ? 1 : 0);
				}
			}
		}

		return output;
	}

	/**
	 * Applies a global threshold across the whole image.  If 'down' is true, then pixels with values <=
	 * to 'threshold' are set to 1 and the others set to 0.  If 'down' is false, then pixels with values >
	 * to 'threshold' are set to 1 and the others set to 0.
	 *
	 * @param input Input image. Not modified.
	 * @param output (Optional) Binary output image. If null a new image will be declared. Modified.
	 * @param threshold threshold value.
	 * @param down If true then the inequality <= is used, otherwise if false then &gt; is used.
	 * @return Output image.
	 */
	public static GrayU8 threshold( GrayU16 input , GrayU8 output ,
										int threshold , boolean down )
	{
		output = InputSanityCheck.checkDeclare(input,output,GrayU8.class);

		if( down ) {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;

				for( int i = input.width; i>0; i-- ) {
					output.data[indexOut++] = (byte)((input.data[indexIn++]& 0xFFFF) <= threshold ? 1 : 0);
				}
			}
		} else {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;

				for( int i = input.width; i>0; i-- ) {
					output.data[indexOut++] = (byte)((input.data[indexIn++]& 0xFFFF) > threshold ? 1 : 0);
				}
			}
		}

		return output;
	}

	/**
	 * Applies a global threshold across the whole image.  If 'down' is true, then pixels with values <=
	 * to 'threshold' are set to 1 and the others set to 0.  If 'down' is false, then pixels with values >
	 * to 'threshold' are set to 1 and the others set to 0.
	 *
	 * @param input Input image. Not modified.
	 * @param output (Optional) Binary output image. If null a new image will be declared. Modified.
	 * @param threshold threshold value.
	 * @param down If true then the inequality <= is used, otherwise if false then &gt; is used.
	 * @return Output image.
	 */
	public static GrayU8 threshold( GrayS32 input , GrayU8 output ,
										int threshold , boolean down )
	{
		output = InputSanityCheck.checkDeclare(input,output,GrayU8.class);

		if( down ) {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;

				for( int i = input.width; i>0; i-- ) {
					output.data[indexOut++] = (byte)((input.data[indexIn++]) <= threshold ? 1 : 0);
				}
			}
		} else {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;

				for( int i = input.width; i>0; i-- ) {
					output.data[indexOut++] = (byte)((input.data[indexIn++]) > threshold ? 1 : 0);
				}
			}
		}

		return output;
	}

	/**
	 * Thresholds the image using a locally adaptive threshold that is computed using a local square region centered
	 * on each pixel.  The threshold is equal to the average value of the surrounding pixels times the scale.
	 * If down is true then b(x,y) = I(x,y) &le; T(x,y) * scale ? 1 : 0.  Otherwise
	 * b(x,y) = I(x,y) * scale &gt; T(x,y) ? 0 : 1
	 *
	 * @param input Input image.
	 * @param output (optional) Output binary image.  If null it will be declared internally.
	 * @param width Width of square region.
	 * @param scale Scale factor used to adjust threshold.  Try 0.95
	 * @param down Should it threshold up or down.
	 * @param storage1 (Optional) Storage for intermediate step. If null will be declared internally.
	 * @param storage2 (Optional) Storage for intermediate step. If null will be declared internally.
	 * @return Thresholded image.
	 */
	public static GrayU8 localSquare( GrayU8 input , GrayU8 output ,
											 ConfigLength width , float scale , boolean down ,
											 GrayU8 storage1 , GrayU8 storage2 ) {

		output = InputSanityCheck.checkDeclare(input,output,GrayU8.class);
		storage1 = InputSanityCheck.checkDeclare(input,storage1,GrayU8.class);
		storage2 = InputSanityCheck.checkDeclare(input,storage2,GrayU8.class);

		int radius = width.computeI(Math.min(input.width,input.height))/2;

		GrayU8 mean = storage1;

		BlurImageOps.mean(input,mean,radius,storage2);

		if( down ) {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;
				int indexMean = mean.startIndex + y*mean.stride;

				int end = indexIn + input.width;

				for( ; indexIn < end; indexIn++ , indexOut++, indexMean++ ) {
					float threshold = (mean.data[indexMean]& 0xFF) * scale;

					if( (input.data[indexIn]& 0xFF) <= threshold )
						output.data[indexOut] = 1;
					else
						output.data[indexOut] = 0;
				}
			}
		} else {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;
				int indexMean = mean.startIndex + y*mean.stride;

				int end = indexIn + input.width;

				for( ; indexIn < end; indexIn++ , indexOut++, indexMean++ ) {
					int threshold = (mean.data[indexMean]& 0xFF);

					if( (input.data[indexIn]& 0xFF) * scale > threshold )
						output.data[indexOut] = 1;
					else
						output.data[indexOut] = 0;
				}
			}
		}

		return output;
	}

	/**
	 * Thresholds the image using a locally adaptive threshold that is computed using a local square region centered
	 * on each pixel.  The threshold is equal to the gaussian weighted sum of the surrounding pixels times the scale.
	 * If down is true then b(x,y) = I(x,y) &le; T(x,y) * scale ? 1 : 0.  Otherwise
	 * b(x,y) = I(x,y) * scale &gt; T(x,y) ? 0 : 1
	 *
	 * @param input Input image.
	 * @param output (optional) Output binary image.  If null it will be declared internally.
	 * @param width Width of square region.
	 * @param scale Scale factor used to adjust threshold.  Try 0.95
	 * @param down Should it threshold up or down.
	 * @param storage1 (Optional) Storage for intermediate step. If null will be declared internally.
	 * @param storage2 (Optional) Storage for intermediate step. If null will be declared internally.
	 * @return Thresholded image.
	 */
	public static GrayU8 localGaussian( GrayU8 input , GrayU8 output ,
										ConfigLength width , float scale , boolean down ,
										GrayU8 storage1 , GrayU8 storage2 ) {

		output = InputSanityCheck.checkDeclare(input,output,GrayU8.class);
		storage1 = InputSanityCheck.checkDeclare(input,storage1,GrayU8.class);
		storage2 = InputSanityCheck.checkDeclare(input,storage2,GrayU8.class);

		int radius = width.computeI(Math.min(input.width,input.height))/2;

		GrayU8 blur = storage1;

		BlurImageOps.gaussian(input,blur,-1,radius,storage2);

		if( down ) {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;
				int indexMean = blur.startIndex + y*blur.stride;

				int end = indexIn + input.width;

				for( ; indexIn < end; indexIn++ , indexOut++, indexMean++ ) {
					float threshold = (blur.data[indexMean]& 0xFF) * scale;

					if( (input.data[indexIn]& 0xFF) <= threshold )
						output.data[indexOut] = 1;
					else
						output.data[indexOut] = 0;
				}
			}
		} else {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;
				int indexMean = blur.startIndex + y*blur.stride;

				int end = indexIn + input.width;

				for( ; indexIn < end; indexIn++ , indexOut++, indexMean++ ) {
					int threshold = (blur.data[indexMean]& 0xFF);

					if( (input.data[indexIn]& 0xFF) * scale > threshold )
						output.data[indexOut] = 1;
					else
						output.data[indexOut] = 0;
				}
			}
		}

		return output;
	}

	/**
	 * Thresholds the image using a locally adaptive threshold that is computed using a local square region centered
	 * on each pixel.  The threshold is equal to the average value of the surrounding pixels times the scale.
	 * If down is true then b(x,y) = I(x,y) &le; T(x,y) * scale ? 1 : 0.  Otherwise
	 * b(x,y) = I(x,y) * scale &gt; T(x,y) ? 0 : 1
	 *
	 * @param input Input image.
	 * @param output (optional) Output binary image.  If null it will be declared internally.
	 * @param width Width of square region.
	 * @param scale Scale factor used to adjust threshold.  Try 0.95
	 * @param down Should it threshold up or down.
	 * @param storage1 (Optional) Storage for intermediate step. If null will be declared internally.
	 * @param storage2 (Optional) Storage for intermediate step. If null will be declared internally.
	 * @return Thresholded image.
	 */
	public static GrayU8 localSquare( GrayF32 input , GrayU8 output ,
											 ConfigLength width , float scale , boolean down ,
											 GrayF32 storage1 , GrayF32 storage2 ) {

		output = InputSanityCheck.checkDeclare(input,output,GrayU8.class);
		storage1 = InputSanityCheck.checkDeclare(input,storage1,GrayF32.class);
		storage2 = InputSanityCheck.checkDeclare(input,storage2,GrayF32.class);

		int radius = width.computeI(Math.min(input.width,input.height))/2;

		GrayF32 mean = storage1;

		BlurImageOps.mean(input,mean,radius,storage2);

		if( down ) {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;
				int indexMean = mean.startIndex + y*mean.stride;

				int end = indexIn + input.width;

				for( ; indexIn < end; indexIn++ , indexOut++, indexMean++ ) {
					float threshold = (mean.data[indexMean]) * scale;

					if( (input.data[indexIn]) <= threshold )
						output.data[indexOut] = 1;
					else
						output.data[indexOut] = 0;
				}
			}
		} else {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;
				int indexMean = mean.startIndex + y*mean.stride;

				int end = indexIn + input.width;

				for( ; indexIn < end; indexIn++ , indexOut++, indexMean++ ) {
					float threshold = (mean.data[indexMean]);

					if( (input.data[indexIn]) * scale > threshold )
						output.data[indexOut] = 1;
					else
						output.data[indexOut] = 0;
				}
			}
		}

		return output;
	}

	/**
	 * Thresholds the image using a locally adaptive threshold that is computed using a local square region centered
	 * on each pixel.  The threshold is equal to the gaussian weighted sum of the surrounding pixels times the scale.
	 * If down is true then b(x,y) = I(x,y) &le; T(x,y) * scale ? 1 : 0.  Otherwise
	 * b(x,y) = I(x,y) * scale &gt; T(x,y) ? 0 : 1
	 *
	 * @param input Input image.
	 * @param output (optional) Output binary image.  If null it will be declared internally.
	 * @param width Width of square region.
	 * @param scale Scale factor used to adjust threshold.  Try 0.95
	 * @param down Should it threshold up or down.
	 * @param storage1 (Optional) Storage for intermediate step. If null will be declared internally.
	 * @param storage2 (Optional) Storage for intermediate step. If null will be declared internally.
	 * @return Thresholded image.
	 */
	public static GrayU8 localGaussian( GrayF32 input , GrayU8 output ,
										ConfigLength width , float scale , boolean down ,
										GrayF32 storage1 , GrayF32 storage2 ) {

		output = InputSanityCheck.checkDeclare(input,output,GrayU8.class);
		storage1 = InputSanityCheck.checkDeclare(input,storage1,GrayF32.class);
		storage2 = InputSanityCheck.checkDeclare(input,storage2,GrayF32.class);

		int radius = width.computeI(Math.min(input.width,input.height))/2;

		GrayF32 blur = storage1;

		BlurImageOps.gaussian(input,blur,-1,radius,storage2);

		if( down ) {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;
				int indexMean = blur.startIndex + y*blur.stride;

				int end = indexIn + input.width;

				for( ; indexIn < end; indexIn++ , indexOut++, indexMean++ ) {
					float threshold = (blur.data[indexMean]) * scale;

					if( (input.data[indexIn]) <= threshold )
						output.data[indexOut] = 1;
					else
						output.data[indexOut] = 0;
				}
			}
		} else {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;
				int indexMean = blur.startIndex + y*blur.stride;

				int end = indexIn + input.width;

				for( ; indexIn < end; indexIn++ , indexOut++, indexMean++ ) {
					float threshold = (blur.data[indexMean]);

					if( (input.data[indexIn]) * scale > threshold )
						output.data[indexOut] = 1;
					else
						output.data[indexOut] = 0;
				}
			}
		}

		return output;
	}


}
