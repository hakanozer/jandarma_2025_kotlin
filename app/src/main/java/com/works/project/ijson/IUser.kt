package com.works.project.ijson

import retrofit2.Call
import retrofit2.http.POST


interface IUser {

    @POST("auth/login")
    fun userLogin(): Call<Any>

}