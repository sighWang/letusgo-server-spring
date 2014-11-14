package com.thoughtworks.letusgo.service;

import com.thoughtworks.letusgo.dao.CartDao;
import com.thoughtworks.letusgo.domain.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartDao cartDao;

    public void setCartDao(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public void addCartItem(CartItem cartItem) {
        cartDao.addCartItem(cartItem);
    }

    public void removeCartItem(int cartId) {
        cartDao.removeCartItem(cartId);
    }

    public void editCartItem(CartItem cartItem) {
        cartDao.editCartItem(cartItem);
    }

    public CartItem getCartItemById(int cartId) {
        return cartDao.getCartItemById(cartId);
    }

    public List<CartItem> getCartItems() {
        return cartDao.getCartItems();
    }

    public CartItem getCartItemByItemId(int id) {
        return cartDao.getCartItemByItemId(id);
    }
}
