package com.example.mynotesapp.presentation.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mynotesapp.appdata.AppDatabase
import com.example.mynotesapp.appdata.item.Item
import com.example.mynotesapp.appdata.item.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ItemRepository
    val listItems: LiveData<MutableList<Item>>

    init {
        val itemDao = AppDatabase.getInstance(application).itemDao()
        repository = ItemRepository(itemDao)
        listItems = repository.items
    }

    fun addItemToDatabase(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItemToDatabase(item)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}