package com.yuvademos.loginwalletapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yuvademos.loginwalletapp.R
import com.yuvademos.loginwalletapp.databinding.ActivityMainBinding
import com.yuvademos.loginwalletapp.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBindings()
        observeUserDetails()
    }

    private fun setupBindings() {
        // Inflate the layout with data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        //Set the viewModel instance
        binding.viewModel = loginViewModel
        // Set the binding's lifecycle (otherwise Live Data won't work properly)
        binding.lifecycleOwner = this

        login.setOnClickListener {
            loading.visibility = View.VISIBLE
            loginViewModel.onLogin()
        }
    }


    private fun observeUserDetails() {

        loginViewModel.getUser()?.observe(this, Observer {
            loading.visibility = View.GONE
            if(TextUtils.isEmpty(it.emailAddress) || !it.isEmailValid()){
                binding.emailAddress.error = "Please Enter email address"
                binding.emailAddress.requestFocus()
            } else if(TextUtils.isEmpty(it.password) || !it.isPasswordLengthGreaterThan5()){
            binding.passwordField.error = "Please Enter password"
            binding.passwordField.requestFocus()
        }else {
                println("details in main activity "+it.emailAddress + it.password)
                binding.emailAddress.setText(it.emailAddress);
                binding.passwordField.setText(it.password);
                Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
            }

        })
    }
}
