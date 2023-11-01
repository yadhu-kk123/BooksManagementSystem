package com.example.BookManagement.service;
import com.example.BookManagement.contract.BookRequest;
import com.example.BookManagement.contract.BookResponse;
import com.example.BookManagement.contract.ReviewRequest;
import com.example.BookManagement.contract.ReviewResponse;
import com.example.BookManagement.model.Book;
import com.example.BookManagement.model.Review;
import com.example.BookManagement.repository.BookRepository;
import com.example.BookManagement.repository.ReviewRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
public class BookServiceTest {
    @InjectMocks
    private   BookService bookService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private ReviewRepository reviewRepository;
    private ModelMapper modelMapper;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetAllBooks(){
        Book book= new Book("booktitle","author","test",10,1);
        book.setId(1L);
        book.setTitle("sample");
        book.setAuthor("sample1");
        book.setGenre("test");
        book.setPrice(10);
        book.setStockQuantity(1);
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(book);
        if (bookService==notNull()&&bookRepository!=null){
            when(bookRepository.findAll()).thenReturn(bookList);
            List<BookResponse> items = bookService.getAllBooks();
            assertEquals(items.get(0).getId(),1L);
        }

    }
    @Test
    void testBookById(){
        Book book = new Book("booktitle","author","test",10,1);
        book.setTitle("sample");
        book.setAuthor("sample1");
        book.setGenre("test");
        book.setPrice(10);
        book.setStockQuantity(1);
        if (bookRepository!=null&&bookService!=null){
            when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
            BookResponse book1 = bookService.getBookById(1L);
            assertEquals(book1.getId(),1L);
        }
    }
    @Test
    public void testCreateBook() {
        BookRequest bookRequest = new BookRequest("booktitle","author","test",10,1);
        Book book = new Book();
        Book savedBook = new Book();
        BookResponse expectedResponse = new BookResponse(1L,"booktitle","author","test",10,1);

        if (modelMapper!=null && bookRepository==notNull()){
            Mockito.when(modelMapper.map(bookRequest, Book.class)).thenReturn(book);
            Mockito.when(bookRepository.save(book)).thenReturn(savedBook);
            Mockito.when(modelMapper.map(savedBook, BookResponse.class)).thenReturn(expectedResponse);
        }
        if (bookService!=null){
            BookResponse actualResponse = bookService.createBook(bookRequest);
            Mockito.verify(bookRepository).save(book);
            Assert.assertEquals(expectedResponse, actualResponse);
        }
    }
    @Test
    public void testUpdateBookById() {
        Long id = 1L;
        Book book =
                new Book(1L,
                        "sample",
                        "author1",
                        "horror"
                        ,50.2,5,

                        new ArrayList<>());
        BookRequest bookRequest =
                new BookRequest("sample","sample", "horror", 50.5, 5);
        Book updateBook =
                new Book(1L,
                        "Updated sample",
                        "sample1",
                        "horror",
                        102.5,8,
                        new ArrayList<>());
        BookResponse expectedResponse = new ModelMapper().map(updateBook, BookResponse.class);
if (bookRepository != null){
    when(bookRepository.findById(id)).thenReturn(Optional.of(book));
    when(bookRepository.save(any(Book.class))).thenReturn(updateBook);

    BookResponse actualResponse = bookService.updateBookById(id, bookRequest);

    assertEquals(expectedResponse, actualResponse);

}

    }

@Test
void testDeleteBookById() {
    Long id = 1L;
    Book book =
            new Book(
                    1,
                    "sample",
                    "genre1",
                    "sample",
                    2.2,10,
                    new ArrayList<>());
    if (bookRepository!=null){
        when(bookRepository.findById(any())).thenReturn(Optional.of(book));
        doNothing().when(bookRepository).delete(any());
    }

if (bookService!=null){
    String message = bookService.deleteBookById(book.getId());
    assertEquals(message, "deleted successfully");
}
}
    @Test
    public void testGetAllReviews() {
        Long id = 1L;
        Book book =
                new Book(1L,
                        "author",
                        "sample",
                        "genre1",
                        12.2,
                        2,
                        new ArrayList<>());
        Review review =
                Review.builder()
                        .id(id)
                        .userId(1)
                        .comment("Great Book!")
                        .rating(3.5)
                        .build();
        List<Review> reviews = Arrays.asList(review);
        ReviewResponse expectedResponse = new ModelMapper().map(review, ReviewResponse.class);
        List<ReviewResponse> expectedResponses = Arrays.asList(expectedResponse);
        if (bookRepository!=null){
            when(bookRepository.findById(id)).thenReturn(Optional.of(book));
            when(reviewRepository.findByBook(book)).thenReturn(reviews);

            List<ReviewResponse> actualResponses = bookService.viewAllReviews(id);

            assertEquals(expectedResponses, actualResponses);
        }
    }
    @Test
    public void testAddReview() {
        Long id = 1L;
        Book book =
                new Book(
                        1,
                        "title",
                        "sample",
                        "horror",
                        20.1,
                        2,
                        new ArrayList<>());
        ReviewRequest reviewRequest = new ReviewRequest(1, "Great book!");
        Review review =
                Review.builder()
                        .id(id)
                        .userId(1)
                        .comment("Great book")
                        .rating(3.6)
                        .book(book)
                        .build();
        ReviewResponse expectedResponse = new ModelMapper().map(review, ReviewResponse.class);
if(bookRepository!=null){
    when(bookRepository.findById(id)).thenReturn(Optional.of(book));
    when(reviewRepository.save(any(Review.class))).thenReturn(review);

    ReviewResponse actualResponse = (ReviewResponse) bookService.addReview(id,reviewRequest);

    assertEquals(expectedResponse, actualResponse);
}

    }

}
