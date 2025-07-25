package com.works.project.adampters

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.works.project.R
import com.works.project.models.Product
import com.works.project.utils.CustomDialogDetail

class ProductItemHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

    fun bindtem(itemProduct: Product) {

        val pr_title = itemView.findViewById<TextView>(R.id.nr_title)
        val pr_price = itemView.findViewById<TextView>(R.id.nr_detail)
        val pr_image = itemView.findViewById<ImageView>(R.id.pr_image)
        val pr_BtnInfo = itemView.findViewById<Button>(R.id.pr_BtnInfo)

        pr_title.text = itemProduct.title
        pr_price.text = itemProduct.price.toString()
        pr_BtnInfo.setOnClickListener {
            CustomDialogDetail(itemView.context).showDialog(itemProduct)
        }

        val imageUrl = itemProduct.images[0]
        pr_image.load(imageUrl)

    }


}