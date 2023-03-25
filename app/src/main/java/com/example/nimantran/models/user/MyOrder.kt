package com.example.nimantran.models.user

import com.example.nimantran.models.user.Guest
import com.example.nimantran.models.user.MyCards
import java.util.Date
import java.util.UUID

data class MyOrder(
    val id: String = UUID.randomUUID().toString(),
    val orderDate: Date = Date(),
    //timestamp
    val orderStatus: String = "",
    val orderTitle: String = "",
    //<gift,qty>
    //total amt
    val orderImageSrc: String = "",
    val card: MyCards = MyCards(),
    val guest: List<Guest> = emptyList(),//= listOf(Guest()),
    val paymentRefId: String = "",
)
