package com.example.pokedev.di

import com.example.pokedev.ui.detail.DetailViewModel
import com.example.pokedev.ui.favorite.FavoriteViewModel
import com.example.pokedev.ui.home.HomeViewModel
import com.example.pokedev.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel {
        HomeViewModel(get(), get())
    }
    viewModel{
        DetailViewModel(get())
    }

    viewModel{
        FavoriteViewModel(get())
    }

    viewModel{
        ProfileViewModel(get())
    }
}