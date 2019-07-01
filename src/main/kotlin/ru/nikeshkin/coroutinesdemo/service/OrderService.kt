package ru.nikeshkin.coroutinesdemo.service

import kotlinx.coroutines.*
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Service
import ru.nikeshkin.coroutinesdemo.client.ProductApi
import ru.nikeshkin.coroutinesdemo.client.UserApi
import ru.nikeshkin.coroutinesdemo.domain.Order
import ru.nikeshkin.coroutinesdemo.domain.Product
import ru.nikeshkin.coroutinesdemo.domain.User
import ru.nikeshkin.coroutinesdemo.dto.PlaceOrderRequest
import ru.nikeshkin.coroutinesdemo.repository.OrderRepository

@Service
class OrderService(
    private val userApiClient: UserApi,
    private val productApiClient: ProductApi,
    private val orderRepository: OrderRepository
) {

    suspend fun placeOrderSequential(placeOrderRequest: PlaceOrderRequest): Order {
        val product = productApiClient.getProductById(placeOrderRequest.productId)
        val user = userApiClient.getUserById(placeOrderRequest.userId)

        val newOrder = Order(
            clientFullName = user.fullName,
            productName = product.name,
            deliveryAddress = placeOrderRequest.deliveryAddress
        )

        return orderRepository.save(newOrder).awaitSingle()
    }

    suspend fun placeOrderParallel(placeOrderRequest: PlaceOrderRequest): Order = coroutineScope {
        val product = async { productApiClient.getProductById(placeOrderRequest.productId) }
        val user = async { userApiClient.getUserById(placeOrderRequest.userId) }

        val newOrder = Order(
            clientFullName = user.await().fullName,
            productName = product.await().name,
            deliveryAddress = placeOrderRequest.deliveryAddress
        )

        orderRepository.save(newOrder).awaitSingle()
    }

    fun placeOrderBlockingSequential(placeOrderRequest: PlaceOrderRequest): Order = runBlocking {
        val product = getProductById(placeOrderRequest.productId)
        val user = getUserById(placeOrderRequest.userId)

        Order(
            clientFullName = user.fullName,
            productName = product.name,
            deliveryAddress = placeOrderRequest.deliveryAddress
        )
    }

    private suspend fun getProductById(productId: Long): Product = withContext(Dispatchers.IO) {
        productApiClient.getProductByIdBlocking(productId)
    }

    private suspend fun getUserById(userId: Long): User = withContext(Dispatchers.IO) {
        userApiClient.getUserByIdBlocking(userId)
    }

    fun placeOrderBlockingParallel(placeOrderRequest: PlaceOrderRequest): Order = runBlocking {
        val product = async(Dispatchers.IO) { productApiClient.getProductByIdBlocking(placeOrderRequest.productId) }
        val user = async(Dispatchers.IO) { userApiClient.getUserByIdBlocking(placeOrderRequest.userId) }

        Order(
            clientFullName = user.await().fullName,
            productName = product.await().name,
            deliveryAddress = placeOrderRequest.deliveryAddress
        )
    }
}