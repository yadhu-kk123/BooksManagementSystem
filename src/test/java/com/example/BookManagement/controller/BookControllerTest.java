package com.example.BookManagement.controller;
import com.example.BookManagement.contract.BookRequest;
import com.example.BookManagement.contract.BookResponse;
import com.example.BookManagement.contract.ReviewRequest;
import com.example.BookManagement.contract.ReviewResponse;
import com.example.BookManagement.model.Book;
import com.example.BookManagement.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BookController.class})
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    @Test
    public void testAddBook() {
        Book book = new Book("booktitle", "author", "test", 10, 1);
        assertNull(book.getId());
        book.setId(1L);
        assertNotNull(book.getId());
        assertEquals("booktitle", book.getTitle());
        assertEquals("author", book.getAuthor());
        assertEquals("test", book.getGenre());
        assertEquals(10, book.getPrice());
        assertEquals(1, book.getStockQuantity());
    }

    @Test
    public void testGetBookById() throws Exception {
        Long bookId = 1L;
        BookResponse expectedResponse = new BookResponse();
        Mockito.when(bookService.getBookById(anyLong())).thenReturn(expectedResponse);
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void testGetAllBooks() throws Exception {
        List<BookResponse> bookResponses = new ArrayList<>();
        bookResponses.add(
                new BookResponse(1L, "Test1", "genre1", 22.5, 1));
        bookResponses.add(
                new BookResponse(2L, "Test2", "comic", "genre2", 20,3));
if(bookService!=null){
    when(bookService.getAllBooks()).thenReturn(bookResponses);

    mockMvc.perform(MockMvcRequestBuilders.get("/books"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(new ObjectMapper().writeValueAsString(bookResponses)));
}
    }

@Test
public void testDeleteBookById() throws Exception {
    Long id = 1L;
    String message = "data deleted";

    when(bookService.deleteBookById(id)).thenReturn(message);

    mockMvc.perform(delete("/books/" + id).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(message));

    verify(bookService, times(1)).deleteBookById(id);
}


    @Test
    public void testUpdateBookById() throws Exception {
        Long id = 1L;
        BookRequest bookRequest =
                new BookRequest("title", "test", "testt", 20,5);
        BookResponse bookResponse =
                new BookResponse(id, "UpdatedTitle", "test1", 30, 2);

        when(bookService.updateBookById(eq(id), any(BookRequest.class))).thenReturn(bookResponse);

        mockMvc.perform(
                        put("/books/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(bookRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(bookResponse)));

        verify(bookService, times(1)).updateBookById(eq(id), any(BookRequest.class));
    }
    @Test
    public void testAddReview() throws Exception {
        Long id = 1L;
        ReviewRequest reviewRequest = new ReviewRequest(1, "Great book!");
        ReviewResponse reviewResponse =
                new ReviewResponse(1,1,1,"test","20-02-2020");

        when(bookService.addReview(eq(id), any(ReviewRequest.class))).thenReturn((List<ReviewResponse>) reviewResponse);

        mockMvc.perform(
                        post("/books/" + id + "/reviews")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(reviewRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(reviewResponse)));

        verify(bookService, times(1)).addReview(eq(id), any(ReviewRequest.class));
    }

    @org.junit.jupiter.api.Test
    public void testViewAllReviews() throws Exception {
        Long id = 1L;
        List<ReviewResponse> reviewResponses =
                Arrays.asList(
                        new ReviewResponse(1,1, 3, "great book","20-02-2020"),
                        new ReviewResponse(1,1, 3, "great book", "20-02-2020"));

        when(bookService.viewAllReviews(id)).thenReturn(reviewResponses);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/" + id + "/reviews").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(reviewResponses)));

        verify(bookService, times(1)).viewAllReviews(id);
    }
}
