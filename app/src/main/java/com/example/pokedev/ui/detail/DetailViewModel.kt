package com.example.pokedev.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.database.entities.PokemonDetailEntity
import com.example.data.database.entities.toPokemonDetailEntity
import com.example.data.model.detail.PokemonDetailModel
import com.example.data.repository.PokemonRepository
import com.example.pokedev.common.BaseViewModel
import kotlinx.coroutines.launch

class DetailViewModel(private val pokemonRepository : PokemonRepository) : BaseViewModel() {

    private val livePokemonDetail = MutableLiveData<PokemonDetailModel>()
    val obsPokemonDetail = livePokemonDetail


    fun onInit(idPokemon: Int) {
        //Pokemon data is retrieved from the database by id
        viewModelScope.launch {
            livePokemonDetail.value = pokemonRepository.getPokemonLocally(idPokemon)
        }
    }

    fun onActionLikeClicked() {

        //Recover the value of liveData
        val pokemonDetailModel : PokemonDetailModel = livePokemonDetail.value!!

        //A pokemonDetailEntity is created with the updated "favorite" value to save to the database
        val pokemonDetailEntity : PokemonDetailEntity = (pokemonDetailModel.copy(favorite = !pokemonDetailModel.favorite)).toPokemonDetailEntity()

        viewModelScope.launch {
            //Save updated pokemon data in database
            pokemonRepository.savePokemonDatabase(pokemonDetailEntity)
            //Pokemon data is retrieved from the database (with the favorite field updated) and it is updated in the view
            livePokemonDetail.value = pokemonRepository.getPokemonLocally(pokemonDetailModel.id)
        }
    }


}