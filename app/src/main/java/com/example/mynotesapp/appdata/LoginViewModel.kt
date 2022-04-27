package com.example.mynotesapp.appdata

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class LoginViewModel(private val repository: UserRepository, application: Application):
    AndroidViewModel(application) {

}