package com.example.solasave.data.models.users

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val id:Int?=null,
    val email:String,
    val firstName:String,
    val lastName:String,
    val password: String
)
