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

package boofcv.app;

import boofcv.generate.Unit;
import org.kohsuke.args4j.Option;

/**
 * @author Peter Abeles
 */
public class CreateFiducialUchiya {
	@Option(name="-n",aliases = {"--Number"}, usage="Number of dots")
	int numberOfDots=-1;

	double dotRadius=-1;

	long randomSeed=0xDEADBEEF;

	@Option(name = "-w", aliases = {"--MarkerWidth"}, usage = "Width of each marker.  In document units.")
	public float markerWidth = -1;

	@Option(name="-u",aliases = {"--Units"}, usage="Name of document units.  default: cm")
	String _unit = Unit.CENTIMETER.abbreviation;
	Unit unit;

	@Option(name="-p",aliases = {"--PaperSize"}, usage="Size of paper used.  See below for predefined document sizes.  "
			+"You can manually specify any size using the following notation. W:H  where W is the width and H is the height.  "
			+"Values of W and H is specified with <number><unit abbreviation>, e.g. 6cm or 6, the unit is optional.  If no unit"
			+" are specified the default document units are used.")
	String _paperSize = PaperSize.LETTER.name;
	PaperSize paperSize;

	@Option(name = "-o", aliases = {"--OutputFile"}, usage = "Name of output file. Extension determines file type. E.g. uchiya.pdf. " +
			"Valid extensions are pdf, png, jpg, gif, bmp")
	public String fileName = "uchiya";

	@Option(name = "--DisablePrintInfo", usage = "Disable printing information about the calibration target")
	public boolean disablePrintInfo = false;

	@Option(name = "--GridFill", usage = "Flag to turn on filling the entire document with a grid of qr codes")
	public boolean gridFill = false;

	@Option(name = "--DrawGrid", usage = "Draws a line showing the grid")
	public boolean drawGrid = false;

	@Option(name = "--HideInfo", usage = "Flag that's used to turn off the printing of extra information")
	public boolean hideInfo = false;

	// if true it will send a document to the printer instead of saving it
	public boolean sendToPrinter = false;
	// specifies the file type
	public String fileType;


	public Unit getUnit() {
		return unit;
	}

}
