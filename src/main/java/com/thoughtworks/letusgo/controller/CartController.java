package com.thoughtworks.letusgo.controller;

import com.thoughtworks.letusgo.domain.CartItem;
import com.thoughtworks.letusgo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<CartItem> getCartItems() {
        return cartService.getCartItems();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CartItem getCartItemById(@PathVariable("id") int id) {
        return cartService.getCartItemById(id);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CartItem getCartItemByItemId(@PathVariable("id") int id) {
        return cartService.getCartItemByItemId(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeCartItem(@PathVariable("id") int id) {
        cartService.removeCartItem(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCartItem(@RequestBody CartItem cartItem) {
        cartService.addCartItem(cartItem);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editCartItem(@RequestBody CartItem cartItem) {
        if(cartItem.getNumber() == 0){
            cartService.removeCartItem(cartItem.getId());
        } else{
            cartService.editCartItem(cartItem);
        }
    }
}
