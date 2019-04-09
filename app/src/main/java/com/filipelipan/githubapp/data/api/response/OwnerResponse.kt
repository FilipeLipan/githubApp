package com.filipelipan.githubapp.data.api.response

import com.google.gson.annotations.SerializedName

class OwnerResponse(
    @SerializedName("login")
    val login:String?,
    @SerializedName("description")
    val description:String?,
    @SerializedName("avatar_url")
    val avatarUrl:String?)