package com.example.nimantran.models

import java.sql.Timestamp

data class Notification(
    val subject: String = "",
    val body: String = "",
    val to: String = "",
    val date: Timestamp = Timestamp(System.currentTimeMillis()),
)
