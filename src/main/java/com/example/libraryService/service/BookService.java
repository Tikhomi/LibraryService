package com.example.libraryService.service;

import com.example.libraryService.dto.BookDTO;
import com.example.libraryService.entity.Book;
import com.example.libraryService.entity.Rental;
import com.example.libraryService.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title);
        if (book != null) {
            return convertToDTO(book);
        }
        return null;
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Long delete(Long id) {
        bookRepository.deleteById(id);
        return id;
    }

    private BookDTO convertToDTO(Book book) {
        List<Rental> activeRentals = book.getRentals().stream()
                .filter(Rental::isActive)
                .collect(Collectors.toList());
        return new BookDTO(book.getTitle(), book.getAuthor(), activeRentals);
    }
}
