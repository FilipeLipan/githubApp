package com.filipelipan.githubapp.data.api.response

import com.google.gson.annotations.SerializedName

class RepositoryResponse(
    @SerializedName("id")
    val id :Long,
    @SerializedName("name")
    val name :String?,
    @SerializedName("url")
    val url:String?,
    @SerializedName("language")
    val language:String?,
    @SerializedName("forks")
    val forks:String?,
    @SerializedName("stargazers_count")
    val stargazersCount:String?,
    @SerializedName("watchers_count")
    val watchersCount:String?,
    @SerializedName("description")
    val description:String?,
    @SerializedName("owner")
    val owner:OwnerResponse)