package com.omerakkoyun.mybaseapp1.models.cryptoResponse

data class Platform(
    val id: Int,
    val name: String,
    val slug: String,
    val symbol: String,
    val token_address: String
)