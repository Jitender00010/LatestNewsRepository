package com.latestnews.view

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
import javax.inject.Inject

class NewsViewModel  @Inject constructor(private val newsRepositoryUseCase: NewsRepositoryUseCase)
    : BaseViewModel() {

    fun getLatestNews() : MutableLiveData<ListNewsResponse> {

        val newsData = MutableLiveData<ListNewsResponse>()
        viewStatus.postValue(ViewStatus.LOADING)

        newsRepositoryUseCase.getNewsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseSubscriber<ListNewsResponse>() {
                override fun onFailure(failure: Failure) {
                    handleError(failure)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: ListNewsResponse) {
                    viewStatus.postValue(ViewStatus.SUCCESS)
                    newsData.postValue(t)
                }
            })

        return newsData
    }
}