package com.example.restservice.controllers;


import com.example.restservice.models.Book;
import com.example.restservice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
@CrossOrigin
public class BookController {
    @Autowired
    BookService bookService;

    //CRUD - create, retreive, update, delete


    @GetMapping(value = "/books" )
    @CrossOrigin
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = new ArrayList<>();
       this.bookService.findAll().forEach(book -> books.add(book));
       try{
           return new ResponseEntity<>(books, HttpStatus.OK);

       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
    @GetMapping(value = "/book/{id}" )
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable("id") long id) {
        try{
            Optional<Book> book = this.bookService.findById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/books/matching/{bookName}" )
    @CrossOrigin
    public ResponseEntity<List<Book>> getAccountByBookName(@PathVariable("bookName") String bookName) {
        try{
            List<Book> books = this.bookService.findByBookName(bookName);
            return new ResponseEntity<>(books, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/book/create" )
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        try{
            Book response = this.bookService.saveBook(book);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/books/delete-by-id/{id}" )
    public ResponseEntity deleteBookById(@PathVariable("id") Long id) {
        try{
            this.bookService.deleteBookById(id);
            return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/books/update-by-id" )
    public ResponseEntity updateBook(@RequestBody Book book) {
        try{
            this.bookService.updateBook(book);
            return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
