package com.example.nimantran.models.admin

import java.util.*

data class Client(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val phone: String? = "",
    val gender: String= "",
)
