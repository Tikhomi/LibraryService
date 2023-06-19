package com.example.LibraryService.service;

import com.example.LibraryService.dto.BookDTO;
import com.example.LibraryService.entity.Book;
import com.example.LibraryService.repository.BookRepository;
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

    private BookDTO convertToDTO(Book book) {
        return new BookDTO(book.getTitle(), book.getAuthor());
    }
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Long delete(Long id) {
        bookRepository.deleteById(id);
        return id;
    }
}
