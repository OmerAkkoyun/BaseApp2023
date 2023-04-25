package com.omerakkoyun.mybaseapp1.ui.home

import com.omerakkoyun.mybaseapp1.base.BaseRepository
import com.omerakkoyun.mybaseapp1.models.NetworkResult
import com.omerakkoyun.mybaseapp1.models.cryptoResponse.CryptoResponse
import com.omerakkoyun.mybaseapp1.network.ApiFactory
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiFactory: ApiFactory): BaseRepository(){

    suspend fun getCoins(apiKey:String, limit:String): NetworkResult<CryptoResponse>{
        return safeApiRequest {
            apiFactory.getCoins(apiKey,limit)
        }
        
    }

}