package com.example.BookManagement.controller;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.BookManagement.contract.BookRequest;
import com.example.BookManagement.model.Book;
import com.example.BookManagement.service.BookService;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Array.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;




    @Test
    public void testAddCourse() {
        Book book = new Book("booktitle","author","test",10,1);
        assertNull(book.getId());
        book.setId(1L);
        assertNotNull(book.getId());
        assertEquals("booktitle", book.getTitle());
        assertEquals("author", book.getAuthor());
        assertEquals("test",book.getGenre());
        assertEquals(10,book.getPrice());
        assertEquals(1,book.getStockQuantity());
    }

    @Test
    public void testGetBookById() throws Exception {

        Book book = new Book("booktitle","author","test",10,1);
       book.setId(1L);
        book.setTitle("sample");
        book.setAuthor("author1");
        book.setGenre("test");
        book.setPrice(10);
        book.setStockQuantity(1);
        when(bookService.getBookById(book.getId())).thenReturn(book);
        mockMvc.perform(get("/book")).andExpect(status().isOk());

    }

    @Test
    public void testGetAllBooks() throws Exception{
        Book book = new Book("booktitle","author","test",10,1);;
        book.setId(1L);
        book.setTitle("sample");
        book.setAuthor("sample1");
        book.setGenre("test");
        book.setPrice(10);
        book.setStockQuantity(1);
        List<Book> bookList=new ArrayList<>();
        bookList.add(book);
        when(bookService.getAllBooks()).thenReturn(bookList);
        mockMvc.perform(get("/books")).andExpect(status().isOk());



    }

    @Test
    public void testDeleteBook() throws Exception {

        doNothing().when(bookService).deleteBookById(1L);
        mockMvc.perform(delete("/courses/1"))
                .andExpect(status().isNoContent());
    }


    @Test
    public void testUpdateBook() throws Exception{
        BookRequest bookRequest = new BookRequest("booktitle","author","test",10,1);
        bookRequest.setTitle("sample");
        bookRequest.setAuthor("sample1");
        bookRequest.setGenre("test");
        bookRequest.setPrice(10);
        bookRequest.setStockQuantity(1);
        when(bookService.updateBookById(1L, bookRequest)).thenReturn(true);
        mockMvc.perform(put("/products/1"))
                .andExpect(status().isOk());
    }
}
