package com.example.BookManagement.contract;

import com.example.BookManagement.model.Review;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BookRequest {
    private String title;
    private String author;
    private String genre;
    private double price;
    private int stockQuantity;
    @OneToMany
    public Review review;

    public BookRequest(String title, String author, String genre, double price, int stockQuantity) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}

