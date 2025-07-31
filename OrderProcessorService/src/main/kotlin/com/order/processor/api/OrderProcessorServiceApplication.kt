package com.order.processor.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OrderProcessorServiceApplication

fun main(args: Array<String>) {
	runApplication<OrderProcessorServiceApplication>(*args)
}
