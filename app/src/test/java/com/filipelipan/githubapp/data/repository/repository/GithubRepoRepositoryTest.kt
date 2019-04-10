package com.filipelipan.githubapp.data.repository.repository

import com.filipelipan.githubapp.data.api.RestApi
import com.filipelipan.githubapp.data.api.response.BaseResponse
import com.filipelipan.githubapp.data.api.response.RepositoryResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.coroutines.cache.core.CoroutinesCache
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test

class GithubRepoRepositoryTest {

    private lateinit var githubRepoRepository :GithubRepoRepository
    private lateinit var restApiMock:RestApi
    private lateinit var cacheMock:CoroutinesCache
    private lateinit var baseResponse: BaseResponse<RepositoryResponse>

    val query = "query"
    val pageCount = 20L
    val pageNumber = 2L

    @Before
    fun setup(){
        cacheMock = CoroutinesCache(mock(), true)
        baseResponse = mock()
        restApiMock = mock {
            on { searchRepos(query, pageCount, pageNumber) }
                .thenReturn(CompletableDeferred(baseResponse))
        }
        githubRepoRepository = GithubRepoRepository(restApiMock, cacheMock)
    }

    @Test
    fun checkIfApiIsCalled(){
        val result = runBlocking {
            whenever(restApiMock.searchRepos(query, pageCount, pageNumber))
                .thenReturn(CompletableDeferred(baseResponse))
            githubRepoRepository.searchRepos(query,pageNumber)
        }
        verify(restApiMock).searchRepos(query,pageCount, pageNumber)
    }
}