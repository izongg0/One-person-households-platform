package com.umc.one_person_households_platform.view.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.databinding.FragmentChatListBinding


class ChatListFragment : Fragment() {

    private lateinit var binding : FragmentChatListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_chat_list,container,false)




        return binding.root
    }


}