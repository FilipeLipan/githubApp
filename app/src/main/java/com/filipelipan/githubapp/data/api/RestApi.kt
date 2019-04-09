package com.filipelipan.githubapp.data.api


import com.filipelipan.githubapp.data.api.response.BaseResponse
import com.filipelipan.githubapp.data.api.response.RepositoryResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface RestApi {

    @GET("search/repositories")
    fun loadRepositories(@Query("q") query: String,
                         @Query("per_page") pageCount:String,
                         @Query("page")pageNumber:String): Deferred<BaseResponse<RepositoryResponse>>
}


