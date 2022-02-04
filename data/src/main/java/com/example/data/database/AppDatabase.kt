package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.entities.PokemonDetailEntity
import com.example.data.database.entities.SpritesEntity
import com.example.data.database.entities.TypesEntity
import com.example.data.model.detail.PokemonDetailModel

@Database(
    entities = [
        PokemonDetailEntity::class,
        SpritesEntity::class,
        TypesEntity::class
    ], version = 1
)
abstract class AppDatabase :RoomDatabase() {

    abstract fun pokemonDao(): PokemonDAO
}