package com.fpt.service;

import com.fpt.entity.Book;
import com.fpt.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> books() {
        bookRepository.findAll(PageRequest.of(1,3));
        return bookRepository.findActiveBook(1);
    }

    public Page<Book> bookPaginate(int page, int limit) {
        return bookRepository.findAll(PageRequest.of(page - 1, limit));
    }

    public Book getById(long id) {
        return bookRepository.findById(id).orElse(null);
    }


    public Book create(Book book) {
        book.setId(Calendar.getInstance().getTimeInMillis());
        book.setStatus(1);
        book.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        book.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return bookRepository.save(book);
    }

    public Book update(Book book) {
        book.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return bookRepository.save(book);
    }

    public boolean delete(Book book) {
        book.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        book.setStatus(-1);
        bookRepository.save(book);
        return true;
    }
}
