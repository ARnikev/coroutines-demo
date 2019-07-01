package ru.nikeshkin.coroutinesdemo.client

import retrofit2.http.GET
import retrofit2.http.Path
import ru.nikeshkin.coroutinesdemo.domain.User

interface UserApi {

    @GET("/users/{id}")
    suspend fun getUserById(@Path("id") id: Long): User

    @GET("/users/{id}")
    fun getUserByIdBlocking(@Path("id") id: Long): User
}