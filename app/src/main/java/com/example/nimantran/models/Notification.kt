package com.example.nimantran.models

import java.sql.Timestamp
import java.util.*

data class Notification(
    val subject: String = "",
    val body: String = "",
    val to: String = "",
    val date: Date = Timestamp(System.currentTimeMillis()),
    val id: String = UUID.randomUUID().toString(),
)
