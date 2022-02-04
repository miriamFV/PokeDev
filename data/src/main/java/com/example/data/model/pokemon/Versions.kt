package com.example.data.model.pokemon

import com.google.gson.annotations.SerializedName

data class Versions (

    @SerializedName("generation-i") val generation_i : Generation_i,
    @SerializedName("generation-ii") val generation_ii : Generation_ii,
    @SerializedName("generation-iii") val generation_iii : Generation_iii,
    @SerializedName("generation-iv") val generation_iv : Generation_iv,
    @SerializedName("generation-v") val generation_v : Generation_v,
    @SerializedName("generation-vi") val generation_vi : Generation_vi,
    @SerializedName("generation-vii") val generation_vii : Generation_vii,
    @SerializedName("generation-viii") val generation_viii : Generation_viii
)