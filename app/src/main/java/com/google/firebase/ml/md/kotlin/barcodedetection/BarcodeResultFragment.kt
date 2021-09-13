/**
 * Created by Anand kashyap on 19,July,2020
 */

package com.google.firebase.ml.md.kotlin.barcodedetection

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.ml.md.R
import com.google.firebase.ml.md.kotlin.camera.WorkflowModel
import com.google.firebase.ml.md.kotlin.camera.WorkflowModel.WorkflowState
import kotlinx.android.synthetic.main.barcode_bottom_sheet.view.*


/** Displays the bottom sheet to present barcode fields contained in the detected barcode.  */
class BarcodeResultFragment : BottomSheetDialogFragment() {
    lateinit var openBtn :View

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        val view = layoutInflater.inflate(R.layout.barcode_bottom_sheet, viewGroup)
        openBtn = view.open_btn
        val arguments = arguments
        val barcodeFieldList: ArrayList<BarcodeField> =
                if (arguments?.containsKey(ARG_BARCODE_FIELD_LIST)!!) {
                    arguments.getParcelableArrayList(ARG_BARCODE_FIELD_LIST) ?: ArrayList()
                } else {
                    Log.e(TAG, "No barcode field list passed in!")
                    ArrayList()
                }

        view.findViewById<RecyclerView>(R.id.barcode_field_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = BarcodeFieldAdapter(barcodeFieldList,this@BarcodeResultFragment)
        }

        return view
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.barcode_field_value -> Toast.makeText(context, "Copied", Toast.LENGTH_LONG).show()
        }
        return super.onContextItemSelected(item)
    }
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.add(0,v.id,0,"Copy")
        val textView = v as TextView
        val manager : ClipboardManager =  requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", textView.text)
        manager.setPrimaryClip(clipData)
    }

    override fun onDismiss(dialogInterface: DialogInterface) {
        activity?.let {
            // Back to working state after the bottom sheet is dismissed.
            ViewModelProviders.of(it).get<WorkflowModel>(WorkflowModel::class.java)
                    .setWorkflowState(WorkflowState.DETECTING)
        }
        super.onDismiss(dialogInterface)
    }

    companion object {

        private const val TAG = "BarcodeResultFragment"
        private const val ARG_BARCODE_FIELD_LIST = "arg_barcode_field_list"

        fun show(fragmentManager: FragmentManager, barcodeFieldArrayList: ArrayList<BarcodeField>) {
            val barcodeResultFragment = BarcodeResultFragment()
            barcodeResultFragment.arguments = Bundle().apply {
                putParcelableArrayList(ARG_BARCODE_FIELD_LIST, barcodeFieldArrayList)
            }
            barcodeResultFragment.show(fragmentManager, TAG)
        }

        fun dismiss(fragmentManager: FragmentManager) {
            (fragmentManager.findFragmentByTag(TAG) as BarcodeResultFragment?)?.dismiss()
        }
    }
}
