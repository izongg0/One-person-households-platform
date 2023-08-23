package com.umc.one_person_households_platform.view.groupbuyingdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.umc.one_person_households_platform.databinding.FragmentGroupBuyingDetailBinding
import com.umc.one_person_households_platform.view.common.ViewModelFactory

class GroupBuyingDetailFragment : Fragment() {

    private var _binding: FragmentGroupBuyingDetailBinding? = null
    private val binding get() = _binding!!

    private val args: GroupBuyingDetailFragmentArgs by navArgs()
    private val viewModel: GroupBuyingDetailViewModel by viewModels { ViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGroupBuyingDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    // 초기 설정
    private fun init() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.detail = this

        viewModel.content.observe(viewLifecycleOwner) {
            binding.content = it
        }
    }

    // 뒤로 가기 버튼 클릭
    fun onBackButtonClick() {
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}