package com.order.processor.api.dtos

data class ReceivedOrder(val orderId:String, val product:String, val quantity:Int, val customerId:String, val status:String )