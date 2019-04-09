package com.filipelipan.githubapp.data.repository.repository

import android.content.Context
import com.filipelipan.githubapp.data.api.RestApi
import com.filipelipan.githubapp.data.entity.GithubRepositoryBO
import com.filipelipan.githubapp.data.mapper.toGithubRepositoryBO
import io.coroutines.cache.core.CachePolicy
import io.coroutines.cache.core.CoroutinesCache
import java.util.concurrent.TimeUnit


class GithubRepoRepository(private val restApi: RestApi, context: Context){

    val PAGE_COUNT = "20"

    suspend fun searchRepos(query: String,pageNumber:String): List<GithubRepositoryBO> {

        return restApi.loadRepositories(query,PAGE_COUNT,pageNumber)
            .await()
            .items.toGithubRepositoryBO()
    }
}