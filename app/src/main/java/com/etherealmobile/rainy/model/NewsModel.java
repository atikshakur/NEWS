package com.etherealmobile.rainy.model;

public class NewsModel  {

    String title;
    String urlToImage;
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public NewsModel(String title, String urlToImage, String content) {
        this.title = title;
        this.urlToImage = urlToImage;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
