package com.example.data.database.entities

import Types
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = TypesEntity.TABLE_NAME, primaryKeys = ["id","slot"])
data class TypesEntity (
    @ColumnInfo(name = "id") val id: Int,
    @SerializedName("slot") val slot : Int,
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String
){
    companion object TABLE{
        const val TABLE_NAME = "types_table"
        const val ID = "id"
        const val SLOT = "slot"
        const val NAME = "name"
        const val URL = "url"
    }
}

fun Types.toTypesEntity(idPokemon : Int) = TypesEntity(
    idPokemon,
    slot,
    type.name,
    type.url
)
