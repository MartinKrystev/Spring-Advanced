package com.example.booksrestserver.services;

import com.example.booksrestserver.models.dtos.AuthorDto;
import com.example.booksrestserver.models.dtos.BookDto;
import com.example.booksrestserver.models.entities.AuthorEntity;
import com.example.booksrestserver.models.entities.BookEntity;
import com.example.booksrestserver.repositories.AuthorRepository;
import com.example.booksrestserver.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Optional<BookDto> findBookById(Long bookId) {
        return this.bookRepository.findById(bookId).map(this::map);
    }

    public Long createBook(BookDto newBook) {
        String authorName = newBook.getAuthor().getName();
        Optional<AuthorEntity> authorOpt = this.authorRepository.findAuthorEntityByName(authorName);

        BookEntity newBookEntity = new BookEntity()
                .setTitle(newBook.getTitle())
                .setIsbn(newBook.getIsbn())
                .setAuthor(authorOpt.orElseGet(() -> createNewAuthor(authorName)));
                //.setAuthor(authorOpt.isPresent() ? authorOpt.get() : createNewAuthor(authorName))

        return this.bookRepository.save(newBookEntity).getId();
    }

    private AuthorEntity createNewAuthor(String authorName) {
        return this.authorRepository.save(new AuthorEntity().setName(authorName));
    }

    public void deleteById(Long bookId) {
        this.bookRepository.deleteById(bookId);
    }

    public List<BookDto> getAllBooks() {
        return this.bookRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    private BookDto map(BookEntity bookEntity) {

        AuthorDto authorDto = new AuthorDto()
                .setName(bookEntity.getAuthor().getName());

        return new BookDto()
                .setId(bookEntity.getId())
                .setAuthor(authorDto)
                .setIsbn(bookEntity.getIsbn())
                .setTitle(bookEntity.getTitle());
    }
}
