package com.umc.one_person_households_platform.view.community

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.databinding.FragmentPostdetailBinding
import com.umc.one_person_households_platform.model.CommunityDetailDTO
import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostdetailFragment : Fragment() {

    private lateinit var
            binding: FragmentPostdetailBinding

    private var isEmpathyClicked = false
    private var isInterestClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_postdetail, container, false)


        binding.tvEmpathybtn.setOnClickListener {
            if (isEmpathyClicked) {
                binding.tvEmpathybtn.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.btn_community_thumb_clicked,
                    0,
                    0,
                    0
                )
                binding.tvEmpathybtn.setTextColor(Color.rgb(255, 182, 41))
            } else {
                binding.tvEmpathybtn.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.btn_community_thumb,
                    0,
                    0,
                    0
                )
                binding.tvEmpathybtn.setTextColor(Color.rgb(134, 134, 134))
            }

            isEmpathyClicked = !isEmpathyClicked
        }

        binding.tvInterestbtn.setOnClickListener {
            if (isInterestClicked) {
                binding.tvEmpathybtn.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.btn_community_heart_clicked,
                    0,
                    0,
                    0
                )
                binding.tvEmpathybtn.setTextColor(Color.rgb(255, 182, 41))
            } else {
                binding.tvEmpathybtn.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.btn_community_heart,
                    0,
                    0,
                    0
                )
                binding.tvEmpathybtn.setTextColor(Color.rgb(134, 134, 134))
            }

            isInterestClicked = !isInterestClicked
        }


        binding.ivMorebtn.setOnClickListener {

            val orderBottomDialogFragment: PostMorebtnFragment = PostMorebtnFragment() {
                when (it) {
                    0 -> Navigation.findNavController(binding.root)
                        .navigate(R.id.action_postdetailFragment_to_reportFragment)
                    1 -> Navigation.findNavController(binding.root)
                        .navigate(R.id.action_postdetailFragment_to_reportFragment)

                }

            }
            orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
        }

        val myData = arguments?.getInt("this_post")

        var postdetailcontent: CommunityDetailDTO? = null

        val apiClient = ApiClient.create()

        apiClient.getPostDetail(myData!!).enqueue(object : Callback<CommunityDetailDTO> {
            override fun onResponse(
                call: Call<CommunityDetailDTO>,
                response: Response<CommunityDetailDTO>
            ) {
                if (response.isSuccessful) {

                    postdetailcontent = response.body()

                    var postCategory: String = ""

                    when (postdetailcontent!!.result!!.categoryIdx) {

                        11 -> postCategory = "맛집이야기"
                        12 -> postCategory = "질문있어요"
                        13 -> postCategory = "대화해요"
                        14 -> postCategory = "공유해요"
                    }

                    binding.tvCategory.text = postCategory
                    binding.tvAuthor.text = postdetailcontent!!.result!!.userIdx.toString()
                    binding.tvTime.text = postdetailcontent!!.result!!.createAt.toString()
                    binding.tvPosttitle.text = postdetailcontent!!.result!!.title
                    binding.tvPostcontent.text = postdetailcontent!!.result!!.contents
                    binding.tvInterestbtn.text = postdetailcontent!!.result!!.likeCount.toString()


                } else {
                    Log.e("ApiError", "API 요청 실패: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<CommunityDetailDTO>, t: Throwable) {
                Log.e("ApiError", "API 요청 실패", t)
            }
        })



        return binding.root
    }


}