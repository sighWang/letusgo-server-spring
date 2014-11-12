package com.thoughtworks.letusgo;

import com.thoughtworks.letusgo.dao.CartDao;
import com.thoughtworks.letusgo.domain.CartItem;
import com.thoughtworks.letusgo.domain.Item;
import com.thoughtworks.letusgo.service.CartService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    private CartService cartService;
    private CartDao cartDao = mock(CartDao.class);
    private double number = 1;
    private CartItem cartItem = new CartItem(new Item(), number);
    List<CartItem> cartItems = new ArrayList<CartItem>();

    @Before
    public void initialize() {
        Item item1 = new Item();
        Item item2 = new Item();
        cartItems.add(new CartItem(item1, 2));
        cartItems.add(new CartItem(item2, 1));

        cartService = new CartService();
        cartService.setCartDao(cartDao);
        when(cartDao.getCartItemById(1)).thenReturn(cartItem);
        when(cartDao.getCartItems()).thenReturn(cartItems);
    }

    @Test
    public void add_to_cart_test() {
        Item item = new Item();
        CartItem cartItem = new CartItem(item, 1.0);
        CartService mockService = mock(CartService.class);
        mockService.addCartItem(cartItem);
        verify(mockService, times(1)).addCartItem(cartItem);
    }

    @Test
    public void remove_cart_item_test() {
        int cartId =1;
        CartService mockService = mock(CartService.class);
        mockService.removeCartItem(cartId);
        verify(mockService, times(1)).removeCartItem(cartId);
    }

    @Test
    public void edit_cart_item_test() {
        int number = 2;
        CartItem cartItem = new CartItem(new Item(), number);
        CartService mockService = mock(CartService.class);
        mockService.editCartItem(cartItem);
        verify(mockService, times(1)).editCartItem(cartItem);
    }

    @Test
    public void get_cartItem_by_id_test() {
        int cartId = 1;
        assertThat(cartService.getCartItemById(cartId)).isEqualTo(cartItem);
    }

    @Test
    public void get_cartItems_test() {
        assertThat(cartService.getCartItems()).isEqualTo(cartItems);
    }

}
