package com.omerakkoyun.mybaseapp1.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB:ViewBinding, VM:ViewModel> (private val bindingInflater:(inflater:LayoutInflater) -> VB) : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB get() = _binding as VB // protected: sadece bu sınıfı kullanan yerden binding çağrılabilir.

    protected abstract val viewModel : VM
    protected abstract fun onCreateFinished()
    protected abstract fun initializeListeners()
    protected abstract fun observeEvents()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       _binding = bindingInflater.invoke(inflater)

        if (_binding == null){
            throw java.lang.IllegalArgumentException("Binding null")
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreateFinished()
        initializeListeners()
        observeEvents()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null // for memory best practice
    }
}