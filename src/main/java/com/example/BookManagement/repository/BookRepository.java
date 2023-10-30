package com.example.BookManagement.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.BookManagement.model.Book;

    @Repository
    public interface BookRepository extends CrudRepository<Book,Long> {


}
