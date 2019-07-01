package ru.nikeshkin.coroutinesdemo.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("products")
data class Product(
    @Id
    val id: Long? = null,
    val name: String,
    val price: BigDecimal
)

@Table("users")
data class User(
    @Id
    val id: Long? = null,
    val fullName: String,
    val address: String
)

@Table("orders")
data class Order(
    @Id
    val id: Long? = null,
    val clientFullName: String,
    val productName: String,
    val deliveryAddress: String
)