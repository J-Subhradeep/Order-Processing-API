package com.order.processor.api.configs

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TopicConfigurations {
    @Bean
    fun orderPlaced(): NewTopic{
        return NewTopic("order-confirmed", 3, 1.toShort())
    }
}