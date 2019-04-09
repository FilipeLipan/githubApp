package com.filipelipan.githubapp.data.api.response

import com.google.gson.annotations.SerializedName

class BaseResponse<T>(
    @SerializedName("total_count")
    val totalCount:Long,
    @SerializedName("items")
    val items: List<T>)