package com.example.mynotesapp.appdata.user

class UserRepository(private val uDao: UserDao) {

    suspend fun addUserToDatabase(user: User) {
        return uDao.addUserToDatabase(user)
    }

    suspend fun getUserEmail(email: String): User? {
        return uDao.getUserEmail(email)
    }
}