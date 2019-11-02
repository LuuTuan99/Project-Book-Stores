package com.fpt.api;

import com.fpt.entity.Book;
import com.fpt.entity.CartItem;
import com.fpt.entity.ShoppingCart;
import com.fpt.rest.RESTResponse;
import com.fpt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController

@RequestMapping(value = "/api/v1/shopping-cart")
public class ShoppingCartController implements ShoppingCartInterface {

    @Autowired
    BookService bookService;

    // POST
    @RequestMapping(method = RequestMethod.POST, value = "/{bookId}")
=======
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/shopping-cart")
public class ShoppingCartController implements ShoppingCartInterface {
    List<CartItem> itemList = new ArrayList<>();
    @Autowired
    BookService bookService;
    @RequestMapping(method = RequestMethod.POST,value = "/{bookId}")
>>>>>>> 7099d85cc537bd94fb2b5f2347474f2cb7ab7055
    @Override
    public ResponseEntity<Object> addCartItem(@PathVariable long bookId,
                                              @RequestParam(defaultValue = "1") int quantity,
                                              HttpSession session) {
        // 1. tìm sách theo id.
        // 2. tạo ra shopping cart
        // 3. add cart item với số lượng 1.
<<<<<<< HEAD

        Book book = bookService.getById(bookId);
        if (book == null){
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("No Tim thay BookId :" + bookId)
                    .build(), HttpStatus.OK);
        }
        System.out.println(bookId + ": Book id database : " + book.getId());

        CartItem cartItem = new CartItem();
        cartItem.setBookId(book.getId());
        cartItem.setBookName(book.getName());
        cartItem.setBookPrice(book.getPrice());
        cartItem.setQuantity(quantity);
        cartItem.setAuthorName(book.getAuthor().getName());

        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("list-order");
        if (shoppingCart == null){
            shoppingCart = new ShoppingCart();
        }

        if (shoppingCart.getItems().containsKey(bookId)){
             int currentQuantity = shoppingCart.getItems().get(bookId).getQuantity();
             currentQuantity += cartItem.getQuantity();
             cartItem.setQuantity(currentQuantity);
             shoppingCart.setItems(cartItem.getBookId(),cartItem);
        }else {
            shoppingCart.setItems(bookId,cartItem);
        }


        session.setAttribute("list-order",shoppingCart);
        System.out.println("Session list :" + shoppingCart.getItems().size());
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Success!")
                .addData(cartItem)
=======
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
>>>>>>> 7099d85cc537bd94fb2b5f2347474f2cb7ab7055
                .build(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{bookId}")
    @Override
    public ResponseEntity<Object> removeCartsItem(@PathVariable long bookId,@RequestParam  int quantityDelete,HttpSession session) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("list-order");
        if (shoppingCart == null || shoppingCart.getItems().size() == 0) {
            return new ResponseEntity<Object>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.OK.value())
                    .setMessage("No Order")
                    .build(), HttpStatus.OK);
        }

        if (shoppingCart.getItems().containsKey(bookId)) {
            CartItem cartItem = shoppingCart.getItems().get(bookId);
            String bookName = cartItem.getBookName();
            System.out.println(quantityDelete);
            int currentQuantity = cartItem.getQuantity();

            if (currentQuantity <= quantityDelete) {

                shoppingCart.getItems().remove(bookId);

                System.out.println(shoppingCart.getItems().size());

            } else {
                currentQuantity -= quantityDelete;
                System.out.println(currentQuantity);
                cartItem.setQuantity(currentQuantity);
                shoppingCart.setItems(cartItem.getBookId(), cartItem);

            }

            if (shoppingCart != null && shoppingCart.getItems().size() != 0){
                session.setAttribute("list-order",shoppingCart);
            }else{
                session.removeAttribute("list-order");
            }

            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Delete " + quantityDelete + " " +bookName)
                    .build(),HttpStatus.OK);

        }

        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.OK.value())
                .setMessage("Not Tim thay : " + bookId )
                .build(),HttpStatus.OK);
        }

    // UPDATE
    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    @Override
    public ResponseEntity<Object> updateCart(@RequestBody List<CartItem> items,HttpSession session) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("list-order");

        if (shoppingCart == null) {
            return new ResponseEntity<Object>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.OK.value())
                    .setMessage("No Order")
                    .build(), HttpStatus.OK);
        }

        for (CartItem cartItem:items
             ) {
            if (shoppingCart.getItems().containsKey(cartItem.getBookId())){
                if (cartItem.getQuantity() <= 1){
                    cartItem.setQuantity(1);
                }
                shoppingCart.setItems(cartItem.getBookId(),cartItem);
            }
        }
        session.setAttribute("list-order",shoppingCart);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Update success")
                .addData(shoppingCart)
                .build(),HttpStatus.OK);
    }

    // GET
    @RequestMapping(method = RequestMethod.GET)
    @Override
    public ResponseEntity<Object> getItems(HttpSession session) {
        ShoppingCart hashMapShoppingCart =(ShoppingCart) session.getAttribute("list-order");


        if (hashMapShoppingCart == null){
            hashMapShoppingCart = new ShoppingCart();
        }
        System.out.println(hashMapShoppingCart.getItems().size());
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(hashMapShoppingCart)
                .build(), HttpStatus.OK);
    }
}
