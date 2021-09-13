/**
 * Created by Anand kashyap on 19,July,2020
 */

package com.google.firebase.ml.md.java.productsearch;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import com.google.firebase.ml.md.R;
import com.google.firebase.ml.md.java.Utils;
import com.google.firebase.ml.md.java.objectdetection.DetectedObject;
import java.util.List;

/** Hosts the detected object info and its search result. */
public class SearchedObject {

  private final DetectedObject object;
  private final List<Product> productList;
  private final int objectThumbnailCornerRadius;

  @Nullable
  private Bitmap objectThumbnail;

  public SearchedObject(Resources resources, DetectedObject object, List<Product> productList) {
    this.object = object;
    this.productList = productList;
    this.objectThumbnailCornerRadius =
        resources.getDimensionPixelOffset(R.dimen.bounding_box_corner_radius);
  }

  public int getObjectIndex() {
    return object.getObjectIndex();
  }

  public List<Product> getProductList() {
    return productList;
  }

  public Rect getBoundingBox() {
    return object.getBoundingBox();
  }

  public synchronized Bitmap getObjectThumbnail() {
    if (objectThumbnail == null) {
      objectThumbnail =
          Utils.getCornerRoundedBitmap(object.getBitmap(), objectThumbnailCornerRadius);
    }
    return objectThumbnail;
  }
}
