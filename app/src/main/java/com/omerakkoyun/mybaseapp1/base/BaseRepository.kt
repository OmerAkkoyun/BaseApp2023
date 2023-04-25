package com.omerakkoyun.mybaseapp1.base

import com.google.gson.Gson
import com.omerakkoyun.mybaseapp1.R
import com.omerakkoyun.mybaseapp1.di.CryptoApp.Companion.getAppContext
import com.omerakkoyun.mybaseapp1.models.errorResponse.ErrorResponse
import com.omerakkoyun.mybaseapp1.models.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApiRequest(apiRequest: suspend () -> T): NetworkResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                // istek atip deniyoruz. -> Hata olusursa catch'e düsecek.
                NetworkResult.Success(apiRequest.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        // Http hatalariysa eger (404,503 ..)
                        NetworkResult.Error(false, errorBodyParser(throwable.response()?.errorBody()?.string()))
                    }
                    // internet baglantisi hatasi
                    else -> NetworkResult.Error(true, "Lütfen İnternet Bağlantınızı Kontrol Edin")
                }
            }
        }
    }


    private fun errorBodyParser(error: String?): String {
        error?.let {
            return try {
                val errorResponse = Gson().fromJson(error, ErrorResponse::class.java)
                val errorMessage = errorResponse.status?.errorMessage
                errorMessage ?: getAppContext().resources.getString(R.string.error_message)
            } catch (e: Exception) {
                getAppContext().resources.getString(R.string.error_message)
            }
        }
        return getAppContext().resources.getString(R.string.error_message)
    }
}