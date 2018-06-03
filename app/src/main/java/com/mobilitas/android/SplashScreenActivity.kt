package com.mobilitas.android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.util.*
import kotlin.concurrent.timerTask

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        // Hide Status and Actionbar
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        // Set up timer to open new Activity
        Timer().schedule(timerTask {
            startActivity(Intent(this@SplashScreenActivity, IntroActivity::class.java))
            this@SplashScreenActivity.finish()
        }, 3000)
    }
}
