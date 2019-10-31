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
    private String img;
    private String description;

    private String createAt;
    private String updateAt;
    private String deleteAt;
    private String status;
    private Set<Book> listBook;

    public AuthorDTO() {
    }


    public AuthorDTO(Author author) {
        this.name = author.getName();
        this.img = author.getImg();
        this.description = author.getDescription();
        ObjectUtil.cloneObject(this, author);
        this.createAt = DateTimeUtil.formatDateFromLong(author.getCreateAt());
        this.updateAt = DateTimeUtil.formatDateFromLong(author.getUpdateAt());
        this.deleteAt = DateTimeUtil.formatDateFromLong(author.getDeleteAt());
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(String deleteAt) {
        this.deleteAt = deleteAt;
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
