package com.sl1ed.t1openschoolhw1.repository;

import com.sl1ed.t1openschoolhw1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
