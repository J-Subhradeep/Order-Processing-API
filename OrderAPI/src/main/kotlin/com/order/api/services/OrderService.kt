package com.order.api.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.order.api.dtos.Order
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import kotlin.random.Random
import kotlin.random.nextInt

@Service
class OrderService(private val kafkaTemplate: KafkaTemplate<String, String>, private val objectMapper: ObjectMapper) {
    fun placeOrder(order: Order): Boolean{
        try {
            kafkaTemplate.send("order-placed", Random.nextInt(3), order.customerId,  objectMapper.writeValueAsString(order))
            return true
        }
        catch (ex: Exception){
            return false;
        }

    }
}