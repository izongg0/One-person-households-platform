package com.umc.one_person_households_platform.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.one_person_households_platform.databinding.FragmentLoginPasswordResultBinding

class LoginPasswordResultFragment : Fragment() {

    private var _binding: FragmentLoginPasswordResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginPasswordResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 이미지뷰를 찾아서 클릭 이벤트를 설정합니다.
        binding.ivClose.setOnClickListener {
            // 창을 닫는 기능을 수행합니다.
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}