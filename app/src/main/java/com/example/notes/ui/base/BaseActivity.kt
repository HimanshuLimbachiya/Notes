package com.example.notes.ui.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.notes.R
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Limbachiya Himanshu.
 */
@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    private var mProgressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initProgressDialog()
    }

    // initialize api progress dialogs
    private fun initProgressDialog() {
        mProgressDialog = Dialog(this)
        mProgressDialog?.setContentView(R.layout.dialog_progressbar)
        mProgressDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mProgressDialog?.setCancelable(false)
    }

    // show loader
    fun showProgressBar() {
        mProgressDialog?.isShowing?.let {
            mProgressDialog?.show()
        }
    }

    // hide loader
    fun hideProgressBar() {
        mProgressDialog?.dismiss()
    }

    // show error message using snack bar
    fun showErrorMsg(mView: View, errorMessage: String) {
        val snack: Snackbar = Snackbar.make(mView, errorMessage, Snackbar.LENGTH_SHORT)
        val view = snack.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        view.textAlignment = View.TEXT_ALIGNMENT_CENTER
        snack.show()
    }
}