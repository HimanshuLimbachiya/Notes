package com.example.notes.app

import android.app.Application
import com.example.notes.utils.firebase.FirebaseHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApp : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseHelper.initFirebase()
    }
}