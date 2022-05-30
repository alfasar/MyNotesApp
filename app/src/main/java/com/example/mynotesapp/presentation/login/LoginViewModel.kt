package com.example.mynotesapp.presentation.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mynotesapp.appdata.AppDatabase
import com.example.mynotesapp.appdata.user.User
import com.example.mynotesapp.appdata.user.UserRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    init {
        val userDao = AppDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
    }

    suspend fun getUserEmail(email: String): User? {
        return repository.getUserEmail(email)
    }
}