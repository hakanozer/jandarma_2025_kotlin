package com.works.project.controllers

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.works.project.R
import com.works.project.models.Product
import com.works.project.services.IProducts
import com.works.project.utils.ApiClient
import com.works.project.utils.AppConts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailActivity : AppCompatActivity() {

    lateinit var iProducts: IProducts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_detail)

        iProducts = ApiClient().getClient().create(IProducts::class.java)

        // get extra data
        val pid = intent.getLongExtra("pid", 0)
        pid?.let {
            iProducts.getProductDetail(it).enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    if (response.isSuccessful) {
                        Log.d("Product Service DEtail", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            } )
        }
        Log.d("pid",  pid.toString())
        Log.d("Detail Product", AppConts.product.toString())

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}