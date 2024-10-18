package com.yazdanmanesh.githubcompose.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserDetails")
data class UserDetailsEntity(
    @PrimaryKey
    val id: String,
    val avatarUrl: String = "",
    val htmlUrl: String = "",
    val name: String? = null,
    val location: String? = "",
    val blogUrl: String = "",
    val publicRepos: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
)
