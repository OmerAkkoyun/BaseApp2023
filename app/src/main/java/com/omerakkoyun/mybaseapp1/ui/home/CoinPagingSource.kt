package com.omerakkoyun.mybaseapp1.ui.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.omerakkoyun.mybaseapp1.models.cryptoResponse.Data
import com.omerakkoyun.mybaseapp1.utils.Constant

/**
 * Created by Omer AKKOYUN on 5.07.2023.
 */
class CoinPagingSource (private val homeRepository: HomeRepository): PagingSource<Int,Data>() {

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        if (state.anchorPosition != null) {
            val anchorPage = state.closestPageToPosition(state.anchorPosition!!) // ->  mevcut pozisyona en yakın olan sayfayı bulmak için
            if (anchorPage != null) {
                return if (anchorPage.prevKey != null) {
                    anchorPage.prevKey?.plus(1) // -> anchorPage.nextKey + 1
                } else {
                    anchorPage.nextKey?.minus(1) // -> anchorPage.nextKey - 1
                }
            }
        }
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val pageNumber = params.key ?: 1
        return try {
            val data = homeRepository.getCoins(Constant.API_KEY,"10")
            val prevKey = if (pageNumber == 1) null else pageNumber - 1
            val nextKey = if (data.data?.data!!.isEmpty()) null else pageNumber + 1
            return LoadResult.Page(data.data.data, prevKey, nextKey)

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}