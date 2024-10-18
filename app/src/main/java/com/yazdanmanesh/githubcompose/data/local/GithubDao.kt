package com.yazdanmanesh.githubcompose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yazdanmanesh.githubcompose.data.local.entities.RepoEntity
import com.yazdanmanesh.githubcompose.data.local.entities.UserDetailsEntity
import com.yazdanmanesh.githubcompose.data.local.entities.UserEntity

@Dao
interface GithubDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repos: List<RepoEntity>)

    @Query("SELECT * FROM User")
    suspend fun getUsers(): List<UserEntity>

    @Query("SELECT * FROM Repo where userId = :id")
    suspend fun getRepos(id:String): List<RepoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetail(userDetailsEntity: UserDetailsEntity)

    @Query("SELECT * FROM UserDetails where id = :id")
    suspend fun getUserDetail(id:String): UserDetailsEntity?

}
