package ru.nikeshkin.coroutinesdemo.repository

import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import ru.nikeshkin.coroutinesdemo.domain.Order

@Repository
interface OrderRepository : R2dbcRepository<Order, Long>