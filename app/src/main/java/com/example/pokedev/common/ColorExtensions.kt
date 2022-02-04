package com.example.pokedev.common

import android.content.Context
import com.example.data.model.detail.PokemonDetailModel
import com.example.pokedev.R
import java.util.*


fun PokemonDetailModel.getColor(context: Context):Int{
    return when (this.types[0].type.name.uppercase(Locale.getDefault())){
        Types.NORMAL.name -> context.getColor(R.color.type_normal)
        Types.FIRE.name -> context.getColor(R.color.type_fire)
        Types.WATER.name -> context.getColor(R.color.type_water)
        Types.ELECTRIC.name -> context.getColor(R.color.type_electric)
        Types.GRASS.name -> context.getColor(R.color.type_grass)
        Types.ICE.name -> context.getColor(R.color.type_ice)
        Types.FIGHTING.name -> context.getColor(R.color.type_fighting)
        Types.POISON.name -> context.getColor(R.color.type_poison)
        Types.GROUND.name -> context.getColor(R.color.type_ground)
        Types.FLYING.name -> context.getColor(R.color.type_flying)
        Types.PSYCHIC.name -> context.getColor(R.color.type_psychic)
        Types.BUG.name -> context.getColor(R.color.type_bug)
        Types.ROCK.name -> context.getColor(R.color.type_rock)
        Types.GHOST.name -> context.getColor(R.color.type_ghost)
        Types.DRAGON.name -> context.getColor(R.color.type_dragon)
        Types.DARK.name -> context.getColor(R.color.type_dark)
        Types.STEEL.name -> context.getColor(R.color.type_steel)
        Types.FAIRY.name -> context.getColor(R.color.type_fairy)
        else -> context.getColor(R.color.secondary_text)
    }
}

enum class Types{
    NORMAL, FIRE, WATER, ELECTRIC, GRASS, ICE, FIGHTING,
    POISON, GROUND, FLYING, PSYCHIC, BUG, ROCK,
    GHOST, DRAGON, DARK, STEEL, FAIRY
}
