/**
 * Created by Anand kashyap on 19,July,2020
 */

package com.google.firebase.ml.md.kotlin.barcodedetection

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/** Information about a barcode field.  */
@Parcelize
data class BarcodeField(val label: String, val value: String) : Parcelable
