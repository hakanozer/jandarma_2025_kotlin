package com.works.project.adampters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.works.project.R
import com.works.project.models.Product

class ProductItemHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

    fun bindtem(itemProduct: Product) {

        val pr_title = itemView.findViewById<TextView>(R.id.pr_title)
        val pr_price = itemView.findViewById<TextView>(R.id.pr_price)
        val pr_image = itemView.findViewById<ImageView>(R.id.pr_image)

        pr_title.text = itemProduct.title
        pr_price.text = itemProduct.price.toString()

        val imageUrl = itemProduct.images[0]
        pr_image.load(imageUrl)

    }


}