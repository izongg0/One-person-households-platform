package com.umc.one_person_households_platform.view.groupbuyingwrite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.umc.one_person_households_platform.databinding.FragmentGroupBuyingWriteCategoryBinding

class GroupBuyingWriteCategoryFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentGroupBuyingWriteCategoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GroupBuyingWriteShareViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGroupBuyingWriteCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    // 초기 설정
    private fun init() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.category = this
    }

    // 카테고리 클릭
    fun onCategoryButton(text: String) {
        viewModel.setGroupBuyingCategory(text)
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}