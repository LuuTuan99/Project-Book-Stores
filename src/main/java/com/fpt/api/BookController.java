package com.fpt.api;

import com.fpt.dto.BookDTO;
import com.fpt.entity.Author;
import com.fpt.entity.Book;
import com.fpt.entity.Publisher;
import com.fpt.rest.RESTPagination;
import com.fpt.rest.RESTResponse;
import com.fpt.service.AuthorService;
import com.fpt.service.BookService;
import com.fpt.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/books")
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Autowired
    PublisherService publisherService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getList(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit) {
        Page<Book> bookPage = bookService.bookPaginate(page, limit);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(bookPage.getContent().stream().map(x -> new BookDTO(x)).collect(Collectors.toList()))
                .setPagination(new RESTPagination(page, limit, bookPage.getTotalPages(), bookPage.getTotalElements()))
                .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> store(@RequestParam(value = "authorId", required = false) String authorId,
                                        @RequestParam(value = "publisherId", required = false) String publisherId,
                                        @RequestBody BookDTO bookDTO) {
        Author author = authorService.getbyId(Long.parseLong(authorId));
        Publisher publisher = publisherService.getById(Long.parseLong(publisherId));
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setDescription(bookDTO.getDescription());
        book.setPhotos(bookDTO.getPhoto());
        book.setPrice(bookDTO.getPrice());
        book.setQuantity(bookDTO.getQuantity());
        book.setSaleOf(bookDTO.getSaleOf());
        book.setAuthor(author);
        book.setPublisher(publisher);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Success!")
                .addData(new BookDTO(bookService.create(book)))
                .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable String id) {
        Book book = bookService.getById(Long.parseLong(id));
        if (book == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not Found!")
                    .build(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Success!")
                .addData(new BookDTO(bookService.getById(Long.parseLong(id))))
                .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody BookDTO updateBookDTO) {
        Book existBook = bookService.getById(Long.parseLong(id));
        if (existBook == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not Found!")
                    .build(), HttpStatus.NOT_FOUND);
        }

        existBook.setName(updateBookDTO.getName());
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Success!")
                .addData(new BookDTO(bookService.update(existBook)))
                .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        Book existBook = bookService.getById(id);

        if (existBook == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not Found!")
                    .build(), HttpStatus.NOT_FOUND);
        }
        bookService.delete(existBook);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Success!")
                .build(), HttpStatus.OK);
    }

}
