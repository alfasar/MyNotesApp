package com.example.mynotesapp.appdata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    init {
        val userDao = AppDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
    }

    fun getUserEmail(email: String): User? {
        var checker: User? = null
        viewModelScope.launch(Dispatchers.IO) {
            checker = repository.getUserEmail(email)
        }
        return checker
    }
}