package com.latestnews.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.domain.entity.NewsResponseVo
import com.latestnews.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_news_list.view.*

class NewsAdapter constructor(private val list : List<NewsResponseVo>,private val context : Context,
                              private val onVASelectClick: (newsData: NewsResponseVo) -> Unit)  : RecyclerView.Adapter<NewsAdapter.VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.VH {
        return VH(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_news_list, parent, false))
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: NewsAdapter.VH, position: Int) {
        var data = list!![position]
        holder.bindData(data)
    }


    inner class VH(val v: View) : RecyclerView.ViewHolder(v) {

        fun bindData(contentAdd: NewsResponseVo) {
            v.tv_headline.text = contentAdd.title
            v.tv_source.text = contentAdd.source.name
            v.tv_publish_date.text = contentAdd.publishedAt

            Glide.with(context)
                .load(contentAdd.urlToImage)
                .fitCenter()
                .into(v.iv_news)
            v.cv_container.setOnClickListener {
                onVASelectClick(contentAdd)
                notifyDataSetChanged()
            }
        }
    }
}