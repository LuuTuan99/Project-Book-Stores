package com.fpt.service;

import com.fpt.entity.Author;
import com.fpt.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Author addAuthor(Author author){
        author.setCreateAt(Calendar.getInstance().getTimeInMillis());
        author.setUpdateAt(Calendar.getInstance().getTimeInMillis());
        author.setDescription("dang test thoi");
        author.setImg("chua co anhh");
        author.setStatus(1);
        return authorRepository.save(author);
    }
    public List<Author> listAuthor() {
        return authorRepository.findAll();
    }
}
