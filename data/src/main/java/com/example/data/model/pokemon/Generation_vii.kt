package com.example.data.model.pokemon

import Icons
import com.google.gson.annotations.SerializedName

data class Generation_vii (

    @SerializedName("icons") val icons : Icons,
    @SerializedName("ultra-sun-ultra-moon") val ultra_sun_ultra_moon : Ultra_sun_ultra_moon
)