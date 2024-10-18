package com.yazdanmanesh.githubcompose.domain.models

data class Repo(
    val id: Long,
    val userId: String,
    val name: String = "",
    val description: String? = null,
    val watchersCount: Int = 0,
    val forksCount: Int = 0,
    val stargazersCount: Int = 0,
    val language: String? = null,
    val htmlUrl: String = "",
)

object RepoPreview {
    val repo: Repo
        get() = Repo(
            name = "GithubApi + Compose + MVI",
            description = "Sample project created using Jetpack compose + MVI",
            watchersCount = 100,
            forksCount = 123,
            stargazersCount = 90,
            language = "Kotlin",
            id = 0,
            userId = "",
        )
}
