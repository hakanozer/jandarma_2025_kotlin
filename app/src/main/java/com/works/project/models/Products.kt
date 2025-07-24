package com.works.project.models

import com.fasterxml.jackson.annotation.JsonProperty

data class SingleProduct(
    val meta: Meta,
    val data: Product,
)

data class Products(
    val meta: ProMeta,
    val data: List<Product>,
)

data class ProMeta(
    val status: Long,
    val message: String,
    val pagination: Pagination,
)

data class Pagination(
    val page: Long,
    @JsonProperty("per_page")
    val perPage: Long,
    @JsonProperty("total_items")
    val totalItems: Long,
    @JsonProperty("total_pages")
    val totalPages: Long,
)

data class Product(
    val id: Long,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Long,
    val tags: List<String>,
    val brand: String,
    val sku: String,
    val minimumOrderQuantity: Long,
    val images: List<String>,
)

