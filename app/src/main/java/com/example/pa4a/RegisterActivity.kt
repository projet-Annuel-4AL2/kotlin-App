package com.example.pa4a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import com.example.pa4a.view.RegisterViewModel
import com.example.pa4a.view.LoginViewModel
import com.example.pa4a.databinding.ActivityRegisterBinding
class RegisterActivity : AppCompatActivity(){
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.lifecycleOwner = this
        binding.viewModel = registerViewModel
        registerViewModel.registerResult.observe(this, Observer { result ->
            if (result.success) {
                Toast.makeText(this, "Register successful!", Toast.LENGTH_SHORT).show()
                // Navigate to the next activity
            } else {
                Toast.makeText(this, "Register failed: ${result.errorMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}