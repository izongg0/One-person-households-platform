package com.umc.one_person_households_platform.view.community

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.adapter.AddPostImageAdapter
import com.umc.one_person_households_platform.adapter.CommunityCategoryAdapter
import com.umc.one_person_households_platform.databinding.FragmentNewpostBinding
import com.umc.one_person_households_platform.model.AddResult
import com.umc.one_person_households_platform.model.ApiResponse
import com.umc.one_person_households_platform.model.CommunityAddpostDTO
import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.util.Base64

class NewpostFragment : Fragment() {

    private lateinit var
            binding: FragmentNewpostBinding
    private val PICK_IMAGE_REQUEST = 1
    private val selectedImageUris = mutableListOf<String>()
    lateinit var imageAdapter: AddPostImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_newpost, container, false)

        binding.llSelectCategory.setOnClickListener {
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

            var eximg = mutableListOf<String>("aaa","bbb")

        binding.tvAddbtn.setOnClickListener {

            if (binding.tvAddbtn.isEnabled == true) {

                var photouri: MutableList<String> = mutableListOf("사진1")
                var categoryid = 0
                when (binding.tvCatergory.text.toString()) {
                    "맛집이야기" -> categoryid = 11
                    "질문있어요" -> categoryid = 12
                    "대화해요" -> categoryid = 13
                    "공유해요" -> categoryid = 14
                    else -> println("null")
                }

                var postadd = CommunityAddpostDTO(
                    categoryid,
                    4,
                    binding.etEdittitle.text.toString(),
                    binding.etEditcontent.text.toString(),
                    selectedImageUris
                )

                Log.d("rrrrrr",postadd.toString())
                val apiService = ApiClient.create()
                val call = apiService.addCommunityPost(postadd)

                call.enqueue(object : Callback<ApiResponse> {
                    override fun onResponse(
                        call: Call<ApiResponse>,
                        response: Response<ApiResponse>
                    ) {
                        if (response.isSuccessful) {
                            // 성공적으로 응답이 도착한 경우
                            val result = response.body()
                            Log.d("rrrrrrr", "성공 결과: ${result.toString()}")
                        } else {

                            Log.d("rrrrrrr", "성공 결과: ${call.toString()}")

                        }
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        Log.d("rrrrrrr", "오류 처리: ${t.toString()}")
                    }
                })



                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_newpostFragment_to_communityFragment)

                //데이터 저장
            }

        }

        binding.btnArrowBack.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_newpostFragment_to_communityFragment)


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

    private fun showAlert(alerttext: String) {

        val myLayout = layoutInflater.inflate(R.layout.fragment_common_check, null)
        myLayout.findViewById<TextView>(R.id.tv_content).text = alerttext
        myLayout.findViewById<TextView>(R.id.tv_check).text = "확인"

        val build = AlertDialog.Builder(view?.context).apply {
            setView(myLayout)
        }
        val dialog = build.create()
        dialog.show()

        myLayout.findViewById<TextView>(R.id.tv_check).setOnClickListener {
            dialog.dismiss()
        }

    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        return try {
            // URI를 통해 비트맵을 가져옵니다.
            val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
            bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun openGallery() {
//        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        startActivityForResult(intent, PICK_IMAGE_REQUEST)
//        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
//        intent.addCategory(Intent.CATEGORY_OPENABLE)
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT // 갤러리에서 다중 선택을 지원하는 경우에도 사용할 수 있는 방법

        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data?.clipData != null) {
                val clipData = data.clipData
                for (i in 0 until clipData?.itemCount!!) {
                    val uri = clipData.getItemAt(i).uri
                    var bitmap_imag = getBitmapFromUri(uri)
                    var resized_bitmap = resizeBitmapWithAspectRatio(bitmap_imag!!,350,350,)

                    var string_bitmap =bitmapToString(resized_bitmap!!)

                    Log.d("iiimmage",string_bitmap.length.toString())


                    selectedImageUris.add(string_bitmap!!)

                }
            } else if (data?.data != null) {
                val uri = data.data
                var bitmap_imag = getBitmapFromUri(uri!!)
                var resized_bitmap = resizeBitmapWithAspectRatio(bitmap_imag!!,500,500,)

                var string_bitmap =bitmapToString(resized_bitmap!!)

                selectedImageUris.add(string_bitmap!!)
            }

            // 선택한 이미지들을 처리하는 로직을 추가할 수 있습니다.
            // 이 리스트에는 선택한 이미지들의 URI가 저장되어 있음

            // 예를 들어, 선택한 이미지들을 로그로 출력해볼 수 있습니다.
            for (imageUri in selectedImageUris) {
                Log.d("SelectedImage", "Image URI: $imageUri")
            }
        }

        Log.d("iiiiimage",selectedImageUris[0].length.toString())
        imageAdapter = AddPostImageAdapter(selectedImageUris)
        binding.rvImage.adapter = imageAdapter
        binding.rvImage.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)

//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
////            data?.data?.let { uri ->
////                val bitmap =
////                    MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
////                binding.ivPhoto.setImageBitmap(bitmap)
////            }
//        }
    }



    fun bitmapToString(bitmap: Bitmap): String {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

        val bytes = stream.toByteArray()

        return Base64.getEncoder().encodeToString(bytes)
    }


    fun resizeBitmapWithAspectRatio(bitmap: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
        val originalWidth = bitmap.width
        val originalHeight = bitmap.height

        val ratioX = maxWidth.toFloat() / originalWidth
        val ratioY = maxHeight.toFloat() / originalHeight

        val scaleFactor = if (ratioX < ratioY) ratioX else ratioY

        val newWidth = (originalWidth * scaleFactor).toInt()
        val newHeight = (originalHeight * scaleFactor).toInt()

        Log.d("resizediiimg","$newWidth ㅇㅇㅇ $newHeight")
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
    }


    fun resizeBitmap(bitmap: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
    }

}

