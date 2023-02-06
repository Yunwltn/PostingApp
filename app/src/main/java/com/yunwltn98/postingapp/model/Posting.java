package com.yunwltn98.postingapp.model;

public class Posting {
    public int userId;
    public int id;
    public String title;
    public String body;

    public Posting(){
    }

    public Posting(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
