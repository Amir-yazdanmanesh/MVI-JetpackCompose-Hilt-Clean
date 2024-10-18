package com.yazdanmanesh.githubcompose.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Repo")
data class RepoEntity(
    @PrimaryKey val id: Long = 0,
    val userId: String,
    val name: String = "",
    val description: String? = null,
    val watchersCount: Int = 0,
    val forksCount: Int = 0,
    val stargazersCount: Int = 0,
    val language: String? = null,
    val htmlUrl: String = "",
)
