package com.example.pokedev.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import com.example.pokedev.R
import com.example.pokedev.common.BaseActivity
import com.example.pokedev.databinding.ActivitySplashBinding
import com.example.pokedev.ui.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Animation
        val logoAnimation : Animation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.animation_scroll_down)
        binding.ivActivitySplashLogo.animation = logoAnimation

        lifecycleScope.launch {
            delay(3500)
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
            finish()
        }
    }
}