package com.example.notes.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Limbachiya Himanshu.
 */
@HiltViewModel
open class BaseViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    var mProgressBar = MutableLiveData<Boolean>()
    var mErrorMessage = MutableLiveData<String>()

    // show loader
    fun showProgressbar() {
        mProgressBar.value = true
    }

    // hide loader
    fun hideProgressbar() {
        mProgressBar.value = false
    }
}