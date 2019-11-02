package com.fpt.dto;

import com.fpt.entity.Book;
import com.fpt.util.DateTimeUtil;
import com.fpt.util.ObjectUtil;

public class BookDTO {

    private long id;
    private String name;
    private String description;
    private double price;
    private String photo;
    private int quantity;
    private double saleOf;
    private String authorName;
    private String publisherName;

    private String createdAt;
    private String updatedAt;
    private String deletedAt;
    private String status;

    public BookDTO() {
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.price = book.getPrice();
<<<<<<< HEAD
        this.img = book.getPhotos();
        this.quantity = book.getQuantity();
        this.sale = book.getSaleOf();
        ObjectUtil.cloneObject(this, book);
        this.authorName = book.getAuthor().getName();
        this.publisherName = book.getPublisher().getName();
        this.createAt = DateTimeUtil.formatDateFromLong(book.getCreatedAt());
        this.updateAt = DateTimeUtil.formatDateFromLong(book.getUpdatedAt());
        this.deleteAt = DateTimeUtil.formatDateFromLong(book.getDeletedAt());
=======
        this.photo = book.getPhotos();
        this.quantity = book.getQuantity();
        this.saleOf = book.getSaleOf();
        ObjectUtil.cloneObject(this, book);
        this.authorName = book.getAuthor().getName();
        this.publisherName = book.getPublisher().getName();
        this.createdAt = DateTimeUtil.formatDateFromLong(book.getCreatedAt());
        this.updatedAt = DateTimeUtil.formatDateFromLong(book.getUpdatedAt());
        this.deletedAt = DateTimeUtil.formatDateFromLong(book.getDeletedAt());
>>>>>>> 7099d85cc537bd94fb2b5f2347474f2cb7ab7055
        this.status = book.getStatus() == 1 ? "Active" : "Deactive";

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSaleOf() {
        return saleOf;
    }

    public void setSaleOf(double saleOf) {
        this.saleOf = saleOf;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
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
}
