package com.callmanagerfinal.base

import com.callmanagerfinal.utility.Failure
import io.reactivex.Observer
import retrofit2.HttpException
import java.io.IOException

abstract class BaseSubscriber<T> : Observer<T> {

    override fun onError(e: Throwable) {
        e.printStackTrace()
        when (e) {
            is IOException -> onFailure(Failure.NetworkConnection("You are not connected to Internet!"))
            is Failure -> onFailure(e)
            is HttpException -> {
                if (e.code() == 401) {
                    try {
                        // val  gson = Gson()
                       // val response = gson.fromJson(e.response()!!.errorBody()?.string(),BaseResponse::class.java)
                        onFailure(Failure.TokenFailure("Session expired."))
                    } catch (e: Exception) {
                        onFailure(Failure.TokenFailure("Session expired."))
                    }
                }
            }
            else -> onFailure(Failure.ServerError("Something went wrong Please try again later"))
        }
    }

    override fun onComplete() {}
    abstract fun onFailure(failure: Failure)
}