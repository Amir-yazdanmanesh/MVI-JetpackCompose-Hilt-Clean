package com.yazdanmanesh.githubcompose.domain.mappers

import com.yazdanmanesh.githubcompose.data.local.entities.RepoEntity
import com.yazdanmanesh.githubcompose.data.local.entities.UserDetailsEntity
import com.yazdanmanesh.githubcompose.data.local.entities.UserEntity
import com.yazdanmanesh.githubcompose.data.remote.model.RepoDto
import com.yazdanmanesh.githubcompose.data.remote.model.UserDetailsDto
import com.yazdanmanesh.githubcompose.domain.models.Repo
import com.yazdanmanesh.githubcompose.domain.models.User
import com.yazdanmanesh.githubcompose.domain.models.UserDetails

class GithubMapper {
    fun fromDtoToEntity(userDto: com.yazdanmanesh.githubcompose.data.remote.model.UserDto): UserEntity = with(userDto) {
        UserEntity(
            userId = userId,
            avatarUrl = avatarUrl,
            htmlUrl = htmlUrl,
        )
    }

    fun fromEntityToDomain(userEntity: UserEntity): User = with(userEntity) {
        User(
            userId = userId,
            avatarUrl = avatarUrl,
            htmlUrl = htmlUrl,
        )
    }

    fun fromDtoToDomain(userDto: com.yazdanmanesh.githubcompose.data.remote.model.UserDto): User = with(userDto) {
        User(
            userId = userId,
            avatarUrl = avatarUrl,
            htmlUrl = htmlUrl,
        )
    }

    fun fromDetailToEntity(userDetails: UserDetails): UserDetailsEntity = with(userDetails) {
        UserDetailsEntity(
            id = id,
            avatarUrl = avatarUrl,
            htmlUrl = htmlUrl,
            name = name,
            location = location,
            blogUrl = blogUrl,
            publicRepos = publicRepos,
            followers = followers,
            following = following,
        )
    }

    fun fromDetailsEntityToDomain(userDetailsEntity: UserDetailsEntity): UserDetails =
        with(userDetailsEntity) {
            UserDetails(
                id = id,
                avatarUrl = avatarUrl,
                htmlUrl = htmlUrl,
                name = name,
                location = location,
                blogUrl = blogUrl,
                publicRepos = publicRepos,
                followers = followers,
                following = following,
            )
        }

    fun fromDtoToDomain(userDetailsDto: UserDetailsDto?, userId:String): UserDetails? {
        return userDetailsDto?.let {
            UserDetails(
                id = userId,
                avatarUrl = it.avatarUrl,
                htmlUrl = it.htmlUrl,
                name = it.name,
                location = it.location,
                blogUrl = it.blogUrl,
                publicRepos = it.publicRepos,
                followers = it.followers,
                following = it.following,
            )
        }
    }


    fun fromRepoDtoToEntity(repoDto: RepoDto, userId: String): RepoEntity = with(repoDto) {
        RepoEntity(
            userId = userId,
            id = id,
            name = name,
            description = description,
            watchersCount = watchersCount,
            forksCount = forksCount,
            stargazersCount = stargazersCount,
            language = language,
            htmlUrl = htmlUrl,
        )
    }

    fun fromRepoEntityToDomain(repoEntity: RepoEntity, userId: String): Repo = with(repoEntity) {
        Repo(
            userId = userId,
            id = id,
            name = name,
            description = description,
            watchersCount = watchersCount,
            forksCount = forksCount,
            stargazersCount = stargazersCount,
            language = language,
            htmlUrl = htmlUrl,
        )
    }

    fun fromRepoDtoToDomain(repoDto: RepoDto, userId: String): Repo = with(repoDto) {
        Repo(
            userId = userId,
            id = id,
            name = name,
            description = description,
            watchersCount = watchersCount,
            forksCount = forksCount,
            stargazersCount = stargazersCount,
            language = language,
            htmlUrl = htmlUrl,
        )
    }
}
