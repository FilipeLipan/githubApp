package com.filipelipan.githubapp.data.repository.repository

import com.filipelipan.githubapp.data.base.Listing

interface IGithubRepositoriesPagingRepository<ReturnType, ParamsType> {

    fun execute(params: ParamsType) : Listing<ReturnType>;
}