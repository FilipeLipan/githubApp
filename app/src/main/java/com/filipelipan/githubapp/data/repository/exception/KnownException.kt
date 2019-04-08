package com.filipelipan.githubapp.data.repository.exception

import com.filipelipan.githubapp.data.repository.exception.KnownError

open class KnownException(var knownError: KnownError = KnownError.UNKNOWN_EXCEPTION, message:String) : Exception( message)
