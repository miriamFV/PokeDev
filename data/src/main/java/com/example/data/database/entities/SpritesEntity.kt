package com.example.data.database.entities

import Other
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.model.pokemon.Sprites

@Entity(tableName = SpritesEntity.TABLE_NAME)
data class SpritesEntity (
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "back_default") val back_default : String,
    @ColumnInfo(name = "back_female") val back_female : String?,
    @ColumnInfo(name = "back_shiny") val back_shiny : String,
    @ColumnInfo(name = "back_shiny_female") val back_shiny_female : String?,
    @ColumnInfo(name = "front_default") val front_default : String,
    @ColumnInfo(name = "front_female") val front_female : String?,
    @ColumnInfo(name = "front_shiny") val front_shiny : String,
    @ColumnInfo(name = "front_shiny_female") val front_shiny_female : String?
){
    companion object TABLE{
        const val TABLE_NAME = "sprites_table"
        const val ID = "id"
        const val BACK_DEFAULT = "back_default"
        const val BACK_FEMALE = "back_female"
        const val BACK_SHINY = "back_shiny"
        const val BACK_SHINY_FEMALE = "back_shiny_female"
        const val FRONT_DEFAULT = "front_default"
        const val FRONT_FEMALE = "front_female"
        const val FRONT_SHINY = "front_shiny"
        const val FRONT_SHINY_FEMALE = "front_shiny_female"
    }
}

fun Sprites.toSpritesEntity(idPokemon : Int) = SpritesEntity(
    idPokemon,
    back_default,
    back_female,
    back_shiny,
    back_shiny_female,
    front_default,
    front_female,
    front_shiny,
    front_shiny_female
)