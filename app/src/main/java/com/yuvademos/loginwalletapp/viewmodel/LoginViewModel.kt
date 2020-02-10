package com.yuvademos.loginwalletapp.viewmodel

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuvademos.loginwalletapp.model.LoginUser

class LoginViewModel: ViewModel() {

    val emailAddress = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private var userData = MutableLiveData<LoginUser>()

    var loading = MutableLiveData<Boolean>()

    fun getUser(): MutableLiveData<LoginUser>? {
        return  userData
    }

//    fun onClick() {
//        loading.value = true
//        userData.value = LoginUser(emailAddress.value!!, password.value!!)
//    }

    fun onLogin() {
        userData.value = LoginUser(emailAddress.value!!, password.value!!)
    }

}