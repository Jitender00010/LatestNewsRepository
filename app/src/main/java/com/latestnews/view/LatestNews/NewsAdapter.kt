package com.latestnews.view.LatestNews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.domain.entity.NewsResponseVo
import com.latestnews.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_news_list.view.*
import kotlinx.android.synthetic.main.item_loading.view.*

class NewsAdapter constructor(private var list : ArrayList<NewsResponseVo>,private val context : Context,
                              private val onVASelectClick: (newsData: NewsResponseVo) -> Unit)  : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val VIEW_TYPE_LOADING = 0
    private val VIEW_TYPE_NORMAL = 1
    private var isLoaderVisible = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {
        when (viewType) {
            VIEW_TYPE_NORMAL ->
                return VH(LayoutInflater.from(parent.context).inflate(R.layout.adapter_news_list, parent, false))
            VIEW_TYPE_LOADING ->
                return ProgressHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false))
            else -> return VH(LayoutInflater.from(parent.context).inflate(R.layout.adapter_news_list, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) === VIEW_TYPE_NORMAL) {
            var data = list!![position]
            (holder as VH).bindData(data)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoaderVisible) {
            if (position == list!!.size - 1) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    fun addItems(newsList: ArrayList<NewsResponseVo>) {
        list.addAll( newsList)
        notifyDataSetChanged()
    }

    fun isLoading(): Boolean = isLoaderVisible

    fun addLoading() {
        isLoaderVisible = true
        list.add(NewsResponseVo())
        notifyItemInserted(list!!.size - 1)
    }

    fun removeLoading() {
        isLoaderVisible = false
        val position = list!!.size - 1
        val item = getItem(position)
        if (item != null) {
            list!!.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        list!!.clear()
        notifyDataSetChanged()
    }

    fun getListSize() : Int {
        return list.size?:0
    }

    private fun getItem(position: Int): NewsResponseVo? {
        return if (position > 0)
            list!![position]
        else
            null
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

    inner class ProgressHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar = itemView.progressBar
    }
}