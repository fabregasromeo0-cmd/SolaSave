package com.example.solasave.data.repository.users

import com.example.solasave.data.models.users.UserInfo
import com.example.solasave.data.repository.weather.supabase
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class UserRepository : UserService {

    override suspend fun createUser(user: UserInfo): UserInfo {
        return supabase.from("Users").insert(user) {
            select()
        }.decodeSingle<UserInfo>()
    }

    override suspend fun getAllUsers(): List<UserInfo> {
        return supabase.from("Users").select().decodeList<UserInfo>()
    }

    override suspend fun getUser(id: Int): UserInfo? {
        return supabase.from("Users").select {
            filter {
                eq("id", id)
            }
        }.decodeSingleOrNull<UserInfo>()
    }

    override suspend fun updateUser(user: UserInfo): UserInfo {
        return supabase.from("Users").update(user) {
            select()
            filter {
                eq("id", user.id ?: 0)
            }
        }.decodeSingle<UserInfo>()
    }

    override suspend fun deleteUser(id: Int): Boolean {
        try {
            supabase.from("Users").delete {
                filter {
                    eq("id", id)
                }
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }
}
