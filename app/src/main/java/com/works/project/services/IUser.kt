package com.works.project.services

import com.works.project.models.Products
import com.works.project.models.User
import com.works.project.models.UserLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface IUser {

    @POST("auth/login")
    fun userLogin(@Body userLogin: UserLogin): Call<User>

}