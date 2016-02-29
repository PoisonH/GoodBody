package com.poison.goodbody.bean;

import java.util.List;

/**
 * Created by PoisonH on 2016/2/22.
 */
public class DataListEntity
{
    private String status;
    private String message;
    private DataEntity data;

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setData(DataEntity data)
    {
        this.data = data;
    }

    public String isStatus()
    {
        return status;
    }

    public String getMessage()
    {
        return message;
    }

    public DataEntity getData()
    {
        return data;
    }

    public static class DataEntity
    {
        private List<DataListItemEntity> ad_data;
        private List<DataListItemEntity> article_list;

        public void setAd_data(List<DataListItemEntity> ad_data)
        {
            this.ad_data = ad_data;
        }

        public void setArticle_list(List<DataListItemEntity> article_list)
        {
            this.article_list = article_list;
        }

        public List<DataListItemEntity> getAd_data()
        {
            return ad_data;
        }

        public List<DataListItemEntity> getArticle_list()
        {
            return article_list;
        }

    }

    public static class DataListItemEntity
    {
        private String id;
        private String title;
        private String description;
        private String picurl;
        private String list_ico;
        private String content;
        private String pubdate;
        private String click;
        private String typename;

        public void setId(String id)
        {
            this.id = id;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public void setDescription(String description)
        {
            this.description = description;
        }

        public void setPicurl(String picurl)
        {
            this.picurl = picurl;
        }

        public void setList_ico(String list_ico)
        {
            this.list_ico = list_ico;
        }

        public void setContent(String content)
        {
            this.content = content;
        }

        public void setPubdate(String pubdate)
        {
            this.pubdate = pubdate;
        }

        public void setClick(String click)
        {
            this.click = click;
        }

        public void setTypename(String typename)
        {
            this.typename = typename;
        }

        public String getId()
        {
            return id;
        }

        public String getTitle()
        {
            return title;
        }

        public String getDescription()
        {
            return description;
        }

        public String getPicurl()
        {
            return picurl;
        }

        public String getList_ico()
        {
            return list_ico;
        }

        public String getContent()
        {
            return content;
        }

        public String getPubdate()
        {
            return pubdate;
        }

        public String getClick()
        {
            return click;
        }

        public String getTypename()
        {
            return typename;
        }
    }
}
