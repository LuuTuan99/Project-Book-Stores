package com.fpt.api;

import com.fpt.dto.BookDTO;
import com.fpt.entity.Book;
import com.fpt.rest.RESTPagination;
import com.fpt.rest.RESTResponse;
import com.fpt.service.BookService;
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
    public ResponseEntity<Object> store(@RequestBody Book book) {
        return new ResponseEntity<>(new RESTResponse.Success()
                  .setStatus(HttpStatus.CREATED.value())
                  .setMessage("Success!")
                  .addData(new BookDTO(bookService.create(book)))
                  .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable long id) {
        Book book = bookService.getById(id);
        if (book == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                   .setCode(HttpStatus.NOT_FOUND.value())
                   .setMessage("Not Found!")
                   .build(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RESTResponse.Success()
                   .setStatus(HttpStatus.OK.value())
                   .setMessage("Success!")
                   .addData(new BookDTO(bookService.getById(id)))
                   .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable long id, @RequestBody Book bookUpdate) {
        Book existBook = bookService.getById(id);
        if (existBook == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                     .setCode(HttpStatus.NOT_FOUND.value())
                     .setMessage("Not Found!")
                     .build(), HttpStatus.NOT_FOUND);
        }

        existBook.setName(bookUpdate.getName());
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

        return new ResponseEntity<>(new RESTResponse.Success()
                   .setStatus(HttpStatus.OK.value())
                   .setMessage("Success!")
                   .build(), HttpStatus.OK);
    }

}
