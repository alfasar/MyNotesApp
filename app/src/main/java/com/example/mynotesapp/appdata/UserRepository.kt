package com.example.mynotesapp.appdata

class UserRepository(private val dao: UserDao) {

    val users = dao.getAllUsers()

    suspend fun addUserToDatabase(user: User) {
        return dao.addUserToDatabase(user)
    }

    suspend fun getUserEmail(email: String): User? {
        return dao.getUserEmail(email)
    }
}