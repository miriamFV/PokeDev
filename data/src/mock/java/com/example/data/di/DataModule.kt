package com.example.data.di

import androidx.room.Room
import com.example.data.BuildConfig
import com.example.data.database.AppDatabase
import com.example.data.remote.MockInterceptorPokemon
import com.example.data.remote.MockInterceptorPokemonSpecies
import com.example.data.remote.PokemonAPI
import com.example.data.remote.PokemonSpeciesAPI
import com.example.data.repository.PokemonRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

/*    single<OkHttpClient>{
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(MockInterceptorPokemon(get()))
            .addInterceptor(MockInterceptorPokemonSpecies(get()))
            .build()
    }*/

/*    single<OkHttpClient>{
        val pokemonInterceptor :Interceptor = MockInterceptorPokemon(get())
        val pokemonSpeciesInterceptor :Interceptor = MockInterceptorPokemonSpecies(get())
        val client = OkHttpClient.Builder()
        client.interceptors().add(pokemonInterceptor)
        client.interceptors().add(pokemonSpeciesInterceptor)
        client.build()
    }*/

    single<OkHttpClient>(named("pokemon")){
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(MockInterceptorPokemon(get()))
            .build()
    }

    single<OkHttpClient>(named("pokemonSpecies")){
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(MockInterceptorPokemonSpecies(get()))
            .build()
    }

    single<PokemonAPI>{
        Retrofit.Builder()
            .client(get(named("pokemon")))
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonAPI::class.java)
    }

    single<PokemonSpeciesAPI>{
        Retrofit.Builder()
            .client(get(named("pokemonSpecies")))
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonSpeciesAPI::class.java)
    }

    single<PokemonRepository>{
        PokemonRepository(get(),get(),get())
    }

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            com.example.data.BuildConfig.DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }
}