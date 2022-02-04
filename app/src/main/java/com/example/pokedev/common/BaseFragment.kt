package com.example.pokedev.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import com.example.pokedev.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseFragment<T:ViewBinding, VM:BaseViewModel> : Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!
    protected abstract val vm : VM


    abstract fun provideBinding(inflater: LayoutInflater, container: ViewGroup?):T


    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = provideBinding(inflater, container)
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData(vm.obsShowLoading,::onObserveLoading)
        observeData(vm.obsNavigate,::onObserveNavigation)
    }

    private fun onObserveLoading(show:Boolean){
        (requireActivity() as? MainActivity)?.showLoading(show)
    }

    protected open fun onObserveNavigation(navData: NavData?) {
        navData?.also {
            onNavigate(it)
        }
    }

    protected open fun onNavigate(navData:NavData){}

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun <LD>observeData(liveData: LiveData<LD>, action:(LD)->Unit){
        liveData.observe(viewLifecycleOwner){
            action.invoke(it)
        }
    }

}