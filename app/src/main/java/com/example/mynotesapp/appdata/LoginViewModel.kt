package com.example.mynotesapp.appdata

import android.app.Application
import androidx.lifecycle.AndroidViewModel

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