/**
 * Created by Anand kashyap on 19,July,2020
 */

package com.google.firebase.ml.md.kotlin.barcodedetection

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ml.md.R
import com.google.firebase.ml.md.kotlin.barcodedetection.BarcodeFieldAdapter.BarcodeFieldViewHolder

/** Presents a list of field info in the detected barcode.  */
internal class BarcodeFieldAdapter(private val barcodeFieldList: List<BarcodeField>, val barcodeResultFragment: BarcodeResultFragment) :
    RecyclerView.Adapter<BarcodeFieldViewHolder>() {

    internal class BarcodeFieldViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {

        private val labelView: TextView = view.findViewById(R.id.barcode_field_label)
        val valueView: TextView = view.findViewById(R.id.barcode_field_value)

        fun bindBarcodeField(barcodeField: BarcodeField) {
            labelView.text = barcodeField.label
            valueView.text = barcodeField.value
        }

        companion object {

            fun create(parent: ViewGroup): BarcodeFieldViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.barcode_field, parent, false)
                return BarcodeFieldViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarcodeFieldViewHolder =
        BarcodeFieldViewHolder.create(parent)

    override fun onBindViewHolder(holder: BarcodeFieldViewHolder, position: Int) {
        holder.bindBarcodeField(barcodeFieldList[position])
        barcodeResultFragment.openBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW ,Uri.parse(barcodeFieldList[position].value))
            barcodeResultFragment.startActivity(intent)
        }
        barcodeResultFragment.registerForContextMenu(holder.valueView);
    }


    override fun getItemCount(): Int =
        barcodeFieldList.size
}
