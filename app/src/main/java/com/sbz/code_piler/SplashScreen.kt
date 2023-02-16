package com.sbz.code_piler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
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
        hideNavAndStatusBar()
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
//        setAnimation()
    }

    @Suppress("DEPRECATION")
    fun hideNavAndStatusBar(){
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    private fun setAnimation(){
//        val mAnimation = AnimationUtils.loadAnimation(this@SplashScreen, R.anim.image_animation)
        val fadeIn = AlphaAnimation(1f, 0f)
        fadeIn.duration = 2600
        binding.ivSplashScreen.startAnimation(fadeIn)
        binding.tvSplashScreen.startAnimation(fadeIn)
    }
}