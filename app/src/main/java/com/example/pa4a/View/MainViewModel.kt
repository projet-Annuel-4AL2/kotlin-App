package com.example.pa4a.view


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pa4a.api.ApiClient
import com.example.pa4a.core.SessionManager
import com.example.pa4a.dataModel.GroupPostResponse
import com.example.pa4a.dataModel.GroupResponse
import com.example.pa4a.dataModel.UserFollowersResponse
import com.example.pa4a.dataModel.UserFollowingsResponse
import com.example.pa4a.dataModel.UserIdRequest
import com.example.pa4a.dataModel.UserIdResponse
import com.example.pa4a.dataModel.UserPostResponse
import com.example.pa4a.dataModel.UserPostsResponse
import com.example.pa4a.dataModel.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val sessionManager = SessionManager(application)
    private val _user = MutableLiveData<UserResponse>()
    val user: LiveData<UserResponse> = _user


    fun getUser() {
        val token = sessionManager.token
        //println("getUser: token is $token")
        if (token != null) {
            ApiClient.authService.userId(UserIdRequest(token)).enqueue(object : Callback<UserIdResponse> {
                override fun onResponse(call: Call<UserIdResponse>, response: Response<UserIdResponse>) {
                    if (response.isSuccessful) {
                        val userId = response.body()?.user_id
                        sessionManager.userId = userId
                        println("getUser: userId is $userId")
                        if (userId != null) {
                            ApiClient.authService.getUser(userId).enqueue(object : Callback<UserResponse> {
                                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                                    if (response.isSuccessful) {
                                        _user.value = response.body()
                                        sessionManager.saveUserInfo(response.body()!!)
                                        //println("infos User: ${response.body()}")
                                    }
                                }

                                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                                    // Handle failure
                                }
                            })
                        }
                    }
                }

                override fun onFailure(call: Call<UserIdResponse>, t: Throwable) {
                    // Handle failure
                }
            })
        }
    }

    private val _users = MutableLiveData<List<UserResponse>>()
    val users: LiveData<List<UserResponse>> = _users
    fun getUsers() {
        ApiClient.userService.getUsers().enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                if (response.isSuccessful) {
                    _users.value = response.body()
                    sessionManager.saveUsers(response.body()!!)
                    //println("Users: ${response.body()}")

                }else{
                    println("Error: ${response.errorBody()}")

                }
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                // Handle failure
                println("getUsers onFailure: ${t.message}")
            }
        })
    }

    private val _groups = MutableLiveData<List<GroupResponse>>()
    val groups: LiveData<List<GroupResponse>> = _groups
    fun getGroups() {
        ApiClient.groupService.getGroups().enqueue(object : Callback<List<GroupResponse>> {
            override fun onResponse(call: Call<List<GroupResponse>>, response: Response<List<GroupResponse>>) {
                if (response.isSuccessful) {

                    _groups.value = response.body()
                    sessionManager.saveGroups(response.body()!!)
                    //println("Groups: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<List<GroupResponse>>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private val _groupInfo = MutableLiveData<GroupResponse>()
    val groupInfo: LiveData<GroupResponse> = _groupInfo
    fun getGroupInfo(groupId: Int) {
        ApiClient.groupService.getGroup(groupId).enqueue(object : Callback<GroupResponse> {
            override fun onResponse(call: Call<GroupResponse>, response: Response<GroupResponse>) {
                if (response.isSuccessful) {
                    _groupInfo.value = response.body()
                    //println("GroupInfo: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<GroupResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }


    private val _groupInfoPosts = MutableLiveData<List<GroupPostResponse>>()
    val groupInfoPosts: LiveData<List<GroupPostResponse>> = _groupInfoPosts

    fun getGroupInfoPosts(groupId: Int) {
        ApiClient.groupService.getGroupPosts(groupId).enqueue(object : Callback<List<GroupPostResponse>> {
            override fun onResponse(call: Call<List<GroupPostResponse>>, response: Response<List<GroupPostResponse>>) {
                if (response.isSuccessful) {
                    _groupInfoPosts.value = response.body()
                    //println("GroupInfoPosts: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<List<GroupPostResponse>>, t: Throwable) {
                // Handle failure
            }
        })
    }




    private val _userFollowers = MutableLiveData<UserFollowersResponse>()
    val userFollowers: LiveData<UserFollowersResponse> = _userFollowers

    fun getUserFollowers() {
        val userId = sessionManager.userId
        //println("getUserFollowers: userId is $userId")
        if (userId != null) {
            ApiClient.userService.getUserFollowers(userId).enqueue(object : Callback<UserFollowersResponse> {
                override fun onResponse(call: Call<UserFollowersResponse>, response: Response<UserFollowersResponse>) {
                    if (response.isSuccessful) {
                        _userFollowers.value = response.body()
                        sessionManager.saveUserFollowers(response.body()!!)
                        //println("Followerssss: ${response.body()?.followers}")
                    }
                }

                override fun onFailure(call: Call<UserFollowersResponse>, t: Throwable) {
                    println("getUserFollowers onFailure: ${t.message}")
                }
            })
        }else{
            println("User id is null")
        }
    }

    private val _userFollowings = MutableLiveData<UserFollowingsResponse>()
    val userFollowings: LiveData<UserFollowingsResponse> = _userFollowings
    fun getUserFollowings(){
        val userId = sessionManager.userId
        //println("getUserFollowings: userId is $userId")
        if (userId != null) {
            ApiClient.userService.getUserFollowings(userId).enqueue(object : Callback<UserFollowingsResponse> {
                override fun onResponse(call: Call<UserFollowingsResponse>, response: Response<UserFollowingsResponse>) {
                    if (response.isSuccessful) {
                        _userFollowings.value = response.body()
                        sessionManager.saveUserFollowings(response.body()!!)
                        println("Followings: ${response.body()?.followings}")
                    }
                }

                override fun onFailure(call: Call<UserFollowingsResponse>, t: Throwable) {
                    println("getUserFollowings onFailure: ${t.message}")
                }
            })
        }else{
            println("User id is null")
        }

    }



    private val _userPosts = MutableLiveData<List<UserPostResponse>>()
    val userPosts: LiveData<List<UserPostResponse>> = _userPosts

    fun getUserPosts() {
        ApiClient.userPostService.getUserPosts().enqueue(object : Callback<List<UserPostResponse>> {
            override fun onResponse(call: Call<List<UserPostResponse>>, response: Response<List<UserPostResponse>>) {
                if (response.isSuccessful) {
                    _userPosts.value = response.body()
                    sessionManager.saveUserPosts(response.body()!!)
                    println("UserPosts: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<List<UserPostResponse>>, t: Throwable) {
                println("getUserPosts onFailure: ${t.message}")
            }
        })
    }

    //getUserPostsByUsername

    private val _userPostsByUsername = MutableLiveData<List<UserPostResponse>>()
    val userPostsByUsername: LiveData<List<UserPostResponse>> = _userPostsByUsername

    fun getUserPostsByUsername(username: String) {
        ApiClient.userPostService.getUserPostsByUsername(username).enqueue(object : Callback<List<UserPostResponse>> {
            override fun onResponse(call: Call<List<UserPostResponse>>, response: Response<List<UserPostResponse>>) {
                if (response.isSuccessful) {
                    _userPostsByUsername.value = response.body()
                    sessionManager.saveUserPostsByUsername(response.body()!!)
                    println("UserPostsByUsername: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<List<UserPostResponse>>, t: Throwable) {
                println("getUserPostsByUsername onFailure: ${t.message}")
            }
        })
    }




}