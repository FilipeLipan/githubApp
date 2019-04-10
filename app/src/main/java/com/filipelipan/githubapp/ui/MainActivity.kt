package com.filipelipan.githubapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView
import com.filipelipan.githubapp.R
import com.filipelipan.githubapp.data.base.NetworkState
import com.filipelipan.githubapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    override val viewModel: MainViewModel by viewModel()
    override val activityLayout: Int
        get() = R.layout.activity_main

    lateinit var repositoriesAdapter: GitHubReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        observeViewModel()
        setUpViews()
    }

    fun setUpViews(){
        searchEditText.addTextChangedListener(getSearchDebounce())
    }

    fun getSearchDebounce(): TextWatcher {
        return object : TextWatcher {
            private var searchFor = ""

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString().trim()
                if (searchText == searchFor)
                    return
                searchFor = searchText
                launch {
                    delay(500)
                    if (searchText != searchFor)
                        return@launch
                    viewModel.searchRepositories(searchText)
                }
            }
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        }
    }

    fun initAdapter(){
        repositoriesAdapter = GitHubReposAdapter()
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = repositoriesAdapter
        repositoriesAdapter.setEnableLoadMore(true)
        repositoriesAdapter.setLoadMoreView(SimpleLoadMoreView())

        swipe_refresh.setOnRefreshListener {
            repositoriesAdapter.setEnableLoadMore(false)
            viewModel.refresh()
            repositoriesAdapter.setOnLoadMoreListener(null)
        }
    }

    fun observeViewModel(){
        viewModel.repositories.observe(this, Observer {
            repositoriesAdapter.setEnableLoadMore(true)
            repositoriesAdapter.submitList(it)
        })

        viewModel.networkState.observe(this, Observer {
            repositoriesAdapter.setNetworkState(it)
            repositoriesAdapter.setOnLoadMoreListener{
                viewModel.retry()
            }
        })

        viewModel.refreshState.observe(this, Observer {
            swipe_refresh.isRefreshing = it == NetworkState.LOADING
        })
    }
}
