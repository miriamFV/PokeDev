package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.remote.MockInterceptorPokemon
import com.example.data.remote.PokemonAPI
import com.example.data.repository.PokemonRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    single<OkHttpClient>{
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(MockInterceptorPokemon(get()))
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

    single<PokemonRepository>{
        PokemonRepository(get(),get())
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