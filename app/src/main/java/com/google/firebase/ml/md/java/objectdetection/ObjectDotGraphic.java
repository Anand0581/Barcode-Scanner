/**
 * Created by Anand kashyap on 19,July,2020
 */

package com.google.firebase.ml.md.java.objectdetection;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.Rect;
import com.google.firebase.ml.md.java.camera.GraphicOverlay;
import com.google.firebase.ml.md.java.camera.GraphicOverlay.Graphic;
import com.google.firebase.ml.md.R;

/** A dot to indicate a detected object used by multiple objects detection mode. */
class ObjectDotGraphic extends Graphic {

  private final ObjectDotAnimator animator;
  private final Paint paint;
  private final PointF center;
  private final int dotRadius;
  private final int dotAlpha;

  ObjectDotGraphic(GraphicOverlay overlay, DetectedObject object, ObjectDotAnimator animator) {
    super(overlay);

    this.animator = animator;

    Rect box = object.getBoundingBox();
    center =
        new PointF(
            overlay.translateX((box.left + box.right) / 2f),
            overlay.translateY((box.top + box.bottom) / 2f));

    paint = new Paint();
    paint.setStyle(Style.FILL);
    paint.setColor(Color.WHITE);

    dotRadius = context.getResources().getDimensionPixelOffset(R.dimen.object_dot_radius);
    dotAlpha = paint.getAlpha();
  }

  @Override
  public void draw(Canvas canvas) {
    paint.setAlpha((int) (dotAlpha * animator.getAlphaScale()));
    canvas.drawCircle(center.x, center.y, dotRadius * animator.getRadiusScale(), paint);
  }
}
