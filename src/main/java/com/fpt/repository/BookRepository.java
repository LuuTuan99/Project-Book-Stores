package com.fpt.repository;

import com.fpt.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select h from Book as h where h.status = :status")
    List<Book> findActiveBook(@Param("status") int status);

    List<Book> findAllByStatus(int status);
}
