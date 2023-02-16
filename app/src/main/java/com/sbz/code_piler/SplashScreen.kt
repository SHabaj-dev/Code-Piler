package com.sbz.code_piler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.sbz.code_piler.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        setAnimation()
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2500)
//        setAnimation()
    }

    private fun setAnimation(){
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 2000
        binding.ivSplashScreen.startAnimation(fadeIn)
    }
}