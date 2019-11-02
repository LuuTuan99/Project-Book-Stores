package com.fpt.dto;

import com.fpt.entity.Author;
import com.fpt.entity.Book;
import com.fpt.util.DateTimeUtil;
import com.fpt.util.ObjectUtil;

import java.util.HashSet;
import java.util.Set;


public class AuthorDTO {
    private long id;
    private String name;
    private String avatar;
    private String description;

    private String createdAt;
    private String updatedAt;
    private String deletedAt;
    private String status;
    private Set<Book> listBook;

    public AuthorDTO() {
    }


    public AuthorDTO(Author author) {
        this.name = author.getName();
<<<<<<< HEAD
        this.img = author.getAvatar();
        this.description = author.getDescription();
        ObjectUtil.cloneObject(this, author);
        this.createAt = DateTimeUtil.formatDateFromLong(author.getCreatedAt());
        this.updateAt = DateTimeUtil.formatDateFromLong(author.getUpdatedAt());
        this.deleteAt = DateTimeUtil.formatDateFromLong(author.getDeletedAt());
=======
        this.avatar = author.getAvatar();
        this.description = author.getDescription();
        ObjectUtil.cloneObject(this, author);
        this.createdAt = DateTimeUtil.formatDateFromLong(author.getCreatedAt());
        this.updatedAt = DateTimeUtil.formatDateFromLong(author.getUpdatedAt());
        this.deletedAt = DateTimeUtil.formatDateFromLong(author.getDeletedAt());
>>>>>>> 7099d85cc537bd94fb2b5f2347474f2cb7ab7055
        this.status = author.getStatus() == 1 ? "Active" : "Deactive";


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Book> getListBook() {
        return listBook;
    }

    public void setListBook(Set<Book> listBook) {
        this.listBook = listBook;
    }
}

