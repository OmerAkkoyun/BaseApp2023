package com.omerakkoyun.mybaseapp1.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omerakkoyun.mybaseapp1.models.NetworkResult
import com.omerakkoyun.mybaseapp1.models.coinDetailsResponse.CoinDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(private val detailsRepository: DetailsRepository) : ViewModel() {

    val details:MutableLiveData<CoinDetailResponse?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val onError: MutableLiveData<String?> = MutableLiveData()


    fun getDetails(apiKey:String,symbol:String) = viewModelScope.launch {
        isLoading.value = true
       val request = detailsRepository.getCoinDetails(apiKey, symbol)
        when(request){
            is NetworkResult.Success ->{
                details.value = request.data
                isLoading.value = false
            }
            is NetworkResult.Error -> {
                onError.value = request.message
                isLoading.value = false

            }
        }
    }


}