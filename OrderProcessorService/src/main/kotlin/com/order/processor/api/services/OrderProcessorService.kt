package com.order.processor.api.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.order.processor.api.dtos.Order
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class OrderProcessorService(private val objectMapper: ObjectMapper, private val orderStoreEmitterService: OrderStoreEmitterService) {

    companion object{
        var orders: ArrayList<Order> = arrayListOf();
    }

    @KafkaListener(topics = ["order-placed"], groupId = "order-client")
    fun listen(record: ConsumerRecord<String, String>) {
        val o: Order = objectMapper.readValue(record.value())
        orders.add(o)
        orderStoreEmitterService.storeOrder(o)
        println("Received: ${record.value()} | Partition: ${record.partition()} | Offset: ${record.offset()}")
    }

    fun orders(): ArrayList<Order>{
        return orders
    }
}