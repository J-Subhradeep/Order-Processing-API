package com.order.api.configs

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TopicConfigurations {
    @Bean
    fun orderPlaced(): NewTopic{
        return NewTopic("order-placed", 3, 1.toShort())
    }
}