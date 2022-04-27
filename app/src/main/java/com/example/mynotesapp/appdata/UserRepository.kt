package com.example.mynotesapp.appdata

class UserRepository(private val dao: UserDao) {

    val users = dao.getAllUsers()

    suspend fun insert(user: User) {
        return dao.insert(user)
    }

    suspend fun getUserEmail(email: String): User? {
        return dao.getUserEmail(email)
    }
}