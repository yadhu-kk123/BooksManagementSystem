package com.example.BookManagement.contract;

import java.util.Date;

public class ReviewResponse {
    private long id;
    private long userId;
    private double rating;
    private String comment;
    private String createdAt;

    public ReviewResponse(long id, long userId, double rating, String comment, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

//    public List<BookResponse>Book;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = String.valueOf(createdAt);
    }
}
