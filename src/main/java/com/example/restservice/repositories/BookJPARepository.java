package com.example.restservice.repositories;

import com.example.restservice.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookJPARepository extends JpaRepository<Book, Long>{

    List<Book> findByBookNameContains(String bookName);

    @Query(value = "update java_class.book set book_name = :bookname, author = :author," +
            "quantity = :quantity, price = :price where id = :id", nativeQuery = true)
    void updateBook(@Param("bookname") String bookname, @Param("author") String author,
                                    @Param("quantity") int quantity,@Param("price") float price,  @Param("id") Long id);

}
