package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.remote.PokemonAPI
import com.example.data.repository.PokemonRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import androidx.room.Room
import com.example.data.database.AppDatabase
import com.example.data.remote.PokemonSpeciesAPI
import com.example.data.repository.ProfileRepository
import org.koin.core.qualifier.named

val dataModule = module {

    single<OkHttpClient>{
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single<PokemonAPI>{
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonAPI::class.java)
    }

    single<PokemonSpeciesAPI>{
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonSpeciesAPI::class.java)
    }


    single<PokemonRepository>{
        PokemonRepository(get(), get(), get())
    }

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            com.example.data.BuildConfig.DB_NAME
        ).fallbackToDestructiveMigration()
        .build()
    }

    single{
        ProfileRepository(get())
    }
}