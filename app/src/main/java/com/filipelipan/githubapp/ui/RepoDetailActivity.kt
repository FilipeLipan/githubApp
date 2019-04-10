package com.filipelipan.githubapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView
import com.filipelipan.githubapp.R
import com.filipelipan.githubapp.data.base.NetworkState
import com.filipelipan.githubapp.data.entity.GithubRepositoryBO
import com.filipelipan.githubapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoDetailActivity : BaseActivity() {

    override val viewModel: MainViewModel by viewModel()
    override val activityLayout: Int
        get() = R.layout.activity_repo_detail

    lateinit var githubRepositoryBO: GithubRepositoryBO

    companion object {

        val GITHUB_REPO_KEY = "GITHUB_REPO_KEY"

        fun getIntent(context: Context, githubRepositoryBO: GithubRepositoryBO): Intent {
            val intent = Intent(context, RepoDetailActivity::class.java)
            intent.putExtra(GITHUB_REPO_KEY, githubRepositoryBO)
            return intent;
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        githubRepositoryBO = intent?.getSerializableExtra(GITHUB_REPO_KEY) as GithubRepositoryBO



    }
}
