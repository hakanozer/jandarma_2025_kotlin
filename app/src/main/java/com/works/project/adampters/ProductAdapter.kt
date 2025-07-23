package com.works.project.adampters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.works.project.R
import com.works.project.controllers.ProductDetailActivity
import com.works.project.models.Product
import com.works.project.utils.AppConts

class ProductAdapter(private val list: List<Product>) : RecyclerView.Adapter<ProductItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemHolder {
        val view = ProductItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false))
        view.itemView.setOnClickListener {
            val product = list[view.position]
            val intent = Intent(parent.context, ProductDetailActivity::class.java)
            intent.putExtra("pid", product.id)
            AppConts.product = product
            parent.context.startActivity(intent)
        }
        return view
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductItemHolder, position: Int) {
        holder.bindtem(list[position])
    }

}