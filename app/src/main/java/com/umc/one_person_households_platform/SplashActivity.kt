package com.umc.one_person_households_platform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.umc.one_person_households_platform.view.login.LoginFirstActivity


class SplashActivity : AppCompatActivity() {
    private val SPLASH_DISPLAY_LENGTH = 3000 // Splash 화면 표시 시간 (3초)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


            // SPLASH_DISPLAY_LENGTH 시간 후에 LoginFirstActivity로 이동
            Handler().postDelayed({
                val intent = Intent(this, LoginFirstActivity::class.java)
                startActivity(intent)
                finish() // SplashActivity 종료
            }, SPLASH_DISPLAY_LENGTH.toLong())
        }
    }
