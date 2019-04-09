package com.filipelipan.githubapp.data.api


import com.filipelipan.githubapp.data.api.response.BaseResponse
import com.filipelipan.githubapp.data.api.response.RepositoryResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface RestApi {

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String,
                         @Query("per_page") pageCount:Long,
                         @Query("page")pageNumber:Long): Deferred<BaseResponse<RepositoryResponse>>
}


