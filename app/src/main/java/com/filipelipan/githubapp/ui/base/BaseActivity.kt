package com.filipelipan.githubapp.ui.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.filipelipan.githubapp.R
import com.filipelipan.githubapp.utils.blackLoading

import kotlinx.coroutines.*
import kotlinx.coroutines.android.Main
import kotlin.coroutines.CoroutineContext


abstract class BaseActivity: AppCompatActivity(), CoroutineScope{

    abstract val viewModel: BaseViewModel
    abstract val activityLayout: Int

    private val executionJob: Job by lazy { Job() }

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Main + executionJob
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityLayout)
        setupViewState()
    }

    override fun onDestroy() {
        super.onDestroy()
        executionJob.cancel()
    }

    override fun onBackPressed() {
        val loadingView = findViewById<ProgressBar>(R.id.loading)
        if(loadingView == null ){
            super.onBackPressed()
        }
    }

    open fun showLoading() {
        if(findViewById<View>(R.id.loading_root)== null){
            findViewById<ViewGroup>(android.R.id.content).addView(blackLoading())
        }
    }

    open fun dismissLoading() {
        val root = findViewById<ViewGroup>(android.R.id.content)
                root.removeView(findViewById(R.id.loading_root))
    }

    fun showError(@StringRes message:Int){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    open fun setupViewState(){
        viewModel.getViewStatebservable().observe(this@BaseActivity, Observer {viewState->
            when(viewState){
                is ViewState.Error ->  showError(viewState.message)
                is ViewState.Loading ->   if(viewState.isLoading){ showLoading() }else {dismissLoading()}
            }
        })
    }



}