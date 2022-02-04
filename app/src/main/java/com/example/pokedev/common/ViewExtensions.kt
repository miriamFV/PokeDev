package com.example.pokedev.common

import android.text.TextWatcher
import android.widget.EditText

fun EditText.setText(text:String, watcher:TextWatcher){
    removeTextChangedListener(watcher)
    setText(text)
    setSelection(text.length)
    addTextChangedListener(watcher)
}