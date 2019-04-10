package com.filipelipan.githubapp.data.mapper

import com.filipelipan.githubapp.data.api.response.OwnerResponse
import com.filipelipan.githubapp.data.api.response.RepositoryResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GithubRepoMapperKtTest{

    private lateinit var repositories :List<RepositoryResponse>
    private lateinit var repositoryResponse :RepositoryResponse
    private lateinit var ownerResponse: OwnerResponse

    val id = 1851L
    val name = "testenamerep"
    val url = "www.google.com"
    val language = "language"
    val forks = "56"
    val stargazersCount = "165"
    val watchersCount = "11"
    val description = "66"
    val ownerName = "ownerName"
    val ownerDescription = "description"
    val ownerAvatar = "ownerAvatar"

    @Before
    fun setup(){
        repositories = mock()
        repositoryResponse = mock()
        ownerResponse = mock()


        whenever(repositoryResponse.id).thenReturn(id)
        whenever(repositoryResponse.url).thenReturn(url)
        whenever(repositoryResponse.name).thenReturn(name)
        whenever(repositoryResponse.language).thenReturn(language)
        whenever(repositoryResponse.forks).thenReturn(forks)
        whenever(repositoryResponse.stargazersCount).thenReturn(stargazersCount)
        whenever(repositoryResponse.watchersCount).thenReturn(watchersCount)
        whenever(repositoryResponse.description).thenReturn(description)
        whenever(ownerResponse.login).thenReturn(ownerName)
        whenever(ownerResponse.description).thenReturn(ownerDescription)
        whenever(ownerResponse.avatarUrl).thenReturn(ownerAvatar)
        whenever(repositoryResponse.owner).thenReturn(ownerResponse)

        repositories = arrayListOf(repositoryResponse)
    }

    @Test
    fun checkMapperTransformation(){

        val githubRepositoryBOs = repositories.toGithubRepositoryBO()


        assertEquals(githubRepositoryBOs.get(0).id, id)
        assertEquals(githubRepositoryBOs.get(0).name,name)
        assertEquals(githubRepositoryBOs.get(0).url,url)
        assertEquals(githubRepositoryBOs.get(0).language,language)
        assertEquals(githubRepositoryBOs.get(0).forks,forks)
        assertEquals(githubRepositoryBOs.get(0).stargazersCount,stargazersCount)
        assertEquals(githubRepositoryBOs.get(0).watchersCount,watchersCount)
        assertEquals(githubRepositoryBOs.get(0).description,description)
        assertEquals(githubRepositoryBOs.get(0).ownerName,ownerName)
        assertEquals(githubRepositoryBOs.get(0).ownerDescription,ownerDescription)
        assertEquals(githubRepositoryBOs.get(0).ownerAvatarUrl,ownerAvatar)
    }
}