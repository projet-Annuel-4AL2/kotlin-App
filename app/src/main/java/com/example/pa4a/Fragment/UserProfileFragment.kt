package com.example.pa4a.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pa4a.Adapter.PostAdapter
import com.example.pa4a.R
import com.example.pa4a.core.SessionManager
import com.example.pa4a.view.MainViewModel
import com.bumptech.glide.Glide
import com.example.pa4a.dataModel.UserPostResponse
import com.example.pa4a.dataModel.UserResponse



class UserProfileFragment : Fragment() {
    private lateinit var sessionManager: SessionManager
    private lateinit var mainViewModel: MainViewModel


    private lateinit var recyclerView: RecyclerView
    private lateinit var postRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)

        sessionManager = SessionManager(requireContext())
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.user.observe(viewLifecycleOwner, { userResponse ->
            println("Observed user response: $userResponse")
        })

        mainViewModel.getUser()
        val user = sessionManager.userInfo
        val username = user?.username
        val userId = user?.id

        val followers = sessionManager.userFollowers
        val following = sessionManager.userFollowings
        val countFollowers = followers?.size
        val countFollowing = following?.size

        print("followessssrs: $followers")



        postRecyclerView = view.findViewById(R.id.post_recycler_view)
        val postLayoutManager = LinearLayoutManager(requireContext())
        postRecyclerView.layoutManager = postLayoutManager

        mainViewModel.userPostsByUsername.observe(viewLifecycleOwner, { userPostsResponse ->


            val modifiedPosts = userPostsResponse?.map { userPost ->
                userPost.apply {
                    if (username != null) {
                        author = username
                    }
                }
            } ?: emptyList()
            println("Modified user posts response: $modifiedPosts")
            postRecyclerView.adapter = PostAdapter(modifiedPosts)
        })

        mainViewModel.getUserPostsByUsername( username!! )


        val userProfileImageUrl = user?.profilePic
        Glide.with(this)
            .load(userProfileImageUrl)
            .placeholder(R.drawable.defaultprofile)
            .into(view.findViewById(R.id.user_profile_image))

        view.findViewById<TextView>(R.id.username_text_view).text = username
        view.findViewById<TextView>(R.id.followers_text_view).text = "$countFollowers Followers"
        view.findViewById<TextView>(R.id.following_text_view).text = "$countFollowing Following"

        return view
        //return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    private fun updatePostAuthors(userPosts: List<UserPostResponse>?, users: List<UserResponse>?) {
        if (userPosts != null && users != null) {
            userPosts.forEach { userPost ->
                userPost.author = users.find { it.id == userPost.author }?.username.toString()
            }
            postRecyclerView.adapter = PostAdapter(userPosts)
        }
    }

}