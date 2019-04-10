package com.filipelipan.githubapp.data.entity

import java.io.Serializable

class GithubRepositoryBO(
    val id :Long,
    val name :String,
    val url:String,
    val language:String,
    val forks:String,
    val stargazersCount:String,
    val watchersCount:String,
    val description:String,
    val ownerName:String,
    val ownerDescription:String,
    val ownerAvatarUrl:String) : Serializable