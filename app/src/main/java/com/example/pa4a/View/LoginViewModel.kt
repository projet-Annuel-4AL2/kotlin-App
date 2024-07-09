package com.example.pa4a.view

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pa4a.RegisterActivity
import com.example.pa4a.api.ApiClient
import com.example.pa4a.core.SessionManager
import com.example.pa4a.dataModel.LoginRequest
import com.example.pa4a.dataModel.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val sessionManager = SessionManager(application)
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login() {
        val username = username.value ?: ""
        val password = password.value ?: ""
        val device = "Android"
        val loginRequest = LoginRequest(username, password , device)
        ApiClient.authService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    _loginResult.value = LoginResult(success = true, token = response.body()?.token)

                    val token = response.body()?.token
                    sessionManager.token = response.body()?.token
                    println("Token: $token")
                    //redirect to the next activity soit home

                } else {
                    _loginResult.value = LoginResult(success = false, errorMessage = "Invalid credentials")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _loginResult.value = LoginResult(success = false, errorMessage = t.message)
            }
        })
    }

    fun register(context: Context) {
        val intent = Intent(context, RegisterActivity::class.java)
        context.startActivity(intent)
    }
}

data class LoginResult(val success: Boolean, val token: String? = null, val errorMessage: String? = null)
