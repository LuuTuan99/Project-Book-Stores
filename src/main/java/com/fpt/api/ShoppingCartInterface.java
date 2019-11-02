package com.fpt.api;

import com.fpt.entity.Book;
import com.fpt.entity.CartItem;
import com.fpt.entity.ShoppingCart;
import org.springframework.http.ResponseEntity;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ShoppingCartInterface {

    ResponseEntity<Object> addCartItem(@PathVariable long bookId, @RequestParam int quantity, HttpSession session); // default

//    ResponseEntity<Object> addCartItem(@PathVariable long bookId, @RequestParam int quantity);

    ResponseEntity<Object> removeCartsItem(@PathVariable long bookId,@RequestParam int quantityDelete,HttpSession session); // default 1

    ResponseEntity<Object> updateCart(@RequestBody List<CartItem> items,HttpSession session); // default 1

    ResponseEntity<Object> getItems(HttpSession session);
}
