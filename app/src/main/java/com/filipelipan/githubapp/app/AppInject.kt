package com.example.fejn.windreport.app

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.cache.http.ApolloHttpCache
import io.coroutines.cache.core.CoroutinesCache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import java.io.File
import com.apollographql.apollo.cache.http.DiskLruHttpCacheStore
import com.filipelipan.githubapp.data.repository.repository.GithubRepoRepository
import com.filipelipan.githubapp.ui.MainViewModel


object AppInject {

    //TODO: put your token here man!!!
    private val GITHUB_AUTH_TOKEN = ""
    private val BASE_URL = "https://api.github.com/graphql"

    fun modules() : List<Module> = listOf(
        applicationModule,
        viewModelModule,
        repositoriesModule
    )

    private val applicationModule: Module = module {
        single { CoroutinesCache(androidContext()) }
        single { provideApolloClient(androidContext(), provideHttpClient())}
    }

    private val viewModelModule = module {
        viewModel { MainViewModel(get()) }
    }

    private val repositoriesModule: Module = module {
        single { GithubRepoRepository(get(), get()) }
    }

    fun provideHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor {
                val original = it.request()
                val builder = original.newBuilder().method(original.method(), original.body())
                builder.header("Authorization", "Bearer $GITHUB_AUTH_TOKEN")
                it.proceed(builder.build())
            }
            .build()
    }

    fun provideApolloClient(context : Context, okHttpClient:OkHttpClient): ApolloClient {

        val file = File(context.applicationContext.cacheDir.toURI())
        val size :Long = 1024 * 1024
        val cacheStore = DiskLruHttpCacheStore(file, size)

        return ApolloClient.builder()
            .serverUrl(BASE_URL)
            .httpCache(ApolloHttpCache(cacheStore))
            .okHttpClient(okHttpClient)
            .build()
    }
}