/**
 * Created by Anand kashyap on 19,July,2020
 */

package com.google.firebase.ml.md.java.camera;

/** Metadata info of a camera frame. */
class FrameMetadata {

  final int width;
  final int height;
  final int rotation;

  FrameMetadata(int width, int height, int rotation) {
    this.width = width;
    this.height = height;
    this.rotation = rotation;
  }
}
