package com.filipelipan.githubapp.ui.base

import androidx.annotation.StringRes


sealed class ViewState{
     data class Error(@StringRes val message:Int) : ViewState()
     data class Loading(val isLoading:Boolean): ViewState()
}

