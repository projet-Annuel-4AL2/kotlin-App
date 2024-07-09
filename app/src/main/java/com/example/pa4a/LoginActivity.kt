package com.example.pa4a

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.pa4a.core.SessionManager
import com.example.pa4a.view.LoginViewModel
import com.example.pa4a.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sessionManager = SessionManager(applicationContext)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = loginViewModel

        loginViewModel.loginResult.observe(this, Observer { result ->
            if (result.success) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                // Navigate to the next activity homeActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Login failed: ${result.errorMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
