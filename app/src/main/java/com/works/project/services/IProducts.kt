package com.works.project.services

import com.works.project.models.Product
import com.works.project.models.Products
import com.works.project.models.SingleProduct
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IProducts {

    @GET("products")
    fun getProducts(@Query("page") page: Int, @Query("per_page") perPage: Int): Call<Products>

    @GET("products/{pid}")
    fun getProductDetail(@Path("pid") pid: Long): Call<SingleProduct>

}