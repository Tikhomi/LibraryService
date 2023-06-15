package com.example.LibraryService.controller.common;

import com.example.LibraryService.dto.BookDTO;
import com.example.LibraryService.entity.Book;
import com.example.LibraryService.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/books")
    public List<BookDTO> getAllBooks() throws AccessDeniedException {
        return bookService.getAllBooks();
    }
    @GetMapping("/book/{title}")
    public BookDTO getBookByTitle(@PathVariable String title) {
        return bookService.getBookByTitle(title);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void addUser(@RequestBody Book book) {
        bookService.save(book);
    }

    @DeleteMapping("admin/books/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        bookService.delete(id);
    }
}
