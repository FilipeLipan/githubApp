package com.filipelipan.githubapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.widget.TextViewCompat
import com.filipelipan.githubapp.app.AppInject
import com.filipelipan.githubapp.data.base.Listing
import com.filipelipan.githubapp.data.entity.GithubRepositoryBO
import com.filipelipan.githubapp.data.repository.repository.GithubRepositoriesPagingRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.inject
import org.koin.test.KoinTest

class MainViewModelTest : KoinTest{

//    @get:Rule
//    val rule: TestRule = InstantTaskExecutorRule()
//
//    lateinit var mainViewModel: MainViewModel
//
//    lateinit var repositoriesListingMock:Listing<GithubRepositoryBO>
//
//    @Before
//    fun setUp() {
//        loadKoinModules( AppInject.modules())
//
//        mainViewModel = MainViewModel()
//        whenever(mainViewModel.githubRepositoriesPagingRepository).thenReturn(mock())
//
//    }
//
//    @Test
//    fun checkIfRepositoryIsCalledAfterQueryLiveDataChange(){
//        val viewModelTest= spy(mainViewModel)
//        val query :String = "Stepper";
//        viewModelTest.query.value = query
//        verify(viewModelTest).search(query)
//    }
//
//    @Test
//    fun testRefresh(){
//        val viewModelTest = spy(mainViewModel)
//
//
//        val query :String = "Stepper";
//        viewModelTest.query.value = query
//
//        viewModelTest.refresh()
//
//        verify(viewModelTest.repoResult.value?.refresh)
//            ?.invoke()
//    }
//
//    fun refresh() {
//        val listing = repoResult?.value
//        listing?.refresh ?.invoke()
//    }

//    @Test
//    fun getGithubRepositoriesPagingRepository() {
//    }
//
//    @Test
//    fun getRepositories() {
//    }
//
//    @Test
//    fun getNetworkState() {
//    }
//
//    @Test
//    fun getRefreshState() {
//    }
//
//    @Test
//    fun refresh() {
//    }
//
//    @Test
//    fun searchRepositories() {
//    }
//
//    @Test
//    fun retry() {
//    }
}