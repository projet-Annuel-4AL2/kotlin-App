package com.example.pa4a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pa4a.Adapter.MyAdapter
import com.example.pa4a.Adapter.PostAdapter
import com.example.pa4a.core.SessionManager
import com.example.pa4a.view.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.user.observe(this, { userResponse ->
            println("Observed user response: $userResponse")
        })

        mainViewModel.getUser()

        mainViewModel.users.observe(this, { usersResponse ->
            println("Observed users response: $usersResponse")
        })

        mainViewModel.getUsers()


        mainViewModel.groups.observe(this, { groupsResponse ->
            println("Observed groups response: $groupsResponse")
        })

        mainViewModel.getGroups()

        mainViewModel.userFollowers.observe(this, { UsersResponse ->
            println("Observed user followers response: $UsersResponse")
        })

        mainViewModel.getUserFollowers()

        mainViewModel.userFollowings.observe(this, { UsersResponse ->
            println("Observed user followings response: $UsersResponse")
        })

        mainViewModel.getUserFollowings()


        mainViewModel.userPosts.observe(this, { userPostsResponse ->
            println("Observed user posts response: $userPostsResponse")
        })

        mainViewModel.getUserPosts()


        val user = sessionManager.userInfo
        val users = sessionManager.users
        val groups = sessionManager.groups
        val userFollowers = sessionManager.userFollowers
        val userFollowings = sessionManager.userFollowings
        val userPosts = sessionManager.userPosts

        println("User info: $user")
        println("User all: $users")
        println("Groups all: $groups")
        println("User followers: $userFollowers")
        println("User followings: $userFollowings")
        println("User posts: $userPosts")

        //val data = sessionManager.getDataBasics()
        //println("Data basics: $data")

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = MyAdapter(users ?: emptyList())

        val postRecyclerView = findViewById<RecyclerView>(R.id.post_recycler_view)
        //postRecyclerView.layoutManager = LinearLayoutManager(this)
        postRecyclerView.adapter = PostAdapter(userPosts ?: emptyList())

    }
//recuperer le token en session et ensuite utiliser la function userId
    //pour recuperer l'id de l'utilisateur
    //et ensuite utiliser getUser pour recuperer les informations de l'utilisateur

}



