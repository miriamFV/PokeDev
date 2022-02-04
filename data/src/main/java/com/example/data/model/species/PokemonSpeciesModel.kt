import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2022 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class PokemonSpeciesModel (

	@SerializedName("base_happiness") val base_happiness : Int,
	@SerializedName("capture_rate") val capture_rate : Int,
	@SerializedName("color") val color : Color,
	@SerializedName("egg_groups") val egg_groups : List<Egg_groups>,
	@SerializedName("evolution_chain") val evolution_chain : Evolution_chain,
	@SerializedName("evolves_from_species") val evolves_from_species : Evolves_from_species,
	@SerializedName("flavor_text_entries") val flavor_text_entries : List<Flavor_text_entries>,
	@SerializedName("form_descriptions") val form_descriptions : List<String>,
	@SerializedName("forms_switchable") val forms_switchable : Boolean,
	@SerializedName("gender_rate") val gender_rate : Int,
	@SerializedName("genera") val genera : List<Genera>,
	@SerializedName("generation") val generation : Generation,
	@SerializedName("growth_rate") val growth_rate : Growth_rate,
	@SerializedName("habitat") val habitat : Habitat,
	@SerializedName("has_gender_differences") val has_gender_differences : Boolean,
	@SerializedName("hatch_counter") val hatch_counter : Int,
	@SerializedName("id") val id : Int,
	@SerializedName("is_baby") val is_baby : Boolean,
	@SerializedName("is_legendary") val is_legendary : Boolean,
	@SerializedName("is_mythical") val is_mythical : Boolean,
	@SerializedName("name") val name : String,
	@SerializedName("names") val names : List<Names>,
	@SerializedName("order") val order : Int,
	@SerializedName("pal_park_encounters") val pal_park_encounters : List<Pal_park_encounters>,
	@SerializedName("pokedex_numbers") val pokedex_numbers : List<Pokedex_numbers>,
	@SerializedName("shape") val shape : Shape,
	@SerializedName("varieties") val varieties : List<Varieties>
)