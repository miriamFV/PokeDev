package com.example.data.model.pokemon

import Platinum
import com.google.gson.annotations.SerializedName

data class Generation_iv (

    @SerializedName("diamond-pearl") val diamond_pearl : Diamond_pearl,
    @SerializedName("heartgold-soulsilver") val heartgold_soulsilver : Heartgold_soulsilver,
    @SerializedName("platinum") val platinum : Platinum
)