package com.yazdanmanesh.githubcompose.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey val userId: String = "",
    val avatarUrl: String = "",
    val htmlUrl: String = "",
)
