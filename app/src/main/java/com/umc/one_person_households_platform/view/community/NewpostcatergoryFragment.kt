package com.umc.one_person_households_platform.view.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.databinding.FragmentNewpostcatergoryBinding


class NewpostcatergoryFragment(val itemClick: (Int) -> Unit) :
    BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_newpostcatergory, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RadioButton>(R.id.btn_delicious).setOnClickListener {

            view.findViewById<RadioButton>(R.id.btn_delicious).isChecked = true


            itemClick(0)
            dialog?.dismiss()
        }
        view.findViewById<RadioButton>(R.id.btn_question).setOnClickListener {

            view.findViewById<RadioButton>(R.id.btn_question).isChecked = true

            itemClick(1)
            dialog?.dismiss()
        }
        view.findViewById<RadioButton>(R.id.btn_communicate).setOnClickListener {


            view.findViewById<RadioButton>(R.id.btn_communicate).isChecked = true

            itemClick(2)
            dialog?.dismiss()
        }
        view.findViewById<RadioButton>(R.id.btn_share).setOnClickListener {


            view.findViewById<RadioButton>(R.id.btn_share).isChecked = true
            itemClick(3)
            dialog?.dismiss()
        }
    }
}