package com.filipelipan.githubapp.data.entity

import com.google.gson.annotations.SerializedName

open class PagedResponseBO<T> {
    open var totalCount: Long = 0
    open var page: Long = 1
    open var items: List<T> = ArrayList()
}