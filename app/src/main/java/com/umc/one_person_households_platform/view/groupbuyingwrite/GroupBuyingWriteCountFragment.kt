package com.umc.one_person_households_platform.view.groupbuyingwrite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.umc.one_person_households_platform.databinding.FragmentGroupBuyingWriteCountBinding

class GroupBuyingWriteCountFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentGroupBuyingWriteCountBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GroupBuyingWriteShareViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGroupBuyingWriteCountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    // 초기 설정
    private fun init() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.count = this

        binding.npPeopleCount.apply {
            value = 0
            minValue = 2
            maxValue = 8
            wrapSelectorWheel = false
        }
    }

    // 취소 버튼 클릭
    fun onCloseButtonClick() {
        findNavController().navigateUp()
    }

    // 확인 버튼 클릭
    fun onCheckButtonClick() {
        viewModel.setGroupBuyingCount(binding.npPeopleCount.value)
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}