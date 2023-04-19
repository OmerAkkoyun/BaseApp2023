package com.omerakkoyun.mybaseapp1.models.coinDetailsResponse

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("ETH")
    val eTH: List<CoinDetail>?
)