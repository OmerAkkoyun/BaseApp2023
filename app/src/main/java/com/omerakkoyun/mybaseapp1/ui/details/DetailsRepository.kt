package com.omerakkoyun.mybaseapp1.ui.details

import com.omerakkoyun.mybaseapp1.base.BaseRepository
import com.omerakkoyun.mybaseapp1.network.ApiFactory
import javax.inject.Inject

class DetailsRepository @Inject constructor(private val apiFactory: ApiFactory): BaseRepository(){

    suspend fun getCoinDetails(apiKey:String, symbol:String) = safeApiRequest {
            apiFactory.getCoinDetails(apiKey,symbol)
    }

}