package com.example.notes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.utils.MySimpleOnGestureListener
import com.example.notes.utils.MySimpleOnGestureListenerInterface
import com.example.notes.utils.addReplaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mDetector: GestureDetectorCompat
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
        mDetector = GestureDetectorCompat(this, MySimpleOnGestureListener(listener))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_category, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add_note -> {
                AddNoteBottomSheet().show(
                    supportFragmentManager,
                    AddNoteBottomSheet::class.java.simpleName
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (mDetector.onTouchEvent(event)) true else super.onTouchEvent(event)
    }

    private val listener = object : MySimpleOnGestureListenerInterface {
        override fun swipeUp() {
            AddNoteBottomSheet().show(
                supportFragmentManager,
                AddNoteBottomSheet::class.java.simpleName
            )
        }
    }
}