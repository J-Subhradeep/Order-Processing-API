package com.order.processor.api.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.order.processor.api.dtos.Order
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class OrderStoreEmitterService(private val kafkaTemplate: KafkaTemplate<String, String>, private val objectMapper: ObjectMapper) {
    fun storeOrder(order: Order): Boolean {
        try {
            kafkaTemplate.send(
                "order-confirmed",
                Random.nextInt(3),
                order.customerId,
                objectMapper.writeValueAsString(order)
            )
            return true
        } catch (ex: Exception) {
            return false;
        }

    }
}