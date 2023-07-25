package com.umc.one_person_households_platform.view.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.umc.one_person_households_platform.R

// TODO: Rename parameter arguments, choose names that match

class NewpostcatergoryFragment(val itemClick: (Int) -> Unit) :
    BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_newpostcatergory, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.btn_delicious).setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        view.findViewById<TextView>(R.id.btn_question).setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        view.findViewById<TextView>(R.id.btn_communicate).setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
        view.findViewById<TextView>(R.id.btn_share).setOnClickListener {
            itemClick(3)
            dialog?.dismiss()
        }
    }
}