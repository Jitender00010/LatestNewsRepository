package com.latestnews.view.LatestNews

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.latestnews.base.BaseActivity
import com.latestnews.base.BaseViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.content.Intent
import android.view.View
import com.latestnews.utility.PaginationListener
import com.latestnews.utility.PaginationListener.PAGE_START
import com.latestnews.view.NewsDetailsActivity

class LatestNewsListActivity : BaseActivity() {

    lateinit var newsViewModel: NewsViewModel
    lateinit var adapter: NewsAdapter

    private var currentPage = PAGE_START
    private var mLastPage = false
    private var mLoading: Boolean = false

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun provideBaseModel(): BaseViewModel {
        newsViewModel = ViewModelProviders.of(this ,viewModelFactory).get(NewsViewModel::class.java)
        return newsViewModel
    }

    override fun layoutId(): Int = com.latestnews.R.layout.activity_main

    override fun initView() {
        recyclerView.setHasFixedSize(true)
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        adapter = NewsAdapter(ArrayList(), this) {
            val i = Intent(this, NewsDetailsActivity::class.java)
            i.putExtra("NEWS_DETAILS", it)
            startActivity(i)
        }
        recyclerView.adapter = adapter

        fetchLatestNews(PAGE_START,false)

        recyclerView.addOnScrollListener(object : PaginationListener(layoutManager) {
            override fun isLastPage(): Boolean = mLastPage
            override fun isLoading(): Boolean = mLoading
            override fun loadMoreItems() {
                mLoading = true
                currentPage++
                fetchLatestNews(currentPage,true)
            }
        })

        newsViewModel.getError().observe(this, Observer {
            if (adapter.isLoading())
                adapter.removeLoading()
            mLastPage = true
            currentPage = PAGE_START

            if (adapter.getListSize() == 0)
                tv_placeholder.visibility = View.VISIBLE
        })
    }


    private fun fetchLatestNews(pageNo: Int, isScroll : Boolean) {
        newsViewModel.getLatestNews(pageNo).observe(this, Observer { response->
            tv_placeholder.visibility = View.GONE

            if (response.articlesList.isNotEmpty()){
                if (pageNo != PAGE_START || adapter.isLoading())
                    adapter.removeLoading()

                adapter.addItems(response.articlesList)

                if (isScroll)
                    adapter.addLoading()

                mLoading = false
                mLastPage = false
            }else{
                if (adapter.isLoading())
                    adapter.removeLoading()
                mLastPage = true
                currentPage = PAGE_START

                if (adapter.getListSize() == 0)
                    tv_placeholder.visibility = View.VISIBLE
            }
        })
    }
}