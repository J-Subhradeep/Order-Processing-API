package com.order.api.controllers

import com.order.api.dtos.Order
import com.order.api.services.OrderService
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(private val orderService: OrderService) {

    @PostMapping("/orders")
    fun placeOrder(@RequestBody order: Order): ResponseEntity<String> {

        val placed:Boolean = orderService.placeOrder(order)

        return if (placed)
            ResponseEntity("Order Placed Successfully $order", HttpStatus.ACCEPTED)
        else ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}