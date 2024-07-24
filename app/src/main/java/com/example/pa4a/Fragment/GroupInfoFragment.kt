package com.example.pa4a.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pa4a.Adapter.GroupInfoPostAdapter
import com.example.pa4a.Adapter.PostAdapter
import com.example.pa4a.R
import com.example.pa4a.core.SessionManager
import com.example.pa4a.dataModel.GroupPostResponse
import com.example.pa4a.dataModel.UserPostResponse
import com.example.pa4a.dataModel.UserResponse
import com.example.pa4a.view.MainViewModel

class GroupInfoFragment : Fragment() {
    private lateinit var sessionManager: SessionManager
    private lateinit var mainViewModel: MainViewModel


    private lateinit var groupPostRecyclerView: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_group_info, container, false)

        sessionManager = SessionManager(requireContext())
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val users = sessionManager.users

        val groupId = arguments?.getString("groupId")
        if (groupId != null) {
            // Fetch group details using groupId
            println("Group ID received: $groupId")
            //groupId en int et non en string
            //getGroupInfo(groupId) et sa
            mainViewModel.getGroupInfo(groupId.toInt())
            mainViewModel.groupInfo.observe(viewLifecycleOwner) { groupInfo ->
                println("Observed group info: $groupInfo")
                //groupRecyclerView.adapter = GroupInfoAdapter(groupInfo)
                val groupInfoImageUrl = "http://10.0.2.2" + groupInfo?.groupPic
                Glide.with(requireContext())
                    .load(groupInfoImageUrl)
                    .error(R.drawable.groups)
                    .into(view.findViewById(R.id.group_pic))
                view.findViewById<TextView>(R.id.name).text = groupInfo?.name
                view.findViewById<TextView>(R.id.description).text = groupInfo?.description

            }



        }

        groupPostRecyclerView = view.findViewById(R.id.group_post_recycler_view)
        val groupPostLayoutManager = LinearLayoutManager(requireContext())
        groupPostRecyclerView.layoutManager = groupPostLayoutManager

        mainViewModel.groupInfoPosts.observe(viewLifecycleOwner) { groupPosts ->
            println("Observed group posts: $groupPosts")
            groupPostRecyclerView.adapter = GroupInfoPostAdapter(groupPosts)
            updateGroupPostAuthor(users!!, groupPosts)

        }

        mainViewModel.getGroupInfoPosts(groupId!!.toInt())






        return view
    }

    private fun updateGroupPostAuthor(users: List<UserResponse> , groupPosts : List<GroupPostResponse>)
    {
        val updatedGroupPosts = groupPosts.map { groupPost ->
            groupPost.apply {
                val user = users.find { it.id == groupPost.author }
                if (user != null) {
                    author = user.username
                }
            }
        }
        println("Updated group posts: $updatedGroupPosts")
    }


}