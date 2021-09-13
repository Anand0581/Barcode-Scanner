/**
 * Created by Anand kashyap on 19,July,2020
 */

package com.google.firebase.ml.md.java.productsearch;

/** Information about a product. */
public class Product {

  final String imageUrl;
  final String title;
  final String subtitle;

  Product(String imageUrl, String title, String subtitle) {
    this.imageUrl = imageUrl;
    this.title = title;
    this.subtitle = subtitle;
  }
}
