package com.order.processor.api.controllers

import com.order.processor.api.dtos.Order
import com.order.processor.api.dtos.ReceivedOrder
import com.order.processor.api.services.OrderProcessorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

@RestController
class OrderProcessorController(private val orderProcessorService: OrderProcessorService) {

    @GetMapping("/orders")
    fun getAllOrders(): ResponseEntity<List<ReceivedOrder>>{
        return ResponseEntity(orderProcessorService.orders(), HttpStatus.OK)
    }

    @GetMapping("/orders", params = ["customerId"])
    fun getOrdersByCustomerId(@RequestParam customerId:String): ResponseEntity<List<ReceivedOrder>>{
        return ResponseEntity(orderProcessorService.getOrderByCustomerID(customerId), HttpStatus.OK)
    }
}