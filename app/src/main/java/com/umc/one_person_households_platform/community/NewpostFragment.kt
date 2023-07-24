package com.umc.one_person_households_platform.community

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.databinding.FragmentNewpostBinding


class NewpostFragment : Fragment() {


    val binding by lazy { FragmentNewpostBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

//        binding.tvCatergory.setOnClickListener {
//
//
//            val bottomSheet = BottomSheetDialog(requireContext())
//            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
//
//        }

// https://youngest-programming.tistory.com/349
        binding.tvCatergory.setOnClickListener {
            val orderBottomDialogFragment: NewpostcatergoryFragment = NewpostcatergoryFragment() {
                when (it) {
                    0 -> binding.tvCatergory.text ="맛집이야기"
                    1 -> binding.tvCatergory.text ="질문있어요"
                    2 -> binding.tvCatergory.text ="대화해요"
                    3 -> binding.tvCatergory.text ="공유해요"

                }
            }
            orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
        }



        return binding.root
    }


}

