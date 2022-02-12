package com.example.notes.utils

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import kotlin.math.abs

/**
 * Created by Abhin.
 */

private const val SWIPE_THRESHOLD = 400
private const val SWIPE_VELOCITY_THRESHOLD = 400

class MySimpleOnGestureListener(var mMySimpleOnGestureListenerInterface: MySimpleOnGestureListenerInterface) :
    GestureDetector.SimpleOnGestureListener() {

    override fun onFling(
        motionEvent1: MotionEvent,
        motionEvent2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        var result = false
        try {
            val diffY: Float = motionEvent2.y - motionEvent1.y
            val diffX: Float = motionEvent2.x - motionEvent1.x
            if (abs(diffX) > abs(diffY)) {
                if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        Log.e("TAG", "Swipe Right")
                    } else {
                        Log.e("TAG", "Swipe Left")
                    }
                    result = true
                }
            } else if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    Log.e("TAG", "Swipe Down")
                } else {
                    mMySimpleOnGestureListenerInterface.swipeUp()
                }
                result = true
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return result
    }
}

interface MySimpleOnGestureListenerInterface {
    fun swipeUp()
}