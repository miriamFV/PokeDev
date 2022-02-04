package com.example.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.model.detail.PokemonDetailModel

@Entity(tableName = PokemonDetailEntity.TABLE_NAME)
data class PokemonDetailEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "weight") val weight: Int,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "specie") val specie: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean
){
    companion object TABLE{
        const val TABLE_NAME = "pokemonDetail_table"
        const val ID = "id"
        const val NAME = "name"
        const val WEIGHT = "weight"
        const val HEIGHT = "height"
        const val SPECIE = "specie"
        const val DESCRIPTION = "description"
        const val FAVORITE = "favorite"
    }
}

fun PokemonDetailModel.toPokemonDetailEntity() = PokemonDetailEntity(
    id,
    name,
    weight,
    height,
    specie,
    description,
    favorite
)