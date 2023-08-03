package com.umc.one_person_households_platform.view.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.umc.one_person_households_platform.databinding.FragmentCommonMoreOneBinding

class CommonMoreOneFragment(
    moreOneInterface: CommonMoreOneInterface,
    text: String
) : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonMoreOneBinding? = null
    private val binding get() = _binding!!

    private var moreOneInterface: CommonMoreOneInterface? = null
    private var text: String? = null

    init {
        this.moreOneInterface = moreOneInterface
        this.text = text
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommonMoreOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.content = text
        // TODO("클릭 이벤트 작성")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}