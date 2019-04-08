package com.filipelipan.githubapp.ui

import androidx.lifecycle.MutableLiveData
import com.filipelipan.githubapp.data.entity.GithubRepositoryBO
import com.filipelipan.githubapp.data.repository.repository.GithubRepoRepository
import com.filipelipan.githubapp.ui.base.BaseViewModel
import com.filipelipan.githubapp.utils.safeAsync

class MainViewModel(val weatherRepository: GithubRepoRepository) : BaseViewModel() {

    val weatherLiveData: MutableLiveData<List<GithubRepositoryBO>> = MutableLiveData()

    init {
        loadWeather("524901,703448,2643743")
    }

    fun loadWeather(query :String){

        showLoading()
        safeAsync(
            action = { weatherRepository.searchRepos(query) },
            onSuccess = {result ->
                dismissLoading()
              weatherLiveData.value = result
            },
            onError = {error ->
                dismissLoading()
                //TODO show error
            })
    }
}