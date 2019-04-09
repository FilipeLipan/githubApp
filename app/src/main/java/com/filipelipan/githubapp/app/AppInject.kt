package com.filipelipan.githubapp.app

import io.coroutines.cache.core.CoroutinesCache
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import com.filipelipan.githubapp.data.api.RestClient
import com.filipelipan.githubapp.data.repository.repository.GithubRepoRepository
import com.filipelipan.githubapp.data.repository.repository.GithubRepositoriesPagingRepository
import com.filipelipan.githubapp.data.repository.repository.IGithubRepositoriesPagingRepository
import com.filipelipan.githubapp.ui.MainViewModel
import kotlinx.coroutines.CoroutineScope


object AppInject {

    fun modules() : List<Module> = listOf(
        applicationModule,
        viewModelModule,
        repositoriesModule
    )

    private val applicationModule: Module = module {
        single { RestClient().api }
        single { CoroutinesCache(androidContext()) }
    }

    private val viewModelModule = module {
        viewModel { MainViewModel() }
    }

    private val repositoriesModule: Module = module {
        single { GithubRepoRepository(get(), get()) }
        single { ( scope: CoroutineScope) -> GithubRepositoriesPagingRepository(scope) as IGithubRepositoriesPagingRepository<*,*> }
    }
}