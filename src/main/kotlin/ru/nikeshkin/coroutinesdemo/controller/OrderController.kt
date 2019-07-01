package ru.nikeshkin.coroutinesdemo.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.nikeshkin.coroutinesdemo.domain.Order
import ru.nikeshkin.coroutinesdemo.dto.PlaceOrderRequest
import ru.nikeshkin.coroutinesdemo.service.OrderService

@RestController
@RequestMapping("/orders")
class OrderController(private val orderService: OrderService) {

    @PostMapping("/parallel")
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun placeOrderParallel(@RequestBody placeOrderRequest: PlaceOrderRequest): Order {
        return orderService.placeOrderParallel(placeOrderRequest)
    }

    @PostMapping("/sequential")
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun placeOrderSequential(@RequestBody placeOrderRequest: PlaceOrderRequest): Order {
        return orderService.placeOrderSequential(placeOrderRequest)
    }
}