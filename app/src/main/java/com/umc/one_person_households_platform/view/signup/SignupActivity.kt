package com.umc.one_person_households_platform.view.signup

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.umc.one_person_households_platform.R

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // 액티비티에서의 iv_back 이미지뷰 클릭 이벤트 설정
        val imageViewBack: ImageView = findViewById(R.id.iv_back)
        imageViewBack.setOnClickListener {
            goBack() // 뒤로가기 기능을 수행하는 함수 호출
        }
    }

    // 뒤로가기 기능을 수행하는 함수
    private fun goBack() {
        supportFragmentManager.popBackStack()
    }
}