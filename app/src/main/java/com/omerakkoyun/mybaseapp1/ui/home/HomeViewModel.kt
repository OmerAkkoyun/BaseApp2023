package com.omerakkoyun.mybaseapp1.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omerakkoyun.mybaseapp1.models.NetworkResult
import com.omerakkoyun.mybaseapp1.models.cryptoResponse.CryptoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    val coins:MutableLiveData<CryptoResponse?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val onError: MutableLiveData<String?> = MutableLiveData()


    fun getCoins(apiKey:String,limit:String) = viewModelScope.launch {
        isLoading.value = true
       val request = homeRepository.getCoins(apiKey, limit)
        when(request){
            is NetworkResult.Success ->{
                coins.value = request.data
                isLoading.value = false
            }
            is NetworkResult.Error -> {
                onError.value = request.message
                isLoading.value = false

            }
        }
    }


}