package com.example.pa4a.view


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pa4a.api.ApiClient
import com.example.pa4a.core.SessionManager
import com.example.pa4a.dataModel.UserIdRequest
import com.example.pa4a.dataModel.UserIdResponse
import com.example.pa4a.dataModel.UserResponse
import com.example.pa4a.dataModel.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val sessionManager = SessionManager(application)
    private val _user = MutableLiveData<UserResponse>()
    val user: LiveData<UserResponse> = _user

    private val _users = MutableLiveData<List<UserResponse>>()
    val users: LiveData<List<UserResponse>> = _users
    fun getUser() {
        val token = sessionManager.token
        if (token != null) {
            ApiClient.authService.userId(UserIdRequest(token)).enqueue(object : Callback<UserIdResponse> {
                override fun onResponse(call: Call<UserIdResponse>, response: Response<UserIdResponse>) {
                    if (response.isSuccessful) {
                        val userId = response.body()?.user_id
                        if (userId != null) {
                            ApiClient.authService.getUser(userId).enqueue(object : Callback<UserResponse> {
                                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                                    if (response.isSuccessful) {
                                        _user.value = response.body()
                                        sessionManager.saveUserInfo(response.body()!!)
                                        println("infos User: ${response.body()}")
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
    fun getUsers() {
        println("User test")
        ApiClient.userService.getUsers().enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                if (response.isSuccessful) {
                    _users.value = response.body()
                    sessionManager.saveUsers(response.body()!!)
                    println("Users: ${response.body()}")

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
}