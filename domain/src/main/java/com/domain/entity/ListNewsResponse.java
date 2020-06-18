package com.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListNewsResponse extends BaseDisplayData<List<NewsResponseVo>>{

    @SerializedName("articles")
    private List<NewsResponseVo> articlesList;

    public List<NewsResponseVo> getArticlesList() {
        return articlesList;
    }

    public void setArticlesList(List<NewsResponseVo> articlesList) {
        this.articlesList = articlesList;
    }

    @Override
    public void update(List<NewsResponseVo> newsResponses) {

        articlesList = newsResponses;
    }
}
