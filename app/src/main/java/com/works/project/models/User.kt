package com.works.project.models

data class User (
    val meta: Meta,
    val data: Data
)

data class Data (
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Long,
    val user: UserClass
)

data class UserClass (
    val id: Long,
    val name: String,
    val email: String,
    val role: String,
    val rememberToken: Any? = null,
    val createdAt: String,
    val updatedAt: String
)

data class Meta (
    val status: Long,
    val message: String
)

