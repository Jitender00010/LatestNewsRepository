package com.latestnews.view

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.latestnews.R
import com.latestnews.base.BaseActivity
import com.latestnews.base.BaseViewModel
import javax.inject.Inject

class LatestNewsListActivity : BaseActivity() {

    lateinit var newsViewModel: NewsViewModel


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun provideBaseModel(): BaseViewModel {
        newsViewModel = ViewModelProviders.of(this ,viewModelFactory).get(NewsViewModel::class.java)
        return newsViewModel
    }

    override fun layoutId(): Int = R.layout.activity_main

    override fun initView() {

        newsViewModel.getLatestNews().observe(this, Observer {
            Log.e("LatestNews "," display ==== "+it.description)
        })
    }
}