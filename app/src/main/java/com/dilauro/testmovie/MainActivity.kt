package com.dilauro.testmovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.dilauro.testmovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        with(mBinding) {
            imageViewStartIcon.startAnimation(AnimationUtils.loadAnimation(this@MainActivity, R.anim.splash_in))
            Handler().postDelayed({
                imageViewStartIcon.startAnimation(AnimationUtils.loadAnimation(this@MainActivity, R.anim.splash_out))
                Handler().postDelayed({
                    startActivity(Intent(this@MainActivity, MoviesList::class.java))
                    finish()
                }, 500)
            }, 1500)
        }
    }
}