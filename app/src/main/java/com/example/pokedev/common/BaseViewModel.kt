package com.example.pokedev.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.atsistemas.formacion2022.common.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

    protected val liveShowLoading = MutableLiveData<Boolean>()
    val obsShowLoading : LiveData<Boolean> = liveShowLoading

    protected val liveNavigation = SingleLiveEvent<NavData?>()
    val obsNavigate = liveNavigation

    protected fun showLoading(show:Boolean){
        liveShowLoading.value = show
    }

    protected fun navigate(navData: NavData){
        liveNavigation.value = navData
    }

}