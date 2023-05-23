package com.example.restservice.services;

import com.example.restservice.models.Book;
import com.example.restservice.repositories.BookJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    @Autowired
    private BookJPARepository bookJPARepository; //property inject

    //constructor injection
    public BookService(){}

    public List<Book> findAll(){
        return bookJPARepository.findAll();
    }

    public Optional<Book> findById(Long id){
        return bookJPARepository.findById(id);
    }
    public List<Book> findByBookName(String bookName){
        return bookJPARepository.findByBookNameContains(bookName);
    }
    public Book saveBook(Book book){
        return bookJPARepository.save(book);
    }

    public void deleteBookById(Long id){
         bookJPARepository.deleteById(id);
    }

    public void updateBook(Book book){
         bookJPARepository.updateBook(book.getBookName(), book.getAuthor(), book.getQuantity(),
                 book.getPrice(), book.getId());
    }


}
