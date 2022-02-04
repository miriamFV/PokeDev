package com.example.data.utils

import com.example.data.model.detail.PokemonDetailModel

object PokemonUtils {

    fun filterByFavorites(list : List<PokemonDetailModel>) : List<PokemonDetailModel>{
        val filteredList = mutableListOf<PokemonDetailModel>()
        list.forEach { pokemon ->
            if (pokemon.favorite) filteredList.add(pokemon)
        }
        return filteredList
    }

    fun orderByDescending(list : List<PokemonDetailModel>) : List<PokemonDetailModel>{
        return list.sortedByDescending { it.id }
    }

    fun getTypes(pokemon : PokemonDetailModel):String{
        var types : String = ""
        pokemon.types.forEach {
            if(types.length == 0)
                types += "${getSpanishType(it.type.name)} "
            else
                types += "/ ${getSpanishType(it.type.name)}"
        }
        return types
    }

    fun convertWeight(weight : Int):String{
        return (weight.toFloat() / 10).toString()
    }

    fun convertHeight(height : Int):String{
        return (height.toFloat() / 10).toString()
    }


    private val mapTypes : Map<String,String> = mapOf(
        "normal" to "Normal",
        "fire" to "Fuego",
        "water" to "Agua",
        "electric" to "Electrico",
        "grass" to "Planta",
        "ice" to "Hielo",
        "fighting" to "Lucha",
        "poison" to "Veneno",
        "ground" to "Tierra",
        "flying" to "Volador",
        "psychic" to "Psíquico",
        "bug" to "Bicho",
        "rock" to "Roca",
        "ghost" to "Fantasma",
        "dragon" to "Dragón",
        "dark" to "Siniestro",
        "steel" to "Acero",
        "fairy" to "Hada",
    )

    private fun getSpanishType(type: String):String{
        return mapTypes[type]?:""
    }


}