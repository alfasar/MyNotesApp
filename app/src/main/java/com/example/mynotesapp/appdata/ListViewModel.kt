package com.example.mynotesapp.appdata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ItemRepository

    init {
        val itemDao = AppDatabase.getInstance(application).itemDao()
        repository = ItemRepository(itemDao)
    }
    fun addItemToDatabase(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItemToDatabase(item)
        }
    }
}