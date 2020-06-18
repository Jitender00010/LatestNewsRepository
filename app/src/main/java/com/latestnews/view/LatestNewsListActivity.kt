package com.latestnews.view

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.latestnews.R
import com.latestnews.base.BaseActivity
import com.latestnews.base.BaseViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.view.View.Y
import android.content.Intent
import com.domain.entity.NewsResponseVO
import com.domain.entity.NewsResponseVo


class LatestNewsListActivity : BaseActivity() {

    lateinit var newsViewModel: NewsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun provideBaseModel(): BaseViewModel {
        newsViewModel = ViewModelProviders.of(this ,viewModelFactory).get(NewsViewModel::class.java)
        return newsViewModel
    }

    override fun layoutId(): Int = com.latestnews.R.layout.activity_main

    override fun initView() {

        recyclerView.layoutManager = LinearLayoutManager(this)

        newsViewModel.getLatestNews().observe(this, Observer {
            val adapter = NewsAdapter(it.articlesList,this){
                val i = Intent(this, NewsDetailsActivity::class.java)
                i.putExtra("NEWS_DETAILS", it)
                startActivity(i)
            }
            recyclerView.adapter = adapter
        })
    }
}