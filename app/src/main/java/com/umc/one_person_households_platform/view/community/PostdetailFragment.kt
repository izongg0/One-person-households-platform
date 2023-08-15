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
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.adapter.CommunityCategoryAdapter
import com.umc.one_person_households_platform.adapter.CommunityCommentAdapter
import com.umc.one_person_households_platform.databinding.FragmentPostdetailBinding
import com.umc.one_person_households_platform.model.ApiResponse
import com.umc.one_person_households_platform.model.CommentAddItems
import com.umc.one_person_households_platform.model.CommentAddResult
import com.umc.one_person_households_platform.model.CommentItems
import com.umc.one_person_households_platform.model.CommunityAddpostDTO
import com.umc.one_person_households_platform.model.CommunityComment
import com.umc.one_person_households_platform.model.CommunityDetailDTO
import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostdetailFragment : Fragment() {

    private lateinit var
            binding: FragmentPostdetailBinding
    lateinit var postAdapter: CommunityCommentAdapter

    private var isEmpathyClicked = false
    private var isInterestClicked = false
    private val apiClient = ApiClient.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_postdetail, container, false)


        binding.ivBackmove.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_postdetailFragment_to_communityFragment)
        }
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

//        val myData = arguments?.getInt("this_post")

        var postdetailcontent: CommunityDetailDTO? = null
        val myData = arguments?.getInt("this_post")

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
                    binding.tvInterstCount.text = postdetailcontent!!.result!!.likeCount.toString()

                    getComment(postdetailcontent!!.result!!.comments)

                    binding.ivAddComment.setOnClickListener {


                        var commentadd = CommentAddItems(
                            postdetailcontent!!.result!!.postIdx,
                            postdetailcontent!!.result!!.userIdx // 현재 로그인한 유저의 인덱스를 나중에 넣어야함
                            ,0,
                            binding.etWritecomment.text.toString())
                        addComment(commentadd)

                        resetcomment(myData) { resetpost ->
                            // resetpost를 활용한 작업 수행

                            getComment(resetpost)

                        }


                    }


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

    fun addComment(commentaddItem : CommentAddItems){


        val call2 = apiClient.addCommunityComment(commentaddItem)

        call2.enqueue(object : Callback<CommentAddResult> {
            override fun onResponse(
                call: Call<CommentAddResult>,
                response: Response<CommentAddResult>
            ) {
                if (response.isSuccessful) {
                    // 성공적으로 응답이 도착한 경우
                    val result = response.body()
                    Log.d("ffffff", "성공 결과: ${result.toString()}")

                } else {

                }
            }

            override fun onFailure(call: Call<CommentAddResult>, t: Throwable) {
                Log.d("rrrrrrr", "오류 처리: ${t.toString()}")
            }
        })

    }

    fun resetcomment(postid: Int, callback: (List<Int>) -> Unit) {
        val apiClient = ApiClient.create()

        apiClient.getPostDetail(postid).enqueue(object : Callback<CommunityDetailDTO> {
            override fun onResponse(
                call: Call<CommunityDetailDTO>,
                response: Response<CommunityDetailDTO>
            ) {
                if (response.isSuccessful) {
                    val resetpost = response.body()!!.result.comments
                    callback(resetpost)
                } else {
                    Log.e("ApiError", "API 요청 실패: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<CommunityDetailDTO>, t: Throwable) {
                Log.e("ApiError", "API 요청 실패", t)
            }
        })
    }

// 함수 사용 예시



    //    fun resetcomment(postid:Int){
//        val apiClient = ApiClient.create()
//
//        apiClient.getPostDetail(postid!!).enqueue(object : Callback<CommunityDetailDTO> {
//            override fun onResponse(
//                call: Call<CommunityDetailDTO>,
//                response: Response<CommunityDetailDTO>
//            ) {
//                if (response.isSuccessful) {
//
//                    var resetpost = response.body()!!.result.comments
//
//
//
//                } else {
//                    Log.e("ApiError", "API 요청 실패: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<CommunityDetailDTO>, t: Throwable) {
//                Log.e("ApiError", "API 요청 실패", t)
//            }
//        })
//
//    }
    fun getComment(commentIdList : List<Int>){

        val commentlist: MutableList<CommentItems> = mutableListOf()


        for( i in commentIdList){

            apiClient.getCommunityComment(i).enqueue(object : Callback<CommunityComment> {
                override fun onResponse(
                    call: Call<CommunityComment>,
                    response: Response<CommunityComment>
                ) {
                    commentlist.add(response.body()!!.result)

                    postAdapter = CommunityCommentAdapter(commentlist)
                    binding.rvCommentrv.adapter = postAdapter
                    binding.rvCommentrv.layoutManager = LinearLayoutManager(requireContext())


                }

                override fun onFailure(call: Call<CommunityComment>, t: Throwable) {
                    Log.e("ApiError", "API 요청 실패", t)
                }
            })
        }


    }

}