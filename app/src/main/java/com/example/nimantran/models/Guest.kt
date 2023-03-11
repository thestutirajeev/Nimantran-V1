package com.example.nimantran.models

import java.util.*

data class Guest(
    val name: String = "",
    val phone: String = "",
    val address: String = "",
    val id: String = UUID.randomUUID().toString(),
)
