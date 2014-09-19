// Launches a webcam and searches for square-binary fiducials.  Draws a cube over the feducials when it
// finds them and their ID number

import processing.video.*;
import boofcv.processing.*;

Capture cam;
SimpleFiducial detector;

void setup() {
  // Open up the camera so that it has a video feed to process
  initializeCamera(320, 240);
  size(cam.width, cam.height);

  // Robust fiducial detectors are invariant to lightning conditions, while the other is much faster
  // but is much more brittle
  detector = Boof.fiducialSquareBinaryRobust(0.1,4);
  //detector = Boof.fiducialSquareBinary(0.1,100);

  // Much better results if you calibrate the camera.  For now let's just guess its parameters
  // detector.setIntrinsic(intrinsic);
  detector.guessCrappyIntrinsic(cam.width,cam.height);

  f = createFont("Arial", 32, true);
}

void draw() {
  if (cam.available() == true) {
    cam.read();

    List<FiducialFound> found = detector.detect(cam);

    image(cam, 0, 0);

    for( FiducialFound f : found ) {
      detector.render(this,f);
    }
}

void initializeCamera( int desiredWidth, int desiredHeight ) {
  String[] cameras = Capture.list();

  if (cameras.length == 0) {
    println("There are no cameras available for capture.");
    exit();
  } else {
    cam = new Capture(this, desiredWidth, desiredHeight);
    cam.start();
  }
}
