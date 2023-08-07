package com.umc.one_person_households_platform.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.one_person_households_platform.databinding.FragmentLoginFindIdBinding

class LoginFindIdFragment : Fragment() {

    private var _binding: FragmentLoginFindIdBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginFindIdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 이미지뷰를 찾아서 클릭 이벤트를 설정합니다.
        binding.ivBack.setOnClickListener {
            goBack() // 뒤로가기 기능을 수행하는 함수 호출
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 뒤로가기 기능을 수행하는 함수
    private fun goBack() {
        parentFragmentManager.popBackStack()
    }
}
