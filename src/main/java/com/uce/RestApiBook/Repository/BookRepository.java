package com.uce.RestApiBook.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uce.RestApiBook.Entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {  
    List<Book> findByPriceBetween(double minPrice, double maxPrice);
}
