package com.example.data.model.pokemon

import Crystal
import Gold
import Silver
import com.google.gson.annotations.SerializedName

data class Generation_ii (

    @SerializedName("crystal") val crystal : Crystal,
    @SerializedName("gold") val gold : Gold,
    @SerializedName("silver") val silver : Silver
)