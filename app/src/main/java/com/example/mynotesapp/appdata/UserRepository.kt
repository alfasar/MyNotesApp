package com.example.mynotesapp.appdata

class UserRepository(private val uDao: UserDao) {

    val users = uDao.getAllUsers()

    suspend fun addUserToDatabase(user: User) {
        return uDao.addUserToDatabase(user)
    }

    suspend fun getUserEmail(email: String): User? {
        return uDao.getUserEmail(email)
    }
}