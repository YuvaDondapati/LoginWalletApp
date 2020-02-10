package com.yuvademos.loginwalletapp.model

import android.util.Patterns

class LoginUser(val emailAddress: String, val password: String) {

    fun isEmailValid(): Boolean
    {
        return Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
    }


    fun isPasswordLengthGreaterThan5(): Boolean
    {
        return password.length > 5
    }

}