package com.example.notes.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.notes.R
import com.example.notes.databinding.FragmentLoginBinding
import com.example.notes.ui.base.BaseFragment
import com.example.notes.ui.viewmodel.LoginViewModel
import com.example.notes.utils.addReplaceFragment

/**
 * Created by Limbachiya Himanshu.
 */
class LoginFragment : BaseFragment() {

    private lateinit var mBinding: FragmentLoginBinding
    private val mLoginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        // show and hide progressbar
        mLoginViewModel.isLoginSuccess.observe(this) {
            if (it != null && it) {
                activity?.addReplaceFragment(
                    R.id.fl_my_container,
                    HomeFragment(),
                    addFragment = false,
                    addToBackStack = false
                )
            }
        }

        // show error message in snack bar
        mLoginViewModel.mErrorMessage.observe(this) {
            if (!it.isNullOrEmpty()) {
                mBaseActivity.showErrorMsg(mBinding.clLoginMain, it)
            }
        }

        // show error message in email textInputField
        mLoginViewModel.errorEmail.observe(this) {
            mBinding.txtInputFieldEmail.error = it
        }

        // show error message in password textInputField
        mLoginViewModel.errorPassword.observe(this) {
            mBinding.txtInputFieldPassword.error = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentLoginBinding.inflate(layoutInflater)
        mBinding.data = mLoginViewModel
        mBinding.executePendingBindings()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
    }

    // view click listener
    private fun clickListener() {
        mBinding.btnLogin.setOnClickListener {
            mLoginViewModel.checkValidation()
        }
    }
}