package com.omerakkoyun.mybaseapp1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.omerakkoyun.mybaseapp1.models.NetworkResult
import com.omerakkoyun.mybaseapp1.models.cryptoResponse.CryptoResponse
import com.omerakkoyun.mybaseapp1.models.cryptoResponse.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    val coins:MutableLiveData<CryptoResponse?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val onError: MutableLiveData<String?> = MutableLiveData()
    var pagerLiveData: LiveData<PagingData<Data>> = MutableLiveData()


    // with live data
    fun getCoinTest()  {
       pagerLiveData =   Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {CoinPagingSource(homeRepository)})
            .liveData.cachedIn(viewModelScope)
    }

    // with flow
    fun getCoins(): Flow<PagingData<Data>> {
        return Pager(config = PagingConfig(pageSize = 20)) {
            CoinPagingSource(homeRepository)
        }.flow
    }


}