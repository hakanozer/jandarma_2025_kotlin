package com.works.project.ijson

import com.works.project.models.User
import com.works.project.models.UserLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface IUser {

    @POST("auth/login")
    fun userLogin(@Body userLogin: UserLogin): Call<User>

}