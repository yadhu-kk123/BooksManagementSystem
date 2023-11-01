package com.example.BookManagement.repository;

import com.example.BookManagement.model.Book;
import com.example.BookManagement.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends CrudRepository<Review,Long> {
List<Review>findByBook(Book book);

}
