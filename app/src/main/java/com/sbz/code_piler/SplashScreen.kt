package com.sbz.code_piler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import com.sbz.code_piler.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
//        setAnimation()
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
//        setAnimation()
    }

    private fun setAnimation(){
//        val mAnimation = AnimationUtils.loadAnimation(this@SplashScreen, R.anim.image_animation)
//        val fadeIn = AlphaAnimation(0f, 1f)
//        fadeIn.duration = 1000
//        binding.ivSplashScreen.startAnimation(mAnimation)
    }
}