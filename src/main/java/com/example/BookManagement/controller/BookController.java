package com.example.BookManagement.controller;
import com.example.BookManagement.contract.BookRequest;
import com.example.BookManagement.contract.BookResponse;
import com.example.BookManagement.contract.ReviewRequest;
import com.example.BookManagement.contract.ReviewResponse;
import com.example.BookManagement.repository.ReviewRepository;
import com.example.BookManagement.service.BookService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
//@NoArgsConstructor
//@AllArgsConstructor
public class BookController {
private final BookService bookService;
private final ReviewRepository reviewRepository;

    public BookController(BookService bookService, ReviewRepository reviewRepository) {
        this.bookService = bookService;
        this.reviewRepository = reviewRepository;
    }
    @PostMapping("/create")
    public BookResponse createBookData(@RequestBody BookRequest bookRequest){
        return bookService.createBook(bookRequest);
    }
@GetMapping
public List<BookResponse> getAllBooks() {
  return bookService.getAllBooks();
}
    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id){
       return bookService.getBookById(id);
    }
    @PutMapping("update/{id}")
    public BookResponse updateBookById(@PathVariable Long id,@RequestBody BookRequest request ){
        return bookService.updateBookById(id,request);
    }
    @DeleteMapping("delete/{id}")
    public String deleteBookById(@PathVariable Long id) {
        return bookService.deleteBookById(id);
}
@PostMapping("/{id}/reviews")
public List<ReviewResponse> addReview(@PathVariable Long id, @RequestBody ReviewRequest reviewRequest){
        return bookService.addReview(id,reviewRequest);

}
@GetMapping("/{id}/reviews")
public List<ReviewResponse>viewAllReviews(@PathVariable Long id){
return bookService.viewAllReviews(id);
}
}
