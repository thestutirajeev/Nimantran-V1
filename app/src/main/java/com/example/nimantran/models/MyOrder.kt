package com.example.nimantran.models

import java.util.UUID

data class MyOrder(
    val id : String = UUID.randomUUID().toString(),
    //val card: MyCards = MyCards(),
)
