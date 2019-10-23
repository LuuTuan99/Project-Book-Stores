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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

}
