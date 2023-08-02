package com.umc.one_person_households_platform.view.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.umc.one_person_households_platform.databinding.FragmentCommonYesOrNoBinding

class CommonYesOrNoFragment(
    yesOrNoInterface: CommonYesOrNoInterface,
    text: String
) : DialogFragment() {

    private var _binding: FragmentCommonYesOrNoBinding? = null
    private val binding get() = _binding!!

    private var yesOrNoInterface: CommonYesOrNoInterface? = null
    private var text: String? = null

    init {
        this.yesOrNoInterface = yesOrNoInterface
        this.text = text
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommonYesOrNoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.content = text
        // TODO("확인, 취소 버튼 눌렀을 때 각각 이벤트 작성")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}