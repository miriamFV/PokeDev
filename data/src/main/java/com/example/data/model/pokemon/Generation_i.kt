package com.example.data.model.pokemon

import Yellow
import com.google.gson.annotations.SerializedName

data class Generation_i (

    @SerializedName("red-blue") val red_blue : Red_blue,
    @SerializedName("yellow") val yellow : Yellow
)