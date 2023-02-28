package com.example.nimantran.models

data class Gift(
    val item: String = "",
    val description: String = "",
    val image: String = "https://wabisabiproject.com/wp-content/uploads/woocommerce-placeholder.png",
    val price: Int = 0,
    val quantity: Int = 0,
)
