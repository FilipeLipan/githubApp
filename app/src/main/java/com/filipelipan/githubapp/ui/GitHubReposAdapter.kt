package com.filipelipan.githubapp.ui

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.filipelipan.githubapp.R
import com.filipelipan.githubapp.data.entity.GithubRepositoryBO

class GitHubReposAdapter(data: List<GithubRepositoryBO>) : BaseQuickAdapter<GithubRepositoryBO, BaseViewHolder>(R.layout.item_repositories, data) {

    override fun convert(helper: BaseViewHolder, item: GithubRepositoryBO) {
        helper.setText(R.id.repoTitleTextView, item.name)
    }
}