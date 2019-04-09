package com.filipelipan.githubapp.ui

import androidx.lifecycle.MutableLiveData
import com.filipelipan.githubapp.data.entity.GithubRepositoryBO
import com.filipelipan.githubapp.data.repository.repository.GithubRepoRepository
import com.filipelipan.githubapp.ui.base.BaseViewModel
import com.filipelipan.githubapp.utils.safeAsync

class MainViewModel(val githubRepoRepository: GithubRepoRepository) : BaseViewModel() {

    val githubRepositoriesLiveData: MutableLiveData<List<GithubRepositoryBO>> = MutableLiveData()

    init {
        loadRepositories("a", "1")
    }

    fun loadRepositories(query :String, pageCount :String){

        showLoading()
        safeAsync(
            action = { githubRepoRepository.searchRepos(query, pageCount) },
            onSuccess = {result ->
                dismissLoading()
                githubRepositoriesLiveData.value = result
            },
            onError = {error ->
                dismissLoading()
                //TODO show error
            })
    }
}