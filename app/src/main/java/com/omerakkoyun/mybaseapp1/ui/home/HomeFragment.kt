package com.omerakkoyun.mybaseapp1.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.omerakkoyun.mybaseapp1.base.BaseFragment
import com.omerakkoyun.mybaseapp1.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel> (FragmentHomeBinding::inflate) {

    override val viewModel by viewModels<HomeViewModel>()


    override fun onCreateFinished() {

    }

    override fun initializeListeners() {

    }

    override fun observeEvents() {

    }


}