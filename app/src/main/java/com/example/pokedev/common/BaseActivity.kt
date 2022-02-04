package com.example.pokedev.common

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T:ViewBinding>(val bindingInflater: (LayoutInflater)->T) : AppCompatActivity(){

    protected lateinit var binding :T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)
    }

    protected fun <LD>observeData(liveData: LiveData<LD>, action:(LD)->Unit){
        liveData.observe(this){
            action.invoke(it)
        }
    }

}