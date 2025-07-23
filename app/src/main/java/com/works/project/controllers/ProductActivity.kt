package com.works.project.controllers

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.works.project.R
import com.works.project.adampters.ProductAdapter
import com.works.project.models.Product
import com.works.project.models.Products
import com.works.project.services.IProducts
import com.works.project.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {

    var list:List<Product> = listOf()
    lateinit var iProducts: IProducts
    lateinit var productList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product)

        productList = findViewById(R.id.productList)
        productList.layoutManager = LinearLayoutManager(this)

        iProducts = ApiClient().getClient().create(IProducts::class.java)
        iProducts.getProducts(1, 10).enqueue(object:Callback<Products>{
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                if (response.isSuccessful) {
                    val products = response.body()
                    products?.let {
                        list = it.data
                        productList.adapter = ProductAdapter(list)
                        //productList.adapter?.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                Toast.makeText(this@ProductActivity, "Product Fail", Toast.LENGTH_SHORT).show()
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


}