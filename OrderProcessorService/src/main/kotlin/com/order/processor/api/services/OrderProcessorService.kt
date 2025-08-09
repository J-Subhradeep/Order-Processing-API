package com.order.processor.api.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.order.processor.api.dtos.Order
import com.order.processor.api.dtos.ReceivedOrder
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.network.Receive
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Service
class OrderProcessorService(private val objectMapper: ObjectMapper, private val orderStoreEmitterService: OrderStoreEmitterService) {

    companion object{
        var orders: ConcurrentHashMap<String, ArrayList<ReceivedOrder>> = ConcurrentHashMap<String, ArrayList<ReceivedOrder>>();
    }

    @KafkaListener(topics = ["order-placed"], groupId = "order-client")
    fun listen(record: ConsumerRecord<String, String>) {
        val o: Order = objectMapper.readValue(record.value())
        val receivedOrder: ReceivedOrder = ReceivedOrder(UUID.randomUUID().toString(), o.product, o.quantity, o.customerId, "RECEIVED")
        if (orders.get(o.customerId)==null) {


            orders.put(o.customerId, ArrayList<ReceivedOrder>())

        }
        else{
            val get: ArrayList<ReceivedOrder> = orders.get(o.customerId)!!
            get.add(receivedOrder)
            orders.put(o.customerId, get )
        }
        orderStoreEmitterService.storeOrder(receivedOrder)
        println("Received: ${record.value()} | Partition: ${record.partition()} | Offset: ${record.offset()}")
    }

    fun orders(): List<ReceivedOrder> {
        return orders.values.flatten();
    }

    fun getOrderByCustomerID(customerId: String): List<ReceivedOrder> {
        return orders.get(customerId).orEmpty()
    }
}