package com.example.data.database

import PokemonModel
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.entities.PokemonDetailEntity
import com.example.data.database.entities.SpritesEntity
import com.example.data.database.entities.TypesEntity
import com.example.data.model.detail.PokemonDetailModel

@Dao
interface PokemonDAO {

    //PokemonDetailEntity
    @Query("SELECT * FROM ${PokemonDetailEntity.TABLE_NAME}")
    fun getPokemonListDetail():List<PokemonDetailEntity>

    @Query("SELECT * FROM ${PokemonDetailEntity.TABLE_NAME} WHERE ${PokemonDetailEntity.ID} = :idPokemon")
    fun getPokemonDetail(idPokemon: Int):PokemonDetailEntity

    @Query("SELECT * FROM ${PokemonDetailEntity.TABLE_NAME} WHERE ${PokemonDetailEntity.FAVORITE} = 1")
    fun getPokemonFavoriteListDetail():List<PokemonDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemonDetail(vararg pokemon : PokemonDetailEntity)

    @Query("DELETE FROM ${PokemonDetailEntity.TABLE_NAME}")
    suspend fun deletePokemonDetail()


    //SpritesEntity
    @Query("SELECT * FROM ${SpritesEntity.TABLE_NAME} WHERE ${SpritesEntity.ID} = :idPokemon")
    fun getPokemonSprites(idPokemon: Int):SpritesEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemonSprites(vararg sprites : SpritesEntity)


    //TypesEntity
    @Query("SELECT * FROM ${TypesEntity.TABLE_NAME} WHERE ${TypesEntity.ID} = :idPokemon")
    fun getPokemonTypes(idPokemon: Int):List<TypesEntity>

    @Query("SELECT * FROM ${TypesEntity.TABLE_NAME} WHERE ${TypesEntity.ID} = :idPokemon AND ${TypesEntity.SLOT} = :slot")
    suspend fun getPokemonType(idPokemon: Int, slot: Int):TypesEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemonTypes(vararg types : TypesEntity)
}