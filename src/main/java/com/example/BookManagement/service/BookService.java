package com.example.BookManagement.service;

import com.example.BookManagement.contract.BookRequest;
import com.example.BookManagement.contract.BookResponse;
import com.example.BookManagement.model.Book;
import com.example.BookManagement.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
//@AllArgsConstructor
//@NoArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }


    public BookResponse createBook(BookRequest bookRequest) {
        Book book = modelMapper.map(bookRequest, Book.class);
        Book savedUser = bookRepository.save(book);
        return modelMapper.map(savedUser, BookResponse.class);
    }
public List<Book> getAllBooks() {
    return (List<Book>) bookRepository.findAll();
}

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        Optional<Book> getById = bookRepository.findById(id);
        return modelMapper.map(getById, BookResponse.class);
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
}