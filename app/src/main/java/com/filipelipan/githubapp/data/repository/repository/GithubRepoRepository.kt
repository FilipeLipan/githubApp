package com.filipelipan.githubapp.data.repository.repository

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.filipelipan.githubapp.data.entity.GithubRepositoryBO
import com.filipelipan.githubapp.data.mapper.toGithubRepositoryBO
import io.coroutines.cache.core.CoroutinesCache


class GithubRepoRepository(private val apolloClient: ApolloClient, context: Context): CoroutinesCache(context){

    suspend fun searchRepos(ids:String):List<GithubRepositoryBO>{



        //TODO return list
        return ArrayList<String>().toGithubRepositoryBO()
    }
}