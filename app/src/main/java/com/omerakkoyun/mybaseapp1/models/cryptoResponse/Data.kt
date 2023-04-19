package com.omerakkoyun.mybaseapp1.models.cryptoResponse

data class Data(
    val circulating_supply: Double,
    val date_added: String,
    val id: Int,
    val infinite_supply: Boolean,
    val last_updated: String,
    val max_supply: Long,
    val name: String,
    val platform: Platform,
    val quote: Quote,
    val self_reported_circulating_supply: Any,
    val self_reported_market_cap: Any,
    val slug: String,
    val symbol: String,
    val tags: List<String>,
    val total_supply: Double,
    val tvl_ratio: Any
)