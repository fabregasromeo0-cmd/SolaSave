package com.example.solasave.data.repository.users

import com.example.solasave.data.models.users.UserInfo

interface UserService {
    suspend fun createUser(user: UserInfo): UserInfo
    suspend fun getAllUsers(): List<UserInfo>
    suspend fun getUser(id: Int): UserInfo?
    suspend fun updateUser(user: UserInfo): UserInfo
    suspend fun deleteUser(id: Int): Boolean
}
