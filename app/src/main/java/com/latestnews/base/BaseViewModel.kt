package com.callmanagerfinal.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.callmanagerfinal.utility.Failure
import com.callmanagerfinal.utility.ViewStatus
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel @Inject constructor() : ViewModel() {

    var viewStatus: MutableLiveData<ViewStatus> = MutableLiveData()
    var compositeDisposable = CompositeDisposable()


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