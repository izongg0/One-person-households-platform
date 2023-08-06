package com.umc.one_person_households_platform.view.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.umc.one_person_households_platform.R

class PostMorebtnFragment(val itemClick: (Int) -> Unit) :
    BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_post_morebtn, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<TextView>(R.id.tv_chat).setOnClickListener {

            itemClick(0)


            dialog?.dismiss()
        }
        view.findViewById<TextView>(R.id.tv_report).setOnClickListener {


            itemClick(1)
            dialog?.dismiss()
        }


    }
}