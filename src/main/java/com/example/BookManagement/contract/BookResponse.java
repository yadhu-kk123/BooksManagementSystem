package com.example.BookManagement.contract;

import lombok.*;

@Data
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String genre;
    private int price;
    private int stockQuantity;

    public BookResponse(Long id, String title, String author, String genre, int price, int stockQuantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public BookResponse(long l, String test1, String genre1, double v, int i) {
    }

    public static Object builder() {
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }



    public BookResponse() {
    }
}
