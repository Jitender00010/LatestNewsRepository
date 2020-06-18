package com.latestnews.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.domain.entity.NewsResponseVo
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.adapter_news_list.*
import kotlinx.android.synthetic.main.adapter_news_list.view.*


class NewsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.latestnews.R.layout.activity_news_detail)

        val i = intent
        val responseVO = intent.extras!!.getSerializable("NEWS_DETAILS") as NewsResponseVo?

        responseVO?.let {
            Glide.with(this)
                .load(it.urlToImage)
                .fitCenter()
                .into(iv_details_activity)

            tv_discription.text = it.description

            tv_headline_newsDetails.text = it.title
        }

    }
}