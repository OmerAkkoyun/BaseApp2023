package com.omerakkoyun.mybaseapp1.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    lateinit var viewModel: VM

    // Inflate layout for the fragment
    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    // Initialize view model
    abstract fun initViewModel(): Class<VM>

    protected abstract fun onCreate()
    protected abstract fun onCreateFinished()
    protected abstract fun initializeListeners()
    protected abstract fun observeEvents()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = inflateBinding(inflater, container)
        viewModel = ViewModelProvider(this).get(initViewModel())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreateFinished()
        initializeListeners()
        observeEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}