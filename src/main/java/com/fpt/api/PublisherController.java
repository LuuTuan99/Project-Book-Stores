package com.fpt.api;

import com.fpt.dto.PublisherDTO;
import com.fpt.entity.Book;
import com.fpt.entity.Publisher;
import com.fpt.rest.RESTPagination;
import com.fpt.rest.RESTResponse;
import com.fpt.service.BookService;
import com.fpt.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/publishers")
public class PublisherController {
    @Autowired
    PublisherService publisherService;

    @Autowired
    BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getList(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit) {
        Page<Publisher> publisherPage = publisherService.publisherPage(page, limit);

        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(publisherPage.getContent().stream().map(x -> new PublisherDTO(x)).collect(Collectors.toList()))
                .setPagination(new RESTPagination(page, limit, publisherPage.getTotalPages(), publisherPage.getTotalElements()))
                .build(), HttpStatus.OK);
    }


   @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody PublisherDTO publisherDTO) {
       Publisher publisher = new Publisher();
       publisher.setName(publisherDTO.getName());
       publisher.setAvatar(publisherDTO.getImg());
       publisher.setDescription(publisherDTO.getDescription());
            return new ResponseEntity<>(new RESTResponse.Success()
                      .setStatus(HttpStatus.OK.value())
                      .setMessage("Success!")
                      .addData(new PublisherDTO(publisherService.create(publisher)))
                      .build(), HttpStatus.OK);
   }

   @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable String id) {
        Publisher publisher = publisherService.getById(Long.parseLong(id));

        if (publisher == null) {
            return  new ResponseEntity<>(new RESTResponse.SimpleError()
                      .setCode(HttpStatus.NOT_FOUND.value())
                      .setMessage("Not Found!")
                      .build(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RESTResponse.Success()
                   .setStatus(HttpStatus.OK.value())
                   .setMessage("Success")
                   .addData(new PublisherDTO(publisherService.getById(Long.parseLong(id))))
                   .build(), HttpStatus.OK);
   }

   @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Object> update(@RequestBody Publisher updatePublisher, @PathVariable long id) {
        Publisher existPublisher = publisherService.getById(id);

        if (existPublisher == null) {
            return  new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not Found!")
                    .build(), HttpStatus.NOT_FOUND);
        }
        existPublisher.setName(updatePublisher.getName());
        return new ResponseEntity<>(new RESTResponse.Success()
                  .setStatus(HttpStatus.OK.value())
                  .setMessage("Success")
                  .addData(new PublisherDTO(publisherService.update(existPublisher)))
                  .build(), HttpStatus.OK);
   }

   @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
       Publisher existPublisher = publisherService.getById(id);

       if (existPublisher == null) {
           return  new ResponseEntity<>(new RESTResponse.SimpleError()
                   .setCode(HttpStatus.NOT_FOUND.value())
                   .setMessage("Not Found!")
                   .build(), HttpStatus.NOT_FOUND);
       }

       publisherService.delete(existPublisher);
       return new ResponseEntity<>(new RESTResponse.Success()
               .setStatus(HttpStatus.OK.value())
               .setMessage("Success")
               .build(), HttpStatus.OK);
   }
}
