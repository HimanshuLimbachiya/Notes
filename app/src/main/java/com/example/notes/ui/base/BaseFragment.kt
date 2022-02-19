package com.example.notes.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.notes.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Limbachiya Himanshu.
 */
@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    lateinit var mBaseActivity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mBaseActivity = context as BaseActivity
    }
}