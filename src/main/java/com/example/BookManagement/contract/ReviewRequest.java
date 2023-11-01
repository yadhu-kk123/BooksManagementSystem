package com.example.BookManagement.contract;

import com.example.BookManagement.model.Book;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;

@Data
public class ReviewRequest {
    private long userId;
    private double rating;
    private String comment;
    private Date createdAt;
@OneToMany
private Book book;
    public ReviewRequest() {
    }

    public ReviewRequest(long userId, double rating, String comment, Date createdAt) {
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public ReviewRequest(int userId, String comment) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
