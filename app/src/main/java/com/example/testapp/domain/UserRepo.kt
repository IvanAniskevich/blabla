package com.example.testapp.domain

import com.example.testapp.data.user.UserDao
import com.example.testapp.data.user.UserModel
import kotlinx.coroutines.flow.Flow


class UserRepo(private val userDao: UserDao) {
    suspend fun insertUser(user: UserModel) = userDao.insert(user)

    suspend fun getUserByEmail(email: String): UserModel {
        return userDao.getUserByEmail(email)
    }
}