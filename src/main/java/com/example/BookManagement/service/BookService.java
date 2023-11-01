package com.example.BookManagement.service;

import com.example.BookManagement.contract.BookRequest;
import com.example.BookManagement.contract.BookResponse;
import com.example.BookManagement.contract.ReviewRequest;
import com.example.BookManagement.contract.ReviewResponse;
import com.example.BookManagement.model.Book;
import com.example.BookManagement.model.Review;
import com.example.BookManagement.repository.BookRepository;
import com.example.BookManagement.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;
    private ModelMapper modelMapper = new ModelMapper();

@Autowired
    public BookService(BookRepository bookRepository, BookRepository reviewRepository, ReviewRepository reviewRepository1) {
        this.bookRepository = bookRepository;
    this.reviewRepository = reviewRepository1;
    this.modelMapper = modelMapper;
    }


    public BookResponse createBook(BookRequest bookRequest) {
        Book book = modelMapper.map(bookRequest, Book.class);
        Book savedUser = bookRepository.save(book);
        return modelMapper.map(savedUser, BookResponse.class);
    }

public List<BookResponse> getAllBooks() {
    List<Book> books = (List<Book>) bookRepository.findAll(); // Assuming you have a method in your repository to retrieve all books
    List<BookResponse> bookResponses = new ArrayList<>();

    for (Book book : books) {
        bookResponses.add(modelMapper.map(book, BookResponse.class));
    }

    return bookResponses;
}

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        Optional<Book> getById = bookRepository.findById(id);
        return modelMapper.map(book, BookResponse.class);
    }

    public BookResponse updateBookById(Long id, BookRequest request) {
        Book book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        modelMapper.map(request, book);
        Book bookResponse = bookRepository.save(book);
        return modelMapper.map(bookResponse, BookResponse.class);
    }


    public String deleteBookById(Long id) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(" Book not found with id: " + id));
        bookRepository.delete(book);
        return "deleted successfully";
    }

    public List<ReviewResponse> viewAllReviews(Long id) {
    Book book=bookRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Book Not found with Id" +id));
List<Review>reviews=reviewRepository.findByBook(book);
return reviews.stream().map(review -> modelMapper.map(review,ReviewResponse.class)).collect(Collectors.toList());
    }

    public List<ReviewResponse> addReview(Long id, ReviewRequest reviewRequest) {
Book book=bookRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Book not found with id :"+id));
Review review=Review.builder().userId(reviewRequest.getUserId()).comment(reviewRequest.getComment()).rating(reviewRequest.getRating()).createdAt(reviewRequest.getCreatedAt()).build();
Review savedReview =reviewRepository.save(review);
return Collections.singletonList(modelMapper.map(savedReview, ReviewResponse.class));

    }

}