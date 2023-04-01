package com.example.nimantran.models.user

import com.example.nimantran.models.admin.Gift
import com.example.nimantran.models.user.Guest
import com.example.nimantran.models.user.MyCards
import java.util.Date
import java.util.UUID

data class MyOrder(
    val id: String = UUID.randomUUID().toString(),
    val orderDate: Date = Date(),
    //timestamp
    val orderStatus: String = "",
    //<gift,qty>
    //total amt
    //val card: MyCards = MyCards(),
    val gift: Gift = Gift(),
    val guest: List<Guest> = emptyList(),//= listOf(Guest()),
    val paymentRefId: String = "",
)
