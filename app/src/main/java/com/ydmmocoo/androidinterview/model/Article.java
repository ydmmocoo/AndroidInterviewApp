package com.ydmmocoo.androidinterview.model;

import cn.bmob.v3.BmobObject;

public class Article extends BmobObject {

    private String title;
    private String url;

    public Article() {
        this.setTableName("_Article");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
