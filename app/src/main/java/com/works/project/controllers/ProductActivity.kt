package com.works.project.controllers

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

    lateinit var sharedPreferences: SharedPreferences
    var list:List<Product> = listOf()
    lateinit var iProducts: IProducts
    lateinit var productList: RecyclerView
    lateinit var p_txtName: TextView

    // 1. ilk çalışan method
    // View katmanı hazırlanıyor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product)

        Log.d("Sıra", "1 - onCreate: ")

        p_txtName = findViewById(R.id.p_txtName)
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        sharedPreferences.getString("name", "")?.let {
            p_txtName.text = it
        }

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

    // 2. Sıra
    // arkaplandan geri gelindiğinde çalışan method
    // örn: kullanıcı geri geldiğinde çalışır
    override fun onStart() {
        super.onStart()
        Log.d("Sıra", "2 - onStart: ")
    }

    // 3. Sıra
    // activity başlatıldıktan sonra kullanıcı ile etkileşim kurulduğunda çalışan method
    override fun onResume() {
        super.onResume()
        Log.d("Sıra", "3 - onResume: ")
    }

    // 4. sıra
    // activiy kapatılma aşamasında çalıişan method
    override fun onPause() {
        super.onPause()
        Log.d("Sıra", "4 - onPause: ")
    }

    // 5. sıra
    // activity tamamen kapatıldığında çalışan method
    override fun onStop() {
        super.onStop()
        Log.d("Sıra", "5 - onStop: ")
    }

    // 6. sıra
    // activity finish olduğunda çalışan method
    override fun onDestroy() {
        super.onDestroy()
        Log.d("Sıra", "6 - onDestroy: ")
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_profile -> {
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_customer -> {
                Toast.makeText(this, "Customer", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}