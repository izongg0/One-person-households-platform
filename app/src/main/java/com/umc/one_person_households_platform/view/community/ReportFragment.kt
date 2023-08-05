package com.umc.one_person_households_platform.view.community

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.databinding.FragmentReportBinding


class ReportFragment : Fragment() {

    private lateinit var binding : FragmentReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_report,container,false)
        binding.tvAd.setOnClickListener {
            showAlert()
        }
        binding.tvNoManner.setOnClickListener {
            showAlert()
        }
        binding.tvFishing.setOnClickListener {
            showAlert()
        }
        binding.tvSwearword.setOnClickListener {
            showAlert()
        }
        binding.tvHarassment.setOnClickListener {
            showAlert()
        }
        binding.tvSwindle.setOnClickListener {
            showAlert()
        }
        binding.tvEtc.setOnClickListener {
            showAlert()
        }


        return binding.root
    }
    private fun showAlert() {


        val myLayout = layoutInflater.inflate(R.layout.fragment_common_yes_or_no, null)
        myLayout.findViewById<TextView>(R.id.tv_question).text = "‘딸기가 좋아’님을 신고하겠습니까? 합당한 이유없이 사용자를 신고할 경우 불이익이 있을 수 있습니다."
        myLayout.findViewById<TextView>(R.id.tv_check).text = "확인"

        val build = AlertDialog.Builder(view?.context).apply {
            setView(myLayout)
        }
        val dialog = build.create()
        dialog.show()

        myLayout.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
            dialog.dismiss()
        }
        myLayout.findViewById<TextView>(R.id.tv_check).setOnClickListener {
            dialog.dismiss()
        }

    }

}