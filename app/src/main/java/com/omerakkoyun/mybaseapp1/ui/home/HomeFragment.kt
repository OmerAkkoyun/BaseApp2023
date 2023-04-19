package com.omerakkoyun.mybaseapp1.ui.home

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.omerakkoyun.mybaseapp1.base.BaseFragment
import com.omerakkoyun.mybaseapp1.databinding.FragmentHomeBinding
import com.omerakkoyun.mybaseapp1.models.cryptoResponse.Data
import com.omerakkoyun.mybaseapp1.utils.Constant.API_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    override val viewModel by viewModels<HomeViewModel>()


    override fun onCreate() {
        viewModel.getCoins(API_KEY, "18")
    }
    override fun onCreateFinished() {

    }

    override fun initializeListeners() {

    }

    override fun observeEvents() {
        with(viewModel) {

            // coins
            coins.observe(viewLifecycleOwner, Observer { response ->
                response?.let { res ->
                    setCoinsAdapterData(res.data)
                }
            })

            //loading
            isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
                binding.rvCoins.isVisible = !isLoading
                binding.progressBarHomeFragment.isVisible = isLoading
            })

            // error
            onError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show();
            })

        }
    }

    private fun setCoinsAdapterData(coinDataList: List<Data>) {

        val adapter = CoinRecyclerViewAdapter(object : ItemClickListener {
            override fun onItemClick(coin: Data) {
                // detay ekranına git
                val navigation = HomeFragmentDirections.actionHomeFragmentToCoinDetailsFragment(coin.symbol)
                Navigation.findNavController(requireView()).navigate(navigation)
            }
        })

        binding.rvCoins.adapter = adapter
        adapter.setCoinDataList(coinDataList)
    }


}