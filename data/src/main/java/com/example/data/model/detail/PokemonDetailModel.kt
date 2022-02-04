package com.example.data.model.detail

import Types
import com.example.data.database.entities.PokemonDetailEntity
import com.example.data.database.entities.SpritesEntity
import com.example.data.database.entities.TypesEntity
import com.example.data.model.pokemon.Sprites
import com.example.data.model.pokemon.toSprites
import com.google.gson.annotations.SerializedName
import toTypes
import java.io.Serializable

data class PokemonDetailModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("sprites") val sprites: Sprites,
    @SerializedName("specie") val specie: String,
    @SerializedName("types") val types: List<Types>,
    @SerializedName("description") val description: String,
    @SerializedName("favorite") val favorite: Boolean = false
) : Serializable

fun PokemonDetailEntity.toPokemonDetailModel(spriteEntity: SpritesEntity, typesEntityList: List<TypesEntity>) = PokemonDetailModel(
    id,
    name,
    weight,
    height,
    spriteEntity.toSprites(),
    specie,
    toListTypes(typesEntityList),
    description,
    favorite
)

fun toListTypes(typesEntityList: List<TypesEntity>) : List<Types>{
    val typesList : MutableList<Types> = arrayListOf()
    typesEntityList.forEach { typesEntity ->
        val types: Types = typesEntity.toTypes()
        typesList.add(types)
    }
    return typesList
}