package com.filipelipan.githubapp.utils

import com.filipelipan.githubapp.data.repository.exception.KnownError
import com.filipelipan.githubapp.data.repository.exception.KnownException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun <T> CoroutineScope.safeAsync(action: suspend () -> T,
                                 onError: (e: Throwable) -> Unit = {},
                                 onSuccess: (T) -> Unit = {}) = launch {
    try {
        val response = action()
        withContext(kotlinx.coroutines.Dispatchers.Main) {
            onSuccess(response)
        }
    } catch (e: Exception) {
        withContext(kotlinx.coroutines.Dispatchers.Main) {
            onError(e)
        }
    } catch (e: OutOfMemoryError) {
        onError(KnownException(KnownError.OUT_OF_MEMORY_ERROR, e.message ?: ""))
    }
}
