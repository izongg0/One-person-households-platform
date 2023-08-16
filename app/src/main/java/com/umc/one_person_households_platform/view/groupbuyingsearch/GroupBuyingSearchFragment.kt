package com.umc.one_person_households_platform.view.groupbuyingsearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.umc.one_person_households_platform.databinding.FragmentGroupBuyingSearchBinding
import com.umc.one_person_households_platform.view.common.ViewModelFactory
import com.umc.one_person_households_platform.view.groupbuying.GroupBuyingAdapter
import kotlinx.coroutines.flow.collectLatest

class GroupBuyingSearchFragment : Fragment() {

    private var _binding: FragmentGroupBuyingSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GroupBuyingSearchViewModel by viewModels { ViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGroupBuyingSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.search = this

        onEnterButtonClick()
    }

    // 검색어 입력 후 엔터 버튼 클릭
    private fun onEnterButtonClick() {
        binding.etSearch.setOnEditorActionListener { text, action, _ ->
            when(action) {
                EditorInfo.IME_ACTION_GO -> {
                    val adapter = GroupBuyingAdapter()
                    binding.rvContent.adapter = adapter

                    binding.tvDescription.visibility = INVISIBLE
                    binding.rvContent.visibility = VISIBLE
                    binding.view.visibility = INVISIBLE

                    lifecycleScope.launchWhenCreated {
                        viewModel.getGroupBuyingSearchData(text.toString())

                        viewModel.content.collectLatest {
                            adapter.submitData(it)
                        }
                    }
                    true
                }
                else -> true
            }
        }
    }

    // 뒤로 가기 버튼 클릭
    fun onBackButton() {
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}