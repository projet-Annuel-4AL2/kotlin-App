package com.example.pa4a.view


import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pa4a.LoginActivity
import com.example.pa4a.api.ApiClient
import com.example.pa4a.core.SessionManager
import com.example.pa4a.dataModel.RegisterRequest
import com.example.pa4a.dataModel.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val sessionManager = SessionManager(application)
    val email = MutableLiveData<String>()
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    fun register() {
        val email = email.value ?: ""
        val username = username.value ?: ""
        val password = password.value ?: ""
        val device = "Android"

        val registerRequest = RegisterRequest(email ,username, password , device)
        ApiClient.authService.subscribe(registerRequest).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    _registerResult.value = RegisterResult(success = true, token = response.body()?.token)
                    val token = response.body()?.token
                    sessionManager.token = response.body()?.token
                } else {
                    _registerResult.value = RegisterResult(success = false, errorMessage = "Invalid credentials")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _registerResult.value = RegisterResult(success = false, errorMessage = t.message)
            }
        })
    }

    fun login (context: Context){
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }
}


data class RegisterResult(val success: Boolean, val token: String? = null, val errorMessage: String? = null)