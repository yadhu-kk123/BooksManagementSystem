package com.example.BookManagement.controller;

import com.example.BookManagement.contract.BookRequest;
import com.example.BookManagement.contract.BookResponse;
import com.example.BookManagement.model.Book;
import com.example.BookManagement.service.BookService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
//@NoArgsConstructor
//@AllArgsConstructor
public class BookController {
private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping("/create")
    public ResponseEntity<BookResponse> createBookData(@RequestBody BookRequest bookRequest){
        return new ResponseEntity<>(bookService.createBook(bookRequest), HttpStatus.OK);
    }
@GetMapping
public ResponseEntity<List<Book>> getAllBooks() {
    List<Book> books = bookService.getAllBooks();
    return ResponseEntity.ok(books);
}
    @GetMapping("get/{id}")
    public ResponseEntity<BookResponse> getBooksById(@PathVariable Long id){
       return ResponseEntity.ok(bookService.getBookById(id));
    }
    @PutMapping("update/{id}")
    public BookResponse updateBookById(@PathVariable Long id,@RequestBody BookRequest request ){
        return bookService.updateBookById(id,request);
    }
    @DeleteMapping("delete/{id}")
    public String deleteBookById(@PathVariable Long id) {
        return bookService.deleteBookById(id);
}
}
