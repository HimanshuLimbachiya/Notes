package com.example.notes.ui.viewmodel

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.example.notes.R
import com.example.notes.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Limbachiya Himanshu.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(var context: Application) : BaseViewModel(context) {

    var email = MutableLiveData("")
    var password = MutableLiveData("")

    var errorEmail = MutableLiveData("")
    var errorPassword = MutableLiveData("")

    var isLoginSuccess = MutableLiveData(false)

    fun checkValidation() {
        if (email.value.isNullOrBlank()) {
            errorEmail.value = context.resources.getString(R.string.msg_enter_email)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value!!).matches()) {
            errorEmail.value = context.resources.getString(R.string.msg_email_validation)
        } else if (password.value.isNullOrBlank()) {
            errorEmail.value = ""
            errorPassword.value = context.resources.getString(R.string.msg_enter_password)
        } else isLoginSuccess.value = true
    }
}