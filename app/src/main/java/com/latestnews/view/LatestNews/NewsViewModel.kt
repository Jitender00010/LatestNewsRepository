package com.latestnews.view.LatestNews

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.domain.entity.ListNewsResponse
import com.latestnews.base.BaseSubscriber
import com.latestnews.base.BaseViewModel
import com.latestnews.data.useCase.NewsRepositoryUseCase
import com.latestnews.utility.Failure
import com.latestnews.utility.ViewStatus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class NewsViewModel  @Inject constructor(private val newsRepositoryUseCase: NewsRepositoryUseCase)
    : BaseViewModel() {

    val errorResponse = MutableLiveData<String>()

    fun getError() : MutableLiveData<String>{
        return errorResponse
    }
    fun getLatestNews(page : Int) : MutableLiveData<ListNewsResponse> {

        val newsData = MutableLiveData<ListNewsResponse>()
        if(page == 1)
        viewStatus.postValue(ViewStatus.LOADING)

        newsRepositoryUseCase.getNewsList(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseSubscriber<ListNewsResponse>() {
                override fun onFailure(failure: Failure) {
                    if(page == 1)
                        viewStatus.postValue(ViewStatus.SUCCESS)
                    handleError(failure)
                    errorResponse.postValue(failure.message)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: ListNewsResponse) {
                    if(page == 1)
                    viewStatus.postValue(ViewStatus.SUCCESS)
                    newsData.postValue(t)
                }
            })

        return newsData
    }
}