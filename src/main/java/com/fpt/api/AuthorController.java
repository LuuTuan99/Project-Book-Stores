package com.fpt.api;

import com.fpt.dto.AuthorDTO;
import com.fpt.entity.Author;
import com.fpt.entity.Book;
import com.fpt.rest.RESTPagination;
import com.fpt.rest.RESTResponse;
import com.fpt.service.AuthorService;
import com.fpt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/authors")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getList(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit) {
        Page<Author> authorPage = authorService.authorPage(page, limit);

        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(authorPage.getContent().stream().map(x -> new AuthorDTO(x)).collect(Collectors.toList()))
                .setPagination(new RESTPagination(page, limit, authorPage.getTotalPages(), authorPage.getTotalElements()))
                .build(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> store(@RequestBody AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(Calendar.getInstance().getTimeInMillis());
        author.setName(authorDTO.getName());
        author.setImg(authorDTO.getImg());
        author.setDescription(authorDTO.getDescription());
        return new ResponseEntity<>(new RESTResponse.Success()
                  .setStatus(HttpStatus.CREATED.value())
                  .setMessage("Success!")
                  .addData(new AuthorDTO(authorService.create(author)))
                  .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable String id) {
        Author author = authorService.getbyId(Long.parseLong(id));
        if (author == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                   .setCode(HttpStatus.NOT_FOUND.value())
                   .setMessage("Not Found!")
                   .build(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RESTResponse.Success()
                  .setStatus(HttpStatus.OK.value())
                  .setMessage("Success!")
                  .addData(new AuthorDTO(authorService.getbyId(Long.parseLong(id))))
                  .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable long id, @RequestBody Author updateAuthor) {
        Author existAuthor = authorService.getbyId(id);
         if (existAuthor == null) {
             return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not Found!")
                    .build(), HttpStatus.NOT_FOUND);
         }
         existAuthor.setName(updateAuthor.getName());
         return new ResponseEntity<>(new RESTResponse.Success()
                  .setStatus(HttpStatus.OK.value())
                  .setMessage("Success!")
                  .addData(new AuthorDTO(authorService.update(existAuthor)))
                  .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {

        Author existAuthor = authorService.getbyId(id);
        if (existAuthor == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not Found!")
                    .build(), HttpStatus.NOT_FOUND);
        }

        authorService.delete(existAuthor);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Success!")
                .build(), HttpStatus.OK);
    }



}
