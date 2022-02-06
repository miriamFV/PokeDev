package com.example.pokedev.ui.favorite

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.database.entities.PokemonDetailEntity
import com.example.data.database.entities.toPokemonDetailEntity
import com.example.data.model.detail.PokemonDetailModel
import com.example.data.repository.PokemonRepository
import com.example.pokedev.common.BaseViewModel
import com.example.pokedev.common.NavData
import com.example.pokedev.ui.home.HomeViewModel
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val pokemonRepository : PokemonRepository
) : BaseViewModel() {

    companion object{
        const val NAV_DETAIL = 0
    }

    private val liveNoFavoritesToShow = MutableLiveData<Boolean>()
    val obsNoFavoritesToShow = liveNoFavoritesToShow

    private val livePokemonList : MutableLiveData <List<PokemonDetailModel>> = MutableLiveData()
    val obsPokemonList : LiveData<List<PokemonDetailModel>> = livePokemonList

    fun onInit(){
        showLoading(true)
        updateFavoriteList()
        showLoading(false)
    }

    fun onActionPokemonClicked(pokemonDetailModel: PokemonDetailModel){
        //Go to detail fragment
        navigate(NavData(NAV_DETAIL,pokemonDetailModel))
    }

    fun onActionOnItemSwiped(itemPosition: Int) {
        val pokemon = obsPokemonList.value?.get(itemPosition)
        pokemon?.also{ pokemon ->
            updateLikeDatabase(pokemon, false)
            updateFavoriteList()
        }
    }

    private fun updateLikeDatabase(pokemon: PokemonDetailModel, like: Boolean) {
        //Update pokemon like in database
        val pokemonDetailEntity : PokemonDetailEntity = (pokemon.copy(favorite = like)).toPokemonDetailEntity()

        viewModelScope.launch {
            //Save updated pokemon data in database
            pokemonRepository.savePokemonDatabase(pokemonDetailEntity)
        }
    }

    private fun updateFavoriteList(){
        //Update favorite pokemon list on screen
        viewModelScope.launch {
            val pokemonFavoriteListDatabase = pokemonRepository.getPokemonFavoriteListLocally()

            if (pokemonFavoriteListDatabase.isEmpty()){
                //Database is currently empty
                liveNoFavoritesToShow.value = true
            }else{
                //Database has data
                liveNoFavoritesToShow.value = false
                livePokemonList.value = pokemonFavoriteListDatabase
            }
        }
    }

}