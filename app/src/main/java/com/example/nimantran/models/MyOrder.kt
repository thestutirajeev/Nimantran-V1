package com.example.nimantran.models

import java.util.Date
import java.util.UUID

data class MyOrder(
    val id: String = UUID.randomUUID().toString(),
    val orderDate: Date = Date(),
    val orderStatus: String = "",
    val orderTitle: String = "",
    val orderImageSrc: String = "",
    val card: MyCards = MyCards(),
    val guest: List<Guest> //= listOf(Guest()),
)
