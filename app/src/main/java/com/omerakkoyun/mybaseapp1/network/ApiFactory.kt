package com.omerakkoyun.mybaseapp1.network

import com.omerakkoyun.mybaseapp1.models.coinDetailsResponse.CoinDetailResponse
import com.omerakkoyun.mybaseapp1.models.cryptoResponse.CryptoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiFactory {

//https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?limit=10
    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getCoins(
        @Header("X-CMC_PRO_API_KEY") apiKey: String,
        @Query("limit") limit: String,
        @Query("start") start: String
    ): CryptoResponse



    //https://pro-api.coinmarketcap.com/v1/cryptocurrency/info?symbol=ETH
    @GET("v1/cryptocurrency/info")
    suspend fun getCoinDetails(
        @Header("X-CMC_PRO_API_KEY") apiKey: String,
        @Query("symbol") symbol: String
    ) : CoinDetailResponse



}//05ca2334-39e3-4cf9-bce0-35e5c368e375