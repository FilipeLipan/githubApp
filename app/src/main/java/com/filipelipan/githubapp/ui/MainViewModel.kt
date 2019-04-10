package com.filipelipan.githubapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.filipelipan.githubapp.data.base.Listing
import com.filipelipan.githubapp.data.entity.GithubRepositoryBO
import com.filipelipan.githubapp.data.repository.repository.GithubRepoRepository
import com.filipelipan.githubapp.data.repository.repository.GithubRepositoriesPagingRepository
import com.filipelipan.githubapp.data.repository.repository.IGithubRepositoriesPagingRepository
import com.filipelipan.githubapp.ui.base.BaseViewModel
import com.filipelipan.githubapp.utils.safeAsync
import kotlinx.coroutines.CoroutineScope
import org.koin.core.parameter.parametersOf
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class MainViewModel : BaseViewModel(), CoroutineScope, KoinComponent {
    val githubRepositoriesPagingRepository: IGithubRepositoriesPagingRepository<GithubRepositoryBO, GithubRepositoriesPagingRepository.Params> by inject{ parametersOf(this) }

    val query = MutableLiveData<String>()

    private val repoResult = Transformations.map(query) {
        search(query = it)
    }

    val repositories = Transformations.switchMap(repoResult, { it.pagedList })!!
    val networkState = Transformations.switchMap(repoResult, { it.networkState })!!
    val refreshState = Transformations.switchMap(repoResult, { it.refreshState })!!

    fun search(query: String): Listing<GithubRepositoryBO> {
        return githubRepositoriesPagingRepository.execute(GithubRepositoriesPagingRepository.Params(query))
    }

    fun refresh() {
        val listing = repoResult?.value
        listing?.refresh ?.invoke()
    }

    fun searchRepositories(searchQuery: String): Boolean {
        if (query.value == searchQuery) {
            return false
        }
        query.value = searchQuery
        return true
    }

    fun retry() {
        val listing = repoResult?.value
        listing?.retry?.invoke()
    }

//    fun setSearchQuery(): String? = query.value
}