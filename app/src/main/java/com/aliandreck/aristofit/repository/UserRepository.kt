package com.aliandreck.aristofit.repository

import com.aliandreck.aristofit.data.UserDao
import com.aliandreck.aristofit.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }
}