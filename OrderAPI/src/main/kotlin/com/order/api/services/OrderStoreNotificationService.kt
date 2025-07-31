package com.order.api.services

import com.fasterxml.jackson.module.kotlin.readValue
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class OrderStoreNotificationService {
    @KafkaListener(topics = ["order-confirmed"], groupId = "order-store-client")
    fun listen(record: ConsumerRecord<String, String>) {
        println("Stored: ${record.value()} | Partition: ${record.partition()} | Offset: ${record.offset()}")
    }
}