package com.fpt.repository;

import com.fpt.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    @Query("select h from Publisher as h where h.status = :status")
    List<Publisher> findActivePublisher(@Param("status") int status);
}
