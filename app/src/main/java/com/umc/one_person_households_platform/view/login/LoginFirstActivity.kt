package com.umc.one_person_households_platform.view.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.databinding.ActivityLoginFirstBinding

class LoginFirstActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var binding: ActivityLoginFirstBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 데이터 바인딩 인스턴스 생성
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_login_first)

        // SharedPreferences 초기화
        sharedPreferences = getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val checkBox = binding.checkBox

        // 이전에 로그인 상태를 기억한 경우 체크박스 상태 설정
        checkBox.isChecked = isLoggedIn()

        binding.editText3.setOnClickListener {
            // 로그인 처리를 수행
            // 알림창 추가 예정

            if (checkBox.isChecked) {
                // 로그인 상태를 SharedPreferences에 저장
                editor.putBoolean("isLoggedIn", true)
                editor.apply()
            }

            navigateToMainActivity()
        }

        // 네비게이션 컴포넌트 초기화
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph_login) as NavHostFragment
        navController = navHostFragment.navController

        binding.signshipt.setOnClickListener {
            navController.navigate(R.id.action_LoginFirstActivity_to_signupActivity)
        }

        binding.findid.setOnClickListener {
            navController.navigate(R.id.action_LoginFirstActivity_to_loginFindIdFragment)
        }

        binding.findpassword.setOnClickListener {
            navController.navigate(R.id.action_LoginFirstActivity_to_loginFindPasswordFragment)
        }
    }

    private fun isLoggedIn(): Boolean {
        // SharedPreferences에서 로그인 상태를 가져옴 (기본값은 false)
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    private fun navigateToMainActivity() {
        // MainActivity로 이동하는 로직 추가 예정
    }
}


