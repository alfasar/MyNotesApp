package com.example.mynotesapp.appdata

class UserRepository(private val uDao: UserDao/*, private var iDao: ItemDao*/) {

    val users = uDao.getAllUsers()
    //val items = iDao.readAllData()

    suspend fun addUserToDatabase(user: User) {
        return uDao.addUserToDatabase(user)
    }

    suspend fun getUserEmail(email: String): User? {
        return uDao.getUserEmail(email)
    }

    /*suspend fun addItemToDatabase(item: Item) {
        return iDao.addItemToDatabase(item)
    }*/
}