package com.latestnews.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.latestnews.utility.Failure
import com.latestnews.utility.ViewStatus
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel @Inject constructor() : ViewModel() {

    val viewStatus: MutableLiveData<ViewStatus> = MutableLiveData()
    val compositeDisposable = CompositeDisposable()


    protected fun handleError(e: Failure){
        //viewStatus.postValue(ViewStatus.FAIL(e))
        when(e){
            is Failure.NetworkConnection -> viewStatus.postValue(ViewStatus.FAIL(e))
            is Failure.ServerError -> viewStatus.postValue(ViewStatus.FAIL(e))
            else -> viewStatus.postValue(ViewStatus.FAIL(e))
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}