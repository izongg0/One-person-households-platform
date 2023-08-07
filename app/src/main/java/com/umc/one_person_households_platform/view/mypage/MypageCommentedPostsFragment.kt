package com.umc.one_person_households_platform.view.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.one_person_households_platform.databinding.FragmentMypageCommentedPostsBinding

class MypageCommentedPostsFragment : Fragment() {

    private var _binding: FragmentMypageCommentedPostsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 데이터 바인딩 초기화
        _binding = FragmentMypageCommentedPostsBinding.inflate(inflater, container, false)
        val view = binding.root

        // 뒤로가기 버튼 클릭 이벤트 설정
        binding.ivBack.setOnClickListener {
            goBack() // 뒤로가기 기능을 수행하는 함수 호출
        }

        return view
    }

    // 뒤로가기 기능을 수행하는 함수
    private fun goBack() {
        parentFragmentManager.popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



