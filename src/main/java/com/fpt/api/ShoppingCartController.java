package com.fpt.api;

import com.fpt.entity.Book;
import com.fpt.entity.CartItem;
import com.fpt.entity.ShoppingCart;
import com.fpt.rest.RESTResponse;
import com.fpt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/shopping-cart")
public class ShoppingCartController implements ShoppingCartInterface {
    List<CartItem> itemList = new ArrayList<>();
    @Autowired
    BookService bookService;
    @RequestMapping(method = RequestMethod.POST,value = "/{bookId}")
    @Override
    public ResponseEntity<Object> addCartItem(@PathVariable long bookId) {
        // 1. tìm sách theo id.
        // 2. tạo ra shopping cart
        // 3. add cart item với số lượng 1.
        Book book = bookService.getById(bookId);
        ShoppingCart shoppingCart = new ShoppingCart();
        CartItem cartItem = new CartItem();
        cartItem.setBookId(book.getId());
        cartItem.setAuthorName(book.getAuthor().getName());
        cartItem.setBookName(book.getName());
        cartItem.setQuantity(1);
        itemList.add(cartItem);
        shoppingCart.setItems(itemList);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Success!")
                .addData(shoppingCart)
                .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{bookId}")
    @Override
    public ResponseEntity<Object> addCartItem(@PathVariable long bookId, @RequestParam int quantity) {
        Book book = bookService.getById(bookId);
        ShoppingCart shoppingCart = new ShoppingCart();
        CartItem cartItem = new CartItem();
        cartItem.setBookId(book.getId());
        cartItem.setAuthorName(book.getAuthor().getName());
        cartItem.setBookName(book.getName());
        cartItem.setQuantity(quantity);
        itemList.add(cartItem);
        shoppingCart.setItems(itemList);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Success!")
                .addData(shoppingCart)
                .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{bookId}")
    @Override
    public ResponseEntity<Object> removeCartsItem(@PathVariable long bookId) {
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    @Override
    public ResponseEntity<Object> updateCart(@RequestBody List<CartItem> items) {
        return null;
    }

    @Override
    public ResponseEntity<Object> getItems() {
        return null;
    }
}
