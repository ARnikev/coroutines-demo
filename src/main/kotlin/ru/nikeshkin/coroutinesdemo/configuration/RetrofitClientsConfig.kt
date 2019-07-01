package ru.nikeshkin.coroutinesdemo.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import ru.nikeshkin.coroutinesdemo.client.ProductApi
import ru.nikeshkin.coroutinesdemo.client.UserApi

@Configuration
class RetrofitClientsConfig {

    @Bean
    fun productApiClient(objectMapper: ObjectMapper): ProductApi {
        val okHttpClient = OkHttpClient.Builder().build()

        return Retrofit.Builder()
            .baseUrl("http://localhost:1080")
            .client(okHttpClient)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build()
            .create(ProductApi::class.java)
    }

    @Bean
    fun userApiClient(objectMapper: ObjectMapper): UserApi {
        val okHttpClient = OkHttpClient.Builder().build()

        return Retrofit.Builder()
            .baseUrl("http://localhost:1080")
            .client(okHttpClient)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build()
            .create(UserApi::class.java)
    }
}