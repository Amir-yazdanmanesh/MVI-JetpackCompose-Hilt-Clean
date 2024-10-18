package com.yazdanmanesh.githubcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yazdanmanesh.githubcompose.data.local.entities.RepoEntity
import com.yazdanmanesh.githubcompose.data.local.entities.UserDetailsEntity
import com.yazdanmanesh.githubcompose.data.local.entities.UserEntity

@Database(
    entities = [RepoEntity::class, UserEntity::class, UserDetailsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GithubDatabase : RoomDatabase() {
    abstract val dao: GithubDao
}
