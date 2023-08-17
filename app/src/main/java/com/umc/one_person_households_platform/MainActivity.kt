package com.umc.one_person_households_platform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.umc.one_person_households_platform.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // navHost 찾기
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_container) as NavHostFragment
        val navController = navHostFragment.findNavController()

        // BottomNavigationView 에게 NavController 설정
        binding.bnvMoveFragment.setupWithNavController(navController)

        // 특정 fragment 들을 제외한 나머지 fragment 들의 bottom navigation bar 숨김 처리
        navController.addOnDestinationChangedListener { _, view, _ ->
            if (view.id == R.id.homeFragment || view.id == R.id.communityFragment ||
                view.id == R.id.groupBuyingFragment || view.id == R.id.recipemainFragment) {
                binding.bnvMoveFragment.visibility = View.VISIBLE
            }else {
                binding.bnvMoveFragment.visibility = View.GONE
            }
        }
    }
}