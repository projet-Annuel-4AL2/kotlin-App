package com.example.pa4a.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pa4a.Adapter.MyAdapter
import com.example.pa4a.Adapter.PostAdapter
import com.example.pa4a.R
import com.example.pa4a.core.SessionManager
import com.example.pa4a.dataModel.UserPostResponse
import com.example.pa4a.dataModel.UserResponse
import com.example.pa4a.view.MainViewModel

class HomeFragment : Fragment() {
    private lateinit var sessionManager: SessionManager
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var postRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        sessionManager = SessionManager(requireContext())
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        recyclerView = view.findViewById(R.id.recycler_view)
        postRecyclerView = view.findViewById(R.id.post_recycler_view)

        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager

        val postLayoutManager = LinearLayoutManager(requireContext())
        postRecyclerView.layoutManager = postLayoutManager


        mainViewModel.user.observe(viewLifecycleOwner, { userResponse ->
            println("Observed user response: $userResponse")
        })

        mainViewModel.getUser()


        mainViewModel.users.observe(viewLifecycleOwner, { usersResponse ->
            println("Observed users response: $usersResponse")
            recyclerView.adapter = MyAdapter(usersResponse ?: emptyList())
            updatePostAuthors(mainViewModel.userPosts.value, usersResponse)
        })
        mainViewModel.getUsers()

        mainViewModel.groups.observe(viewLifecycleOwner, { groupsResponse ->
            println("Observed groups response: $groupsResponse")
        })

        mainViewModel.getGroups()

        mainViewModel.userFollowers.observe(viewLifecycleOwner, { UsersResponse ->
            println("Observed user followers response: $UsersResponse")
        })

        mainViewModel.getUserFollowers()

        mainViewModel.userFollowings.observe(viewLifecycleOwner, { UsersResponse ->
            println("Observed user followings response: $UsersResponse")
        })

        mainViewModel.getUserFollowings()



        mainViewModel.userPosts.observe(viewLifecycleOwner, { userPostsResponse ->
            println("Observed user posts response: $userPostsResponse")
            updatePostAuthors(userPostsResponse, mainViewModel.users.value)
        })

        mainViewModel.getUserPosts()

        val user = sessionManager.userInfo
        val users = sessionManager.users
        val groups = sessionManager.groups
        val userFollowers = sessionManager.userFollowers
        val userFollowings = sessionManager.userFollowings
        val userPosts = sessionManager.userPosts

        //println("User info: $user")
        //println("User all: $users")
        //println("Groups all: $groups")
        //println("User followers: $userFollowers")
        //println("User followings: $userFollowings")
        //println("User posts: $userPosts")


        return view
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
