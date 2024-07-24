package com.example.pa4a.View

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pa4a.api.ApiClient
import com.example.pa4a.core.SessionManager
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
class UserProfileViewModel (application: Application) : AndroidViewModel(application) {
    private val sessionManager = SessionManager(application)
    private val _user = MutableLiveData<UserResponse>()
    val user: LiveData<UserResponse> = _user
}