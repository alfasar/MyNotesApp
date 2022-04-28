package com.example.mynotesapp.appdata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegViewModel(application: Application): AndroidViewModel(application) {
    private val repository: UserRepository
        init {
            val userDao = AppDatabase.getInstance(application).userDao()
            repository = UserRepository(userDao)
        }
    fun addUserToDatabase(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUserToDatabase(user)
        }
    }
}