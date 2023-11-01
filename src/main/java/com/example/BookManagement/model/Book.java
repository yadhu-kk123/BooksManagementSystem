package com.example.BookManagement.model;
import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
    @Entity
//    @Builder
//    @Getter
//    @Setter
    @NoArgsConstructor
   @AllArgsConstructor
    public class Book
    {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String title;
        private String author;
        private String genre;
        private Double price;
        private int stockQuantity;

        @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
        private List<Review> review;

        public Book( String title, String author, String genre, double price, int stockQuantity) {

            this.title = title;
            this.author = author;
            this.genre = genre;
            this.price = price;
            this.stockQuantity = stockQuantity;

        }

        public <E> Book(long id, String title,String author, String genre, double price, int stockQuantity, ArrayList<E> es) {
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

        public List<Review> getReview() {
            return review;
        }

        public void setReview(List<Review> review) {
            this.review = review;
        }
    }

