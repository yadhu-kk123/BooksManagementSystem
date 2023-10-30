package com.example.BookManagement.service;

import com.example.BookManagement.contract.BookRequest;
import com.example.BookManagement.contract.BookResponse;
import com.example.BookManagement.model.Book;
import com.example.BookManagement.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BookServiceTest {
    private   BookService bookService;
    private BookRepository bookRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookService(bookRepository);
    }

    @Test
    void testGetAllBooks(){
        Book book= new Book("booktitle","author","test",10,1);
//        book.setId(1L);
        book.setTitle("sample");
        book.setAuthor("sample1");
        book.setGenre("test");
        book.setPrice(10);
        book.setStockQuantity(1);
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(book);
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> items = bookService.getAllBooks();
        assertEquals(items.get(0).getId(),1L);
    }

    @Test
    void testBookById(){
        Book book = new Book("booktitle","author","test",10,1);
        book.setTitle("sample");
        book.setAuthor("sample1");
        book.setGenre("test");
        book.setPrice(10);
        book.setStockQuantity(1);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        BookResponse book1 = bookService.getBookById(1L);
        assertEquals(book1.getId(),1L);
    }


    @Test
    void testAddBook(){
        BookRequest bookRequest = new BookRequest("booktitle","author","test",10,1);
        bookRequest.setTitle("sample");
        bookRequest.setAuthor("sample1");
        bookRequest.setGenre("test");
        bookRequest.setPrice(10);
        bookRequest.setStockQuantity(1);
        Book book = new Book("booktitle","author","test",10,1);
        bookRequest.setTitle("sample");
        bookRequest.setAuthor("sample1");
        bookRequest.setGenre("test");
        bookRequest.setPrice(10);
        bookRequest.setStockQuantity(1);
        when(bookRepository.save(book)).thenReturn(book);
        Book id = bookService.createBook(book);
        assertEquals(id,1L);
    }




    @Test
    void testDeleteProduct(){
        Book book = new Book("booktitle","author","test",10,1);
        book.setTitle("sample");
        book.setAuthor("sample1");
        book.setGenre("test");
        book.setPrice(10);
        book.setStockQuantity(1);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        String item = bookService.deleteBookById(1L);
        Mockito.verify(bookRepository).deleteById(1L);
    }
}
