package com.umc.one_person_households_platform.view.groupbuyingdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.databinding.FragmentGroupBuyingDetailMoreUserBinding
import com.umc.one_person_households_platform.databinding.FragmentGroupBuyingDetailMoreWriterBinding

class GroupBuyingDetailMoreUserFragment : Fragment() {

    private var _binding: FragmentGroupBuyingDetailMoreUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGroupBuyingDetailMoreUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}