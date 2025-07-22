package com.works.project.models

data class User (
    val meta: Meta,
    val data: Data
)

data class Data (
    val access_token: String,
    val token_type: String,
    val expires_in: Long,
    val user: UserClass
)

data class UserClass (
    val id: Long,
    val name: String,
    val email: String,
    val role: String,
    val remember_token: Any? = null,
    val created_at: String,
    val updated_at: String
)

data class Meta (
    val status: Long,
    val message: String
)

