package com.umc.one_person_households_platform.view.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.umc.one_person_households_platform.databinding.FragmentCommonCheckBinding

class CommonCheckFragment(
    checkInterface: CommonCheckInterface,
    check: String,
    text: String
) : DialogFragment() {

    private var _binding: FragmentCommonCheckBinding? = null
    private val binding get() = _binding!!

    private var checkInterface: CommonCheckInterface? = null
    private var check: String? = null
    private var text: String? = null

    init {
        this.checkInterface = checkInterface
        this.check = check
        this.text = text
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommonCheckBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.content = listOf(text, check)
        // TODO("클릭 이벤트 작성")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}