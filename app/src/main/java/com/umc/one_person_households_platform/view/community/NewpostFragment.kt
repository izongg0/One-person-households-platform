package com.umc.one_person_households_platform.view.community

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.databinding.FragmentNewpostBinding

class NewpostFragment : Fragment() {

    private lateinit var
            binding: FragmentNewpostBinding
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_newpost, container, false)

        binding.tvCatergory.setOnClickListener {
            val orderBottomDialogFragment: NewpostcatergoryFragment = NewpostcatergoryFragment() {
                when (it) {
                    0 -> binding.tvCatergory.text = "맛집이야기"
                    1 -> binding.tvCatergory.text = "질문있어요"
                    2 -> binding.tvCatergory.text = "대화해요"
                    3 -> binding.tvCatergory.text = "공유해요"

                }
            }
            orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
        }


        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // 모든 EditText의 입력 내용이 비어있지 않은지 확인
                val isEditText1NotEmpty = !binding.etEditcontent.text.isNullOrBlank()
                val isEditText2NotEmpty = !binding.etEdittitle.text.isNullOrBlank()
                val isEditText3NotEmpty = !(binding.tvCatergory.text == "게시글의 주제를 선택해주세요")

                // 모든 EditText가 비어있지 않으면 버튼 활성화
                binding.tvAddbtn.isEnabled =
                    isEditText1NotEmpty && isEditText2NotEmpty && isEditText3NotEmpty

            }
        }
        binding.tvAddbtn.setOnClickListener {

            if(binding.tvAddbtn.isEnabled == true){

            Navigation.findNavController(binding.root).navigate(R.id.action_newpostFragment_to_communityFragment)

                //데이터 저장
            }
        }

        binding.btnArrowBack.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_newpostFragment_to_communityFragment)


        }
        binding.etEditcontent.addTextChangedListener(textWatcher)
        binding.etEdittitle.addTextChangedListener(textWatcher)
        binding.tvCatergory.addTextChangedListener(textWatcher)


        //추가


        binding.tvAddphoto.setOnClickListener {
            openGallery()
        }

        return binding.root

    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                val bitmap =
                    MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
                binding.ivPhoto.setImageBitmap(bitmap)
            }
        }
    }
}

