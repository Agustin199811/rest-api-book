package com.uce.RestApiBook.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uce.RestApiBook.Entity.Book;
import com.uce.RestApiBook.Repository.BookRepository;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = {"https://react-book-frontend-production.up.railway.app/","http://localhost:3000/"})
@RestController
@RequestMapping("/api")
@Tag(name = "Api Rest for books use Swagger 3 - Books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/findByPriceRange")
    public ResponseEntity<List<Book>> findByPriceRange(){
        return ResponseEntity.ok(bookRepository.findByPriceBetween(20.0, 60.0));
    }

    @Operation(summary = "This method use for list books")
    @GetMapping("/books")
    public ResponseEntity<List<Book>> bookList() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @Operation(summary = "This method use for get book by id")
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getbookById(@PathVariable Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            return ResponseEntity.ok(optionalBook.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "This method use for create a book")
    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book newBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @Operation(summary = "This method use for update a book")
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        book.setId(id);
        Book bookUpdate = bookRepository.save(book);
        return ResponseEntity.ok(bookUpdate);
    }

    @Operation(summary = "This method use for delete a book by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}