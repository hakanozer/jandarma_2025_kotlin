package com.works.project.adampters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.works.project.R
import com.works.project.models.Product

class ProductAdapter(private val list: List<Product>) : RecyclerView.Adapter<ProductItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductItemHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductItemHolder, position: Int) {
        holder.bindtem(list[position])
    }

}