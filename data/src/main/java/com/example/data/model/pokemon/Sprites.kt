package com.example.data.model.pokemon

import Other
import com.example.data.database.entities.SpritesEntity
import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2022 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

data class Sprites(
	@SerializedName("back_default") val back_default: String,
	@SerializedName("back_female") val back_female: String?,
	@SerializedName("back_shiny") val back_shiny: String,
	@SerializedName("back_shiny_female") val back_shiny_female: String?,
	@SerializedName("front_default") val front_default: String,
	@SerializedName("front_female") val front_female: String?,
	@SerializedName("front_shiny") val front_shiny: String,
	@SerializedName("front_shiny_female") val front_shiny_female: String?,
	@SerializedName("other") val other: Other?,
	@SerializedName("versions") val versions: Versions?
)

fun SpritesEntity.toSprites() = Sprites(
	back_default,
	back_female,
	back_shiny,
	back_shiny_female,
	front_default,
	front_female,
	front_shiny,
	front_shiny_female,
	null,
	null
)