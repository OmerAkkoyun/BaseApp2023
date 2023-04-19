package com.omerakkoyun.mybaseapp1.ui.details


import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.omerakkoyun.mybaseapp1.base.BaseFragment
import com.omerakkoyun.mybaseapp1.databinding.FragmentCoinDetailsBinding
import com.omerakkoyun.mybaseapp1.models.coinDetailsResponse.CoinDetail
import com.omerakkoyun.mybaseapp1.models.coinDetailsResponse.CoinDetailResponse
import com.omerakkoyun.mybaseapp1.utils.Constant.API_KEY
import com.omerakkoyun.mybaseapp1.utils.loadImageFromID
import com.omerakkoyun.mybaseapp1.utils.loadImageFromURL
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray
import org.json.JSONObject

@AndroidEntryPoint
class CoinDetailsFragment : BaseFragment<FragmentCoinDetailsBinding,CoinDetailsViewModel> (FragmentCoinDetailsBinding::inflate) {


    override val viewModel by viewModels<CoinDetailsViewModel>()
    private val args by navArgs<CoinDetailsFragmentArgs>()


    override fun onCreate() {

    }

    override fun onCreateFinished() {
        viewModel.getDetails(API_KEY,args.symbol)
    }

    override fun initializeListeners() {

    }

    override fun observeEvents() {
        with(viewModel) {

            // coins
            details.observe(viewLifecycleOwner, Observer { response ->
                response?.let { res ->
                    parseData(res)
                }
            })

            //loading
            isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
                binding.progressBarDetailFragment.isVisible = isLoading
            })

            // error
            onError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show(); // error mesajlar base clasa taşınabilir, update
            })

        }

    }

    private fun parseData(it:CoinDetailResponse?){
        val asadas = it
      //  val coin =  it?.data as CoinDetail
       // val coinName = coin.name


       val gson = Gson();
        val json = gson.toJson(it?.data)
        val jsonObject = JSONObject(json)
        val coinJsonModel = jsonObject[args.symbol]

        val coin = gson.fromJson(coinJsonModel.toString(),CoinDetail::class.java)
        coin.let {
            with(binding){
                titleTextDetailFragment.text = it.name
                descriptionTextDetailFragment.text = it.description
                imageViewDetailFragment.loadImageFromURL(it.logo)
                symbolTextDetailFragment.text = it.symbol
            }

        }
    }

}