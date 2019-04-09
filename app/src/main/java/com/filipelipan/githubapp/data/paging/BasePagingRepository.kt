package com.filipelipan.githubapp.data.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.filipelipan.githubapp.data.base.Listing
import com.filipelipan.githubapp.data.entity.PagedResponseBO
import kotlinx.coroutines.CoroutineScope

abstract class BasePagingRepository(private val scope: CoroutineScope) {

    abstract val config :PagedList.Config

    fun<ListingType, ParamsType> listingFactory(getFunction: (suspend (page: Long, params: ParamsType) -> PagedResponseBO<ListingType>), params: ParamsType): Listing<ListingType> {
        val sourceFactory = BaseDataFactory<ListingType, ParamsType> (getFunction, params, scope)
        val livePagedList : LiveData<PagedList<ListingType>> = LivePagedListBuilder<Long, ListingType>(sourceFactory, config).build()

        return Listing(
            pagedList = livePagedList,
            networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                it.networkState
            },
            retry = {
                sourceFactory.sourceLiveData.value?.retryAllFailed()
            },
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            },
            refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                it.initialLoad
            })
    }
}
