package com.fpt.controller;

import com.fpt.entity.Book;
import com.fpt.entity.CartItem;
import com.fpt.entity.ShoppingCart;
import com.fpt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/shopping-card")
public class ShoppingCartMVCController {
    @Autowired
    private BookService bookService;

    //List Book Test Order
    @RequestMapping(method = RequestMethod.GET,value = "/")
    public String HomePage(Model model, HttpSession session){
        model.addAttribute("listBook",bookService.books());

        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("list-order");

        if (shoppingCart != null){

            Set<CartItem> listCartItem = new HashSet<>();
            for (Long hashMapKey:shoppingCart.getItems().keySet()
                 ) {
                CartItem cartItem = shoppingCart.getItems().get(hashMapKey);
                listCartItem.add(cartItem);
                System.out.println("quantity : " + cartItem.getQuantity());
                System.out.println("size list cart : " + listCartItem.size());
            }
            model.addAttribute("listCartItem",listCartItem);
        }


        return "homePage";
    }



    //CREATE NEW
    @RequestMapping(method = RequestMethod.GET, value = "/create/{bookId}")
    public String createNewCard(@PathVariable long bookId,
                                @RequestParam(defaultValue = "1") int quantity,
                                HttpSession session, Model model){
        Book book = bookService.getById(bookId);
        System.out.println(bookId);
        if (book == null){
            model.addAttribute("error-no-bookID",bookId);
            return "redirect:/shopping-card/";
        }

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        cartItem.setBookId(book.getId());
        cartItem.setBookName(book.getName());
        cartItem.setAuthorName(book.getAuthor().getName());
        cartItem.setBookPrice(book.getPrice());

        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("list-order");
        if (shoppingCart == null){
            shoppingCart = new ShoppingCart();
        }

        if (shoppingCart.getItems().containsKey(book.getId())){
            int currentQuantity = shoppingCart.getItems().get(book.getId()).getQuantity();

            currentQuantity += cartItem.getQuantity();
            cartItem.setQuantity(currentQuantity);
        }
        System.out.println("Name : "+ cartItem.getBookId() + " quantity Session : "+ cartItem.getQuantity());
        shoppingCart.setItems(cartItem.getBookId(),cartItem);

        session.setAttribute("list-order",shoppingCart);
        model.addAttribute("addnewSuccess","Order Success");

        return "redirect:/shopping-card/";
    }

    //UPDATE
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String updateCart(List<CartItem> items,
                             HttpSession session,
                             Model model){
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("list-order");
        if (shoppingCart == null){
            shoppingCart = new ShoppingCart();
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
        model.addAttribute("updateSuccess","Update Success");
        session.setAttribute("list-order",shoppingCart);
        return "redirect:homepage";
    }

    public String deleteCart(@PathVariable long bookId,
                             HttpSession session,
                             Model model){
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("list-order");
        if (shoppingCart == null){
            return "redirect:homepage";
        }
        if (shoppingCart.getItems().containsKey(bookId)){
            shoppingCart.getItems().remove(bookId);
        }
        model.addAttribute("deleteSuccess","Delete Sucess");
        session.setAttribute("list-order",shoppingCart);
        return "redirect:homepage";
    }
}
