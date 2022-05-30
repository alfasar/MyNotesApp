package com.example.mynotesapp.appdata.item

class ItemRepository(private val iDao: ItemDao) {

    val items = iDao.readAllData()

    suspend fun addItemToDatabase(item: Item) {
        return iDao.addItemToDatabase(item)
    }

    suspend fun deleteAll() {
        return iDao.deleteAll()
    }
}