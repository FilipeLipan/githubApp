package com.filipelipan.githubapp.data.repository.repository

import androidx.paging.PagedList
import com.filipelipan.githubapp.data.base.Listing
import com.filipelipan.githubapp.data.entity.GithubRepositoryBO
import com.filipelipan.githubapp.data.paging.BasePagingRepository
import kotlinx.coroutines.CoroutineScope
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

val PAG_ECOUNT :Long = 20
class GithubRepositoriesPagingRepository(private val scope: CoroutineScope) :
    BasePagingRepository(scope), IGithubRepositoriesPagingRepository<GithubRepositoryBO, GithubRepositoriesPagingRepository.Params>, KoinComponent {

    private val githubRepoRepository: GithubRepoRepository by inject()

    override val config = PagedList.Config.Builder()
        .setPageSize(PAG_ECOUNT.toInt())
        .setInitialLoadSizeHint(20 * 2)
        .setEnablePlaceholders(false)
        .build()

    override fun execute(params: GithubRepositoriesPagingRepository.Params): Listing<GithubRepositoryBO> {
        //TODO fix last loading - make list show the load finish view when necessary

        return listingFactory({ page, movieRepositoryParams -> githubRepoRepository.searchRepos(params.query, page)}, params)
    }

    data class Params(val query:String)
}