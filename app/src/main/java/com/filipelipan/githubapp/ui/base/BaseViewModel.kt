package com.filipelipan.githubapp.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope{

    val viewStateEvent: SingleLiveEvent<ViewState> = SingleLiveEvent()

    private val executionJob: Job  by lazy { Job() }

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Default + executionJob
    }


    fun showLoading(){
        viewStateEvent.value = ViewState.Loading(true)
    }

    fun dismissLoading(){
        viewStateEvent.value = ViewState.Loading(false)
    }

    /**
     * Expose the LiveData so the UI can observe it.
     */
    fun getViewStatebservable(): LiveData<ViewState> {
        return viewStateEvent
    }

    override fun onCleared() {
        super.onCleared()
        executionJob.cancel()
    }
}