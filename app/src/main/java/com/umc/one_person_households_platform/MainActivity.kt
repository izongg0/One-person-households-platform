package com.umc.one_person_households_platform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.umc.one_person_households_platform.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // navHost 찾기
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_container) as NavHostFragment
        // BottomNavigationView 에게 NavController 설정
        binding.bnvMoveFragment.setupWithNavController(navHostFragment.navController)
    }





}