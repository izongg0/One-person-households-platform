package com.umc.one_person_households_platform.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.databinding.FragmentHomeBinding
import com.umc.one_person_households_platform.view.common.OnClickInterface
import com.umc.one_person_households_platform.view.common.ViewModelFactory

class HomeFragment : Fragment(), OnClickInterface {

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
        binding.home = this
        setAdapter()
    }

    private fun setAdapter() {
        val communityAdapter = HomeCommunityAdapter(this)
        val groupBuyingListAdapter = HomeGroupBuyingAdapter(this)
        val hotRecipeAdapter = HomeHotRecipeAdapter(this)

        binding.rvCommunity.adapter = communityAdapter
        binding.rvGroupBuying.adapter = groupBuyingListAdapter
        binding.rvHotRecipe.adapter = hotRecipeAdapter

        viewModel.communityContent.observe(viewLifecycleOwner) {
            communityAdapter.submitList(it)
        }
        viewModel.groupBuyingContent.observe(viewLifecycleOwner) {
            groupBuyingListAdapter.submitList(it)
        }
        viewModel.hotRecipeContent.observe(viewLifecycleOwner) {
            hotRecipeAdapter.submitList(it)
        }
    }

    // 더 보기 이동
    fun onMoveButton(view: View) {
        when(view.id) {
            R.id.tv_community_more_detail -> {
                val action = HomeFragmentDirections.actionHomeFragmentToCommunityFragment("인기순")
                findNavController().navigate(action)
            }
            R.id.tv_group_buying_more_detail -> {
                val action = HomeFragmentDirections.actionHomeFragmentToGroupBuyingFragment("마감임박순")
                findNavController().navigate(action)
            }
            R.id.tv_recipe_more_detail -> {
                val action = HomeFragmentDirections.actionHomeFragmentToRecipemainFragment("인기순")
                findNavController().navigate(action)
            }
        }
    }

    // 게시글 상세 화면 이동
    override fun onClick(postIdx: Int, category: String) {
        when(category) {
            "커뮤니티" -> {
                val action = HomeFragmentDirections.actionHomeFragmentToPostdetailFragment(postIdx)
                findNavController().navigate(action)
            }
            "공동구매" -> {
                val action = HomeFragmentDirections.actionHomeFragmentToGroupBuyingDetailFragment(postIdx)
                findNavController().navigate(action)
            }
            "레시피" -> {
                val action = HomeFragmentDirections.actionHomeFragmentToRecipedetailFragment(postIdx)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}