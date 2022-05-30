package com.example.mynotesapp.presentation.registration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotesapp.appdata.AppDatabase
import com.example.mynotesapp.appdata.user.User
import com.example.mynotesapp.appdata.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegViewModel(application: Application) : AndroidViewModel(application) {
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