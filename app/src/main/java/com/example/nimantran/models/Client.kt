package com.example.nimantran.models

import java.util.*

data class Client(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val email: String?,
    val phone: String?,
    val gender: String,
)
