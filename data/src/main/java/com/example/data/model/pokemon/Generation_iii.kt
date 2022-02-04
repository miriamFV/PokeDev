package com.example.data.model.pokemon

import Emerald
import com.google.gson.annotations.SerializedName

data class Generation_iii (

    @SerializedName("emerald") val emerald : Emerald,
    @SerializedName("firered-leafgreen") val firered_leafgreen : Firered_leafgreen,
    @SerializedName("ruby-sapphire") val ruby_sapphire : Ruby_sapphire
)