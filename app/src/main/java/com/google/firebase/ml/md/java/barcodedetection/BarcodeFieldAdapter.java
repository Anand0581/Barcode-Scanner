/**
 * Created by Anand kashyap on 19,July,2020
 */

package com.google.firebase.ml.md.java.barcodedetection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.ml.md.R;
import com.google.firebase.ml.md.java.barcodedetection.BarcodeFieldAdapter.BarcodeFieldViewHolder;
import java.util.List;

/** Presents a list of field info in the detected barcode. */
class BarcodeFieldAdapter extends RecyclerView.Adapter<BarcodeFieldViewHolder> {

  static class BarcodeFieldViewHolder extends RecyclerView.ViewHolder {

    static BarcodeFieldViewHolder create(ViewGroup parent) {
      View view =
          LayoutInflater.from(parent.getContext()).inflate(R.layout.barcode_field, parent, false);
      return new BarcodeFieldViewHolder(view);
    }

    private final TextView labelView;
    private final TextView valueView;

    private BarcodeFieldViewHolder(View view) {
      super(view);
      labelView = view.findViewById(R.id.barcode_field_label);
      valueView = view.findViewById(R.id.barcode_field_value);
    }

    void bindBarcodeField(BarcodeField barcodeField) {
      labelView.setText(barcodeField.label);
      valueView.setText(barcodeField.value);
    }
  }

  private final List<BarcodeField> barcodeFieldList;

  BarcodeFieldAdapter(List<BarcodeField> barcodeFieldList) {
    this.barcodeFieldList = barcodeFieldList;
  }

  @Override
  @NonNull
  public BarcodeFieldViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return BarcodeFieldViewHolder.create(parent);
  }

  @Override
  public void onBindViewHolder(@NonNull BarcodeFieldViewHolder holder, int position) {
    holder.bindBarcodeField(barcodeFieldList.get(position));
  }

  @Override
  public int getItemCount() {
    return barcodeFieldList.size();
  }
}
