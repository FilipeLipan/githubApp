package com.filipelipan.githubapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.filipelipan.githubapp.R
import com.filipelipan.githubapp.data.entity.GithubRepositoryBO
import com.filipelipan.githubapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_repo_detail.*
import kotlinx.android.synthetic.main.include_repo_detail.view.*
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

        username.text = githubRepositoryBO.ownerName
        description.text = githubRepositoryBO.ownerDescription
        repoTitle.text = githubRepositoryBO.name
        repoDescription.text = githubRepositoryBO.description
        include_repo_detail.forkCountTextView.text = githubRepositoryBO.forks
        include_repo_detail.starCountTextView.text = githubRepositoryBO.stargazersCount
        include_repo_detail.watchersCountTextView.text = githubRepositoryBO.watchersCount

        Glide.with(this)
            .load(githubRepositoryBO.ownerAvatarUrl)
            .into(ownerAvatar)

        blackBackButton.setOnClickListener { onBackPressed() }
    }
}
