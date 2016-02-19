package com.poison.goodbody.model;

import java.util.List;

/**
 * Created by PoisonH on 2016/2/19.
 */
public class Data
{
    private List<Ad_data> ad_data;
    private List<Article_list> article_list;

    public List<Ad_data> getAd_data()
    {
        return ad_data;
    }

    public void setAd_data(List<Ad_data> ad_data)
    {
        this.ad_data = ad_data;
    }

    public List<Article_list> getArticle_list()
    {
        return article_list;
    }

    public void setArticle_list(List<Article_list> article_list)
    {
        this.article_list = article_list;
    }
}
