package com.works.project.controllers

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.works.project.R
import com.works.project.adampters.ImageSliderAdapter
import com.works.project.models.SingleProduct
import com.works.project.services.IProducts
import com.works.project.utils.ApiClient
import com.works.project.utils.AppConts
import com.works.project.utils.UtilApp
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity() {

    @Inject lateinit var utilApp: UtilApp

    lateinit var iProducts: IProducts
    lateinit var imageSlider: ViewPager2
    lateinit var d_txtTitle: TextView
    lateinit var d_txtPrice: TextView
    lateinit var d_txtDetail: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_detail)

        // action bar back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // title change
        supportActionBar?.title = "Product Detail"

        val data = utilApp.call()
        Log.d("datax", data)

        iProducts = ApiClient().getClient().create(IProducts::class.java)
        imageSlider = findViewById(R.id.imageSlider)
        d_txtTitle = findViewById(R.id.d_txtTitle)
        d_txtPrice = findViewById(R.id.d_txtPrice)
        d_txtDetail = findViewById(R.id.d_txtDetail)

        // get extra data
        val pid = intent.getLongExtra("pid", 0)
        pid?.let {
            iProducts.getProductDetail(it).enqueue(object : Callback<SingleProduct> {
                override fun onResponse(call: Call<SingleProduct>, response: Response<SingleProduct>) {
                    if (response.isSuccessful) {
                        // ?, !!, let
                        response.body()?.let {
                            val item = it.data
                            val images = item.images
                            val imageSliderAdapter = ImageSliderAdapter(images)
                            imageSlider.adapter = imageSliderAdapter

                            d_txtTitle.text = item.title
                            d_txtPrice.text = "${item.price}₺"
                            d_txtDetail.text = item.description
                        }

                    }
                }

                override fun onFailure(call: Call<SingleProduct>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
        Log.d("pid",  pid.toString())
        Log.d("Detail Product", AppConts.product.toString())

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}