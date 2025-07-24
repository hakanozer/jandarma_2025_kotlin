package com.works.project.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.works.project.R
import com.works.project.models.Product

class CustomDialogDetail(val context: Context) {

    // custom dialog show
    fun showDialog(item: Product) {
        val dialog = Dialog(context)
        val view = LayoutInflater.from(context).inflate(R.layout.detail_dialog, null)

        val stock = view.findViewById<TextView>(R.id.dia_txtStock)
        val closBtn = view.findViewById<Button>(R.id.di_btnClose)

        stock.text = item.stock.toString()
        closBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setContentView(view)
        dialog.show()
    }

}