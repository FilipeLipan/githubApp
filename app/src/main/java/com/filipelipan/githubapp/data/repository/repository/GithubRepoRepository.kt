package com.filipelipan.githubapp.data.repository.repository

import com.filipelipan.githubapp.data.api.RestApi
import com.filipelipan.githubapp.data.entity.GithubRepositoryBO
import com.filipelipan.githubapp.data.entity.PagedResponseBO
import com.filipelipan.githubapp.data.mapper.toGithubRepositoryBO
import io.coroutines.cache.core.CachePolicy
import io.coroutines.cache.core.CoroutinesCache
import java.util.concurrent.TimeUnit


class GithubRepoRepository(private val restApi: RestApi, private val coroutinesCache: CoroutinesCache){

    suspend fun searchRepos(query: String,pageNumber:Long): PagedResponseBO<GithubRepositoryBO> {

        val repositoriesPagedResponse = coroutinesCache
            .asyncCache({
                restApi.searchRepos(query,PAG_ECOUNT,pageNumber)
            },
                query + pageNumber.toString(),
                CachePolicy.LifeCache(1, TimeUnit.HOURS)).await()

        val pagedResponseBO :PagedResponseBO<GithubRepositoryBO> = PagedResponseBO()

        pagedResponseBO.items = repositoriesPagedResponse.items.toGithubRepositoryBO()
        pagedResponseBO.page = pageNumber
        pagedResponseBO.totalCount = repositoriesPagedResponse.totalCount

        return pagedResponseBO;
    }
}