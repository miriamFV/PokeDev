package com.example.data.model.pokemon

import com.google.gson.annotations.SerializedName

class Red_blue (

    @SerializedName("back_default") val back_default : String,
    @SerializedName("back_gray") val back_gray : String,
    @SerializedName("back_transparent") val back_transparent : String,
    @SerializedName("front_default") val front_default : String,
    @SerializedName("front_gray") val front_gray : String,
    @SerializedName("front_transparent") val front_transparent : String
)