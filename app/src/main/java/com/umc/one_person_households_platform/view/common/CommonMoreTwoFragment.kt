package com.umc.one_person_households_platform.view.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.umc.one_person_households_platform.databinding.FragmentCommonMoreTwoBinding

class CommonMoreTwoFragment(
    moreTwoInterface: CommonMoreTwoInterface,
    firstText: String,
    secondText: String
) : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonMoreTwoBinding? = null
    private val binding get() = _binding!!

    private var moreTwoInterface: CommonMoreTwoInterface? = null
    private var firstText: String? = null
    private var secondText: String? = null

    init {
        this.moreTwoInterface = moreTwoInterface
        this.firstText = firstText
        this.secondText = secondText
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommonMoreTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.content = listOf(firstText, secondText)
        // TODO("클릭 이벤트 작성")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}