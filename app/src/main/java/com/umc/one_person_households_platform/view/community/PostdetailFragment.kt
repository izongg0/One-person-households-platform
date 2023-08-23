package com.umc.one_person_households_platform.view.community

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.adapter.CommunityCategoryAdapter
import com.umc.one_person_households_platform.adapter.CommunityCommentAdapter
import com.umc.one_person_households_platform.adapter.PostDetailImageAdapter
import com.umc.one_person_households_platform.databinding.FragmentPostdetailBinding
import com.umc.one_person_households_platform.model.ApiResponse
import com.umc.one_person_households_platform.model.CommentAddItems
import com.umc.one_person_households_platform.model.CommentAddResult
import com.umc.one_person_households_platform.model.CommentItems
import com.umc.one_person_households_platform.model.CommonPostResult
import com.umc.one_person_households_platform.model.CommunityAddpostDTO
import com.umc.one_person_households_platform.model.CommunityComment
import com.umc.one_person_households_platform.model.CommunityDetailDTO
import com.umc.one_person_households_platform.model.PostDeleteDTO
import com.umc.one_person_households_platform.model.PostDeleteItems
import com.umc.one_person_households_platform.model.PostHeartDTO
import com.umc.one_person_households_platform.model.RecipeScrapBody
import com.umc.one_person_households_platform.model.RecipeScrapResponse

import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.util.Base64


class PostdetailFragment : Fragment() {

    private lateinit var binding: FragmentPostdetailBinding
    lateinit var postAdapter: CommunityCommentAdapter
    lateinit var imageAdapter: PostDetailImageAdapter

    private val apiClient = ApiClient.create()
    private val arg: PostdetailFragmentArgs by navArgs()


    private val current_useridx: Int = 4
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_postdetail, container, false)
//        val myData = arguments?.getInt("this_post")
        val myData = arg.postIdx
        apiClient.getPostDetail(myData!!).enqueue(object : Callback<CommunityDetailDTO> {
            override fun onResponse(
                call: Call<CommunityDetailDTO>, response: Response<CommunityDetailDTO>
            ) {
                if (response.isSuccessful) {
                    Log.d("current_post",response.body().toString())

                    var postdetailcontent: CommunityDetailDTO? = null

                    postdetailcontent = response.body()

                binding.ivBackmove.setOnClickListener {
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_postdetailFragment_to_communityFragment)
                }


                    //하트 버튼 시작

                    var currentHeartCount = postdetailcontent!!.result!!.heartCount

                    if (postdetailcontent.result.liked == true) {
                        binding.tvEmpathybtn.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.btn_community_thumb_clicked, 0, 0, 0
                        )
                        binding.tvEmpathybtn.setTextColor(Color.rgb(255, 182, 41))

                    }else{

                        binding.tvEmpathybtn.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.btn_community_thumb, 0, 0, 0
                        )
                        binding.tvEmpathybtn.setTextColor(Color.rgb(134, 134, 134))
                    }


                    // 관심 버튼 클릭 준비
                    binding.tvEmpathybtn.setOnClickListener {

                        if (postdetailcontent.result.liked == false) {
                            postdetailcontent.result.liked = true
                            binding.tvEmpathybtn.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.btn_community_thumb_clicked, 0, 0, 0
                            )
                            binding.tvEmpathybtn.setTextColor(Color.rgb(255, 182, 41))

                            currentHeartCount = currentHeartCount + 1
                            binding.tvEmpathyCount.text = currentHeartCount.toString()

                            val addscrap = PostHeartDTO(myData!!, 4)
                            val apiService = ApiClient.create()
                            val call = apiService.addHeartPost(addscrap)

                            call.enqueue(object : Callback<CommonPostResult> {
                                override fun onResponse(
                                    call: Call<CommonPostResult>,
                                    response: Response<CommonPostResult>
                                ) {
                                    if (response.isSuccessful) {
                                        // 성공적으로 응답이 도착한 경우
                                        val result = response.body()
                                        Log.d("rrrrrrr", "성공 결과: ${result.toString()}")
                                    } else {
                                        val result = response.body()

                                        Log.d("rrrrrrr", "성공 결과: ${result.toString()}")

                                    }
                                }

                                override fun onFailure(
                                    call: Call<CommonPostResult>, t: Throwable
                                ) {
                                    Log.d("rrrrrrr", "오류 처리: ${t.toString()}")
                                }
                            })

                        } else {
                            postdetailcontent.result.liked = false

                            currentHeartCount = currentHeartCount - 1
                            binding.tvInterstCount.text = currentHeartCount.toString()

                            binding.tvInterestbtn.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.btn_community_heart, 0, 0, 0
                            )
                            binding.tvInterestbtn.setTextColor(Color.rgb(134, 134, 134))

                            val addscrap = PostHeartDTO(myData!!, 4)
                            val apiService = ApiClient.create()
                            val call = apiService.cancelHeartPost(addscrap)

                            call.enqueue(object : Callback<CommonPostResult> {
                                override fun onResponse(
                                    call: Call<CommonPostResult>,
                                    response: Response<CommonPostResult>
                                ) {
                                    if (response.isSuccessful) {
                                        // 성공적으로 응답이 도착한 경우
                                        val result = response.body()
                                        Log.d("bbbbb", "성공 결과: ${result.toString()}")
                                    } else {

                                    }
                                }

                                override fun onFailure(
                                    call: Call<CommonPostResult>, t: Throwable
                                ) {
                                    Log.d("bbbbb", "오류 처리: ${t.toString()}")
                                }
                            })

                        }

                    }





                val apiClient = ApiClient.create()

                binding.ivMorebtn.setOnClickListener {

                    if (current_useridx == postdetailcontent!!.result!!.userIdx) { // 본인의 게시글인 경우
                        val orderBottomDialogFragment: PostMorebtnMeFragment = PostMorebtnMeFragment() {

                            when (it) {
                                0 -> {
                                    // 자신의 게시글인 경우
                                    val myLayout =
                                        layoutInflater.inflate(
                                            R.layout.fragment_common_yes_or_no,
                                            null
                                        )
                                    myLayout.findViewById<TextView>(R.id.tv_question).text =
                                        "삭제하시겠습니까?"
                                    myLayout.findViewById<TextView>(R.id.tv_check).text = "확인"

                                    val build = AlertDialog.Builder(view?.context).apply {
                                        setView(myLayout)
                                    }
                                    val dialog = build.create()
                                    dialog.show()

                                    myLayout.findViewById<TextView>(R.id.tv_cancel)
                                        .setOnClickListener {

                                            dialog.dismiss()
                                        }
                                    myLayout.findViewById<TextView>(R.id.tv_check)
                                        .setOnClickListener {
                                            // 삭제 구현

                                            deletepost(myData)


                                            dialog.dismiss()
                                            Navigation.findNavController(binding.root)
                                                .navigate(R.id.action_postdetailFragment_to_communityFragment)
                                        }


                                }
                                //게시글 삭제


                                1 -> Navigation.findNavController(binding.root)
                                    .navigate(R.id.action_postdetailFragment_to_reportFragment)
                            }

                        }
                        orderBottomDialogFragment.show(
                            parentFragmentManager,
                            orderBottomDialogFragment.tag
                        )

                    } else {


                        val orderBottomDialogFragment: PostMorebtnFragment = PostMorebtnFragment() {

                            when (it) {
                                0 -> {
                                    // 자신의 게시글인 경우



                                }
                                //게시글 삭제


                                1 -> Navigation.findNavController(binding.root)
                                    .navigate(R.id.action_postdetailFragment_to_reportFragment)
                            }

                        }
                        orderBottomDialogFragment.show(
                            parentFragmentManager,
                            orderBottomDialogFragment.tag)


                    }

                    // if 해당 게시글 userId와 로그인 본인 userId  비교하여 다이얼로그 띄우기


//            val orderBottomDialogFragment: PostMorebtnFragment = PostMorebtnFragment() {
//
//                when (it) {
//                    0 -> Navigation.findNavController(binding.root)
//                        .navigate(R.id.action_postdetailFragment_to_reportFragment)
//                    1 -> Navigation.findNavController(binding.root)
//                        .navigate(R.id.action_postdetailFragment_to_reportFragment)
//
//                }
//
//            }
//            orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
                }





                    var postCategory: String = ""

                    when (postdetailcontent!!.result!!.categoryIdx) {

                        11 -> postCategory = "맛집이야기"
                        12 -> postCategory = "질문있어요"
                        13 -> postCategory = "대화해요"
                        14 -> postCategory = "공유해요"
                    }


                    var currentIntestCount = postdetailcontent!!.result!!.likeCount

                    if (postdetailcontent.result.scraped == true) {
                        binding.tvInterestbtn.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.btn_community_heart_clicked, 0, 0, 0
                        )
                        binding.tvInterestbtn.setTextColor(Color.rgb(255, 182, 41))
                        
                    }else{

                        binding.tvInterestbtn.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.btn_community_heart, 0, 0, 0
                        )
                        binding.tvInterestbtn.setTextColor(Color.rgb(134, 134, 134))
                    }


                        // 관심 버튼 클릭 준비
                    binding.tvInterestbtn.setOnClickListener {

                        if (postdetailcontent.result.scraped == false) {
                            postdetailcontent.result.scraped = true
                            binding.tvInterestbtn.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.btn_community_heart_clicked, 0, 0, 0
                            )
                            binding.tvInterestbtn.setTextColor(Color.rgb(255, 182, 41))

                            currentIntestCount = currentIntestCount + 1
                            binding.tvInterstCount.text = currentIntestCount.toString()

                            val addscrap = RecipeScrapBody(myData!!, 4)
                            val apiService = ApiClient.create()
                            val call = apiService.addRecipeBookmark(addscrap)

                            call.enqueue(object : Callback<RecipeScrapResponse> {
                                override fun onResponse(
                                    call: Call<RecipeScrapResponse>,
                                    response: Response<RecipeScrapResponse>
                                ) {
                                    if (response.isSuccessful) {
                                        // 성공적으로 응답이 도착한 경우
                                        val result = response.body()
                                        Log.d("rrrrrrr", "성공 결과: ${result.toString()}")
                                    } else {
                                        val result = response.body()

                                        Log.d("rrrrrrr", "성공 결과: ${result.toString()}")

                                    }
                                }

                                override fun onFailure(
                                    call: Call<RecipeScrapResponse>, t: Throwable
                                ) {
                                    Log.d("rrrrrrr", "오류 처리: ${t.toString()}")
                                }
                            })

                        } else {
                            postdetailcontent.result.scraped = false

                            currentIntestCount = currentIntestCount - 1
                            binding.tvInterstCount.text = currentIntestCount.toString()

                            binding.tvInterestbtn.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.btn_community_heart, 0, 0, 0
                            )
                            binding.tvInterestbtn.setTextColor(Color.rgb(134, 134, 134))

                            val addscrap = RecipeScrapBody(myData!!, 4)
                            val apiService = ApiClient.create()
                            val call = apiService.cancelRecipeBookmark(addscrap)

                            call.enqueue(object : Callback<RecipeScrapResponse> {
                                override fun onResponse(
                                    call: Call<RecipeScrapResponse>,
                                    response: Response<RecipeScrapResponse>
                                ) {
                                    if (response.isSuccessful) {
                                        // 성공적으로 응답이 도착한 경우
                                        val result = response.body()
                                        Log.d("bbbbb", "성공 결과: ${result.toString()}")
                                    } else {

                                    }
                                }

                                override fun onFailure(
                                    call: Call<RecipeScrapResponse>, t: Throwable
                                ) {
                                    Log.d("bbbbb", "오류 처리: ${t.toString()}")
                                }
                            })

                        }

                    }


                    binding.tvCategory.text = postCategory
                    binding.tvAuthor.text = postdetailcontent!!.result!!.userIdx.toString()
                    binding.tvTime.text = postdetailcontent!!.result!!.createAt.toString()
                    binding.tvPosttitle.text = postdetailcontent!!.result!!.title
                    binding.tvPostcontent.text = postdetailcontent!!.result!!.contents
                    binding.tvInterstCount.text = postdetailcontent!!.result!!.likeCount.toString()

                    if (postdetailcontent!!.result!!.paths.isNullOrEmpty()) {
                        binding.rvPostimg.isVisible = false

                    } else {
                        binding.rvPostimg.isVisible = true
                        imageAdapter =
                            PostDetailImageAdapter(
                                postdetailcontent!!.result!!.paths,
                                requireContext()
                            )
                        binding.rvPostimg.adapter = imageAdapter
                        binding.rvPostimg.layoutManager = LinearLayoutManager(requireContext())
                    }

                    binding.tvViewCount.text = postdetailcontent!!.result!!.viewCount.toString()
                    getComment(postdetailcontent!!.result!!.comments)

                    binding.ivAddComment.setOnClickListener {

                        var commentadd = CommentAddItems(
                            postdetailcontent!!.result!!.postIdx,
                            postdetailcontent!!.result!!.userIdx // 현재 로그인한 유저의 인덱스를 나중에 넣어야함
                            ,
                            0,
                            binding.etWritecomment.text.toString()
                        )
                        addComment(commentadd)

                        binding.etWritecomment.text.clear()
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

    fun deletepost(postIdx: Int) {

        val apiService = ApiClient.create() // ApiService를 생성하는 방법에 따라 생성
        val call = apiService.deletePost(postIdx)

        Log.d("hhhhh",postIdx.toString())

        call.enqueue(object : Callback<CommonPostResult> {
            override fun onResponse(
                call: Call<CommonPostResult>, response: Response<CommonPostResult>
            ) {
                if (response.isSuccessful) {
                    // 성공적으로 응답이 도착한 경우
                    val result = response.body()
                    // 처리 로직 추가
                    Log.d("hhhhhh", "성공 결과: ${result.toString()}")

                } else {
                    // 오류 처리 로직 추가
                    val result = response.body()

                    Log.d("hhhhhh", "실패 결과: ${result.toString()}")


                }
            }

            override fun onFailure(call: Call<CommonPostResult>, t: Throwable) {
                // 오류 처리 로직 추가
                Log.d("hhhhhh", "오류 처리: ${t.toString()}")

            }
        })
    }

    fun addComment(commentaddItem: CommentAddItems) {


        val call2 = apiClient.addCommunityComment(commentaddItem)

        call2.enqueue(object : Callback<CommentAddResult> {
            override fun onResponse(
                call: Call<CommentAddResult>, response: Response<CommentAddResult>
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
                call: Call<CommunityDetailDTO>, response: Response<CommunityDetailDTO>
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


    fun getComment(commentIdList: List<Int>) {

        val commentlist: MutableList<CommentItems> = mutableListOf()


        for (i in commentIdList) {

            apiClient.getCommunityComment(i).enqueue(object : Callback<CommunityComment> {
                override fun onResponse(
                    call: Call<CommunityComment>, response: Response<CommunityComment>
                ) {
                    commentlist.add(response.body()!!.result)


                    postAdapter = CommunityCommentAdapter(commentlist, parentFragmentManager)
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