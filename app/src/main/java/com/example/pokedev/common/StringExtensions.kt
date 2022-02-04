package com.example.pokedev.common

import android.content.Context
import java.util.*


/**
 * Created by Carlos Mateo Benito on 19/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
fun Int.getString(context: Context, vararg arg:Any?):String{
    return String.format(context.resources.getString(this),*arg)
}

fun String.capitalize():String{
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
}