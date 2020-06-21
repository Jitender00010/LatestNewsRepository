package com.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListNewsResponse extends BaseDisplayData<ArrayList<NewsResponseVo>>{

    @SerializedName("articles")
    private ArrayList<NewsResponseVo> articlesList;

    public ArrayList<NewsResponseVo> getArticlesList() {
        return articlesList;
    }

    public void setArticlesList(ArrayList<NewsResponseVo> articlesList) {
        this.articlesList = articlesList;
    }

    @Override
    public void update(ArrayList<NewsResponseVo> newsResponses) {

        articlesList = newsResponses;
    }
}
