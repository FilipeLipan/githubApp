package com.filipelipan.githubapp.ui

import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BasePagedQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.filipelipan.githubapp.R
import com.filipelipan.githubapp.data.base.NetworkState
import com.filipelipan.githubapp.data.base.Status
import com.filipelipan.githubapp.data.entity.GithubRepositoryBO

class GitHubReposAdapter : BasePagedQuickAdapter<GithubRepositoryBO, BaseViewHolder>(R.layout.item_repositories, diffCallback) {

    override fun convert(helper: BaseViewHolder, item: GithubRepositoryBO?) {
        item?.let {
            helper.setText(R.id.repoNameTextView, item.name)
                .setText(R.id.programingLanguageTextView, item.language)
                .setText(R.id.startCountTextView, item.stargazersCount)
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<GithubRepositoryBO>() {
            override fun areItemsTheSame(oldItem: GithubRepositoryBO, newItem: GithubRepositoryBO): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GithubRepositoryBO, newItem: GithubRepositoryBO): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        if(!isLoadMoreEnable){
            return;
        }
        when(newNetworkState?.status){
            Status.RUNNING -> {
//                notifyLoadMoreToLoading()
            }
            Status.FAILED -> {
                loadMoreFail()
            }
        }
    }
}