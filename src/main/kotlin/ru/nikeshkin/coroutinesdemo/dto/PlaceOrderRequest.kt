package ru.nikeshkin.coroutinesdemo.dto

data class PlaceOrderRequest(
    val productId: Long,
    val userId: Long,
    val deliveryAddress: String
)