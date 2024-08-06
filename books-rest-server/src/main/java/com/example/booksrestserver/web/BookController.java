package com.example.booksrestserver.web;

import com.example.booksrestserver.models.dtos.BookDto;
import com.example.booksrestserver.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long bookId) {
        Optional<BookDto> theBook = this.bookService.findBookById(bookId);

        return theBook.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

//        if (theBook.isPresent()){
//            return ResponseEntity.ok(theBook.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> deleteBookById(@PathVariable("id") Long bookId) {
        this.bookService.deleteById(bookId);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping()
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto newBook,
                                              UriComponentsBuilder uriComponentsBuilder) {
        Long newBookId = this.bookService.createBook(newBook);

        return ResponseEntity.created(uriComponentsBuilder
                        .path("/api/books/{id}").build(newBookId)).build();
    }
    //
}
