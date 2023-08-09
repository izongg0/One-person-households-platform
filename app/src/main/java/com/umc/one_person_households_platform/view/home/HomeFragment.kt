package com.umc.one_person_households_platform.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.umc.one_person_households_platform.databinding.FragmentHomeBinding
import com.umc.one_person_households_platform.view.common.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        setAdapter()
    }

    private fun setAdapter() {
        val groupBuyingListAdapter = HomeGroupBuyingAdapter()
        binding.rvGroupBuying.adapter = groupBuyingListAdapter

        viewModel.groupBuyingContent.observe(viewLifecycleOwner) {
            groupBuyingListAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}