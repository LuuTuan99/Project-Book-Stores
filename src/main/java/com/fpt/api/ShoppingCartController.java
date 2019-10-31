package com.fpt.api;

import com.fpt.entity.CartItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/shopping-cart")
public class ShoppingCartController implements ShoppingCartInterface {

    @RequestMapping(method = RequestMethod.POST)
    @Override
    public ResponseEntity<Object> addCartItem(@PathVariable long bookId) {
        // 1. tìm sách theo id.
        // 2. tạo ra shopping cart
        // 3. add cart item với số lượng 1.
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{bookId}")
    @Override
    public ResponseEntity<Object> addCartItem(@PathVariable long bookId, @RequestParam int quantity) {
        return null;
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
