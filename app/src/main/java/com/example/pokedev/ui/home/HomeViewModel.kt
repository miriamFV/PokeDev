package com.example.pokedev.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.model.detail.PokemonDetailModel
import com.example.data.repository.PokemonRepository
import com.example.pokedev.common.BaseViewModel
import com.example.pokedev.common.NavData
import com.example.pokedev.common.Network
import kotlinx.coroutines.launch

class HomeViewModel(
    private val pokemonRepository : PokemonRepository,
    private val context: Context
) : BaseViewModel() {

    companion object{
        const val NAV_DETAIL = 0
    }

    private val LIM_INF = 1
    private val LIM_SUP = 80


    private val liveNoDataToShow = MutableLiveData<Boolean>()
    val obsNoDataToShow = liveNoDataToShow

    private val livePokemonList by lazy { MutableLiveData <List<PokemonDetailModel>>() }
    val obsPokemonList : LiveData<List<PokemonDetailModel>> = livePokemonList


    fun onInit(){
        showLoading(true)
        viewModelScope.launch {

            val pokemonListDatabase = pokemonRepository.getPokemonListLocally()

            if (Network.checkConnectivity(context)) {
                //Internet is working

                if (pokemonListDatabase.isEmpty()){
                    //Database is currently empty
                    liveNoDataToShow.value = false
                    livePokemonList.value = pokemonRepository.getPokemonList(LIM_INF,LIM_SUP)
                }else{
                    //Database has data
                    liveNoDataToShow.value = false
                    livePokemonList.value = pokemonListDatabase
                }

            }else{
                //No internet connectivity

                if (pokemonListDatabase.isEmpty()){
                    //Database is currently empty
                    liveNoDataToShow.value = true
                }else{
                    //Database has data
                    liveNoDataToShow.value = false
                    livePokemonList.value = pokemonListDatabase
                }
            }
            showLoading(false)
        }
    }

    fun onActionPokemonClicked(pokemonDetailModel: PokemonDetailModel){
        //Go to detail fragment
        navigate(NavData(NAV_DETAIL,pokemonDetailModel))
    }


}