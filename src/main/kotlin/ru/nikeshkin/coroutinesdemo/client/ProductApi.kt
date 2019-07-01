package ru.nikeshkin.coroutinesdemo.client

import retrofit2.http.GET
import retrofit2.http.Path
import ru.nikeshkin.coroutinesdemo.domain.Product

interface ProductApi {

    @GET("/products/{id}")
    suspend fun getProductById(@Path("id") id: Long): Product

    @GET("/products/{id}")
    fun getProductByIdBlocking(@Path("id") id: Long): Product
}