package com.example.pokedev.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.core.content.res.ResourcesCompat
import com.example.data.model.detail.PokemonDetailModel
import com.example.pokedev.R
import java.util.*


enum class Types{
    NORMAL, FIRE, WATER, ELECTRIC, GRASS, ICE, FIGHTING,
    POISON, GROUND, FLYING, PSYCHIC, BUG, ROCK,
    GHOST, DRAGON, DARK, STEEL, FAIRY
}


fun PokemonDetailModel.getTypeBackground(context:Context):Drawable{
    //Returns a background that will be a color gradient if the pokemon has two or more types
    // and a single color if it only has one type
    if(this.types.size > 1){
        var background = ResourcesCompat.getDrawable(context.resources, R.drawable.type_background_gradient, null) as GradientDrawable
        var colorList = IntArray(this.types.size) // Create an integer array for the color associated with the type
        this.types.forEachIndexed { index, types ->
            println("colorList forEach: $index")
            println("type: ${types.type.name}")
            colorList[index] = getTypeColor(context,types.type.name)
        }
        //Reverse color list to fit with order of types written in detail fragment
        background.colors = colorList.reversed().toIntArray()
        return background.mutate()

    }else{
        var background = ResourcesCompat.getDrawable(context.resources, R.drawable.type_background, null) as GradientDrawable
        background.setColor(getTypeColor(context,types[0].type.name))
        return background
    }
}


fun getTypeColor(context: Context, type:String):Int{
    //Returns color corresponding with pokemon type
    return when (type.uppercase(Locale.getDefault())){
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
