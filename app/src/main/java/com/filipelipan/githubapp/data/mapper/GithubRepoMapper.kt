package com.filipelipan.githubapp.data.mapper

import com.filipelipan.githubapp.data.api.response.BaseResponse
import com.filipelipan.githubapp.data.api.response.RepositoryResponse
import com.filipelipan.githubapp.data.entity.GithubRepositoryBO

fun List<RepositoryResponse>.toGithubRepositoryBO(): List<GithubRepositoryBO> = this.map {

    GithubRepositoryBO(name = it.name ?: "",
        url = it.url ?: "",
        language = it.language ?: "",
        forks = it.forks ?: "",
        stargazersCount = it.stargazersCount ?: "",
        watchersCount = it.watchersCount ?: "",
        description = it.description ?: "",
        ownerName = it.owner.login ?: "",
        ownerDescription = it.owner.description ?: "",
        ownerAvatarUrl = it.owner.avatarUrl ?: "")
}