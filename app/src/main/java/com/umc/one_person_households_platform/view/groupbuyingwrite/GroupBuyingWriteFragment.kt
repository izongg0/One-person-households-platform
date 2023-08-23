package com.umc.one_person_households_platform.view.groupbuyingwrite

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.databinding.FragmentGroupBuyingWriteBinding
import com.umc.one_person_households_platform.model.GroupBuyingWritePost
import com.umc.one_person_households_platform.view.common.ViewModelFactory
import java.io.ByteArrayOutputStream
import java.util.Base64

class GroupBuyingWriteFragment : Fragment(), GroupBuyingWriteRemoveInterface {

    private var _binding: FragmentGroupBuyingWriteBinding? = null
    private val binding get() = _binding!!

    private val shareViewModel: GroupBuyingWriteShareViewModel by activityViewModels()
    private val viewModel: GroupBuyingWriteViewModel by viewModels { ViewModelFactory() }

    private lateinit var photoResult: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGroupBuyingWriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    // 초기 설정
    private fun init() {
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            write = this@GroupBuyingWriteFragment

            // 가져온 사진에 대한 처리
            photoResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if(result.resultCode == RESULT_OK) {
                    val data: Intent? = result.data

                    // 사진이 여러 장인 경우
                    if(data?.clipData != null) {
                        val count = data.clipData!!.itemCount

                        for(i in 0 until count) {
                            val imageUri = data.clipData?.getItemAt(i)?.uri
                            viewModel.addGroupBuyingPhoto(imageUri.toString())
                        }
                    }
                    // 사진이 한 장인 경우
                    else {
                        val imageUri: Uri? = data?.data

                        if(imageUri != null) {
                            viewModel.addGroupBuyingPhoto(imageUri.toString())
                        }
                    }

                    setAdapter()
                }
            }

            // 조건에 따른 작성 완료 버튼 활성화
            val watcher = object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
                override fun afterTextChanged(p0: Editable?) {
                    setEnabled()
                }
            }

            etTitle.addTextChangedListener(watcher)
            etName.addTextChangedListener(watcher)
            etMoney.addTextChangedListener(watcher)
            etDeadLineYear.addTextChangedListener(watcher)
            etDeadLineMonth.addTextChangedListener(watcher)
            etDeadLineDay.addTextChangedListener(watcher)
            etContent.addTextChangedListener(watcher)

            shareViewModel.groupBuyingCategory.observe(viewLifecycleOwner) {
                category = it
                setEnabled()
            }
            shareViewModel.groupBuyingCount.observe(viewLifecycleOwner) {
                count = it.toString()
                setEnabled()
            }
            viewModel.groupBuyingPhoto.observe(viewLifecycleOwner) {
                tvSelectPhoto.text = "${it.size}/5"
            }
        }
    }

    // 어댑터 설정
    private fun setAdapter() {
        val adapter = GroupBuyingWritePhotoAdapter(this)
        binding.rvPhotoList.adapter = adapter
        adapter.submitList(viewModel.getGroupBuyingPhoto())
    }

    // 작성 완료 버튼 상태
    private fun setEnabled() {
        with(binding) {
            tvFinish.isEnabled = !etTitle.text.isNullOrBlank() && !etName.text.isNullOrBlank() && !etMoney.text.isNullOrBlank()
                    && !etDeadLineYear.text.isNullOrBlank() && !etDeadLineMonth.text.isNullOrBlank()
                    && !etDeadLineDay.text.isNullOrBlank() && !etContent.text.isNullOrBlank()
                    && shareViewModel.getGroupBuyingCategory() && shareViewModel.getGroupBuyingCount()
        }
    }

    // 앨범 버튼 클릭
    fun onPhotoButtonClick() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
        photoResult.launch(intent)
    }

    // 배송비 '유' 버튼 클릭
    fun onDeliveryFeeYesButtonClick() {
        binding.etDeliveryFee.apply {
            isEnabled = true
        }
    }

    // 배송비 '무' 버튼 클릭
    fun onDeliveryFeeNoButtonClick() {
        binding.etDeliveryFee.apply {
            setText("")
            isEnabled = false
        }
    }

    // 카테고리 선택 버튼 클릭
    fun onCategoryButtonClick() {
        findNavController().navigate(R.id.action_groupBuyingWriteFragment_to_groupBuyingWriteCategoryFragment)
    }

    // 모집 인원 선택 버튼 클릭
    fun onCountButtonClick() {
        findNavController().navigate(R.id.action_groupBuyingWriteFragment_to_groupBuyingWriteCountFragment)
    }

    // 뒤로 가기 버튼 클릭
    fun onBackButtonClick() {
        shareViewModel.setInit()
        findNavController().navigateUp()
    }

    // 작성 완료 버튼 클릭
    fun onCheckButtonClick() {
        with(binding) {
            val category = when (tvSelectCategory.text.toString()) {
                "식재료" -> 21
                "생활용품" -> 22
                else -> 23
            }
            val userIdx = 4
            val title = etTitle.text.toString()
            val name = etName.text.toString()
            val link = etLink.text.toString().ifBlank { "" }
            val singlePrice = etMoney.text.toString().toInt()
            val members = tvPeopleCount.text.toString().toInt()
            val deliveryFee = etMoney.text.toString().ifBlank { "0" }.toInt()
            val day = "${etDeadLineYear.text}-${etDeadLineMonth.text}-${etDeadLineDay.text}"

            val photoList = viewModel.getGroupBuyingPhoto()
            val photoToString = mutableListOf<String>()
/*
            for(i in 0 until 6) {
                val bitmap = getBitmapFromUri(photoList[i].toUri())
                val resizeBitmap = resizeBitmapWithAspectRatio(bitmap!!, 350, 350)
                photoToString.add(bitmapToString(resizeBitmap))
            }
 */
            val data = GroupBuyingWritePost(category, userIdx, title, name, link,singlePrice, deliveryFee, members, day, listOf(""))
            viewModel.addGroupBuyingWriteData(data)
            findNavController().navigateUp()
        }
    }

    // Uri -> Bitmap
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

    private fun resizeBitmapWithAspectRatio(bitmap: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
        val originalWidth = bitmap.width
        val originalHeight = bitmap.height

        val ratioX = maxWidth.toFloat() / originalWidth
        val ratioY = maxHeight.toFloat() / originalHeight

        val scaleFactor = if (ratioX < ratioY) ratioX else ratioY

        val newWidth = (originalWidth * scaleFactor).toInt()
        val newHeight = (originalHeight * scaleFactor).toInt()

        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
    }

    // Bitmap -> String
    private fun bitmapToString(bitmap: Bitmap): String {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

        val bytes = stream.toByteArray()

        return Base64.getEncoder().encodeToString(bytes)
    }

    // 사진 삭제
    override fun onRemoveClick(position: Int) {
        viewModel.removePhoto(position)
        setAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        shareViewModel.setInit()
        _binding = null
    }
}