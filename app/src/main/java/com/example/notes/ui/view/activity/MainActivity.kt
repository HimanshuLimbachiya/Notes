package com.example.notes.ui.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import com.example.notes.R
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.ui.base.BaseActivity
import com.example.notes.ui.view.fragments.HomeFragment
import com.example.notes.utils.MySimpleOnGestureListener
import com.example.notes.utils.MySimpleOnGestureListenerInterface
import com.example.notes.utils.addReplaceFragment
import com.example.notes.utils.bottomsheet.AddNoteBottomSheet
import com.example.notes.ui.view.fragments.LoginFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addReplaceFragment(
            R.id.fl_my_container,
            HomeFragment(),
            addFragment = false,
            addToBackStack = false
        )
    }
}