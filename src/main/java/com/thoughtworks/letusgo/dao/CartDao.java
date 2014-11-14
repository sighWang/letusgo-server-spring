package com.thoughtworks.letusgo.dao;

import com.thoughtworks.letusgo.domain.CartItem;
import com.thoughtworks.letusgo.domain.Category;
import com.thoughtworks.letusgo.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addCartItem(CartItem cartItem) {
        String sql = "INSERT INTO cart (item_id, number)" +
                "values (?, ?)";
        jdbcTemplate.update(sql, cartItem.findItemId(), cartItem.getNumber());
    }

    public void removeCartItem(int cartId) {
        String sql = "DELETE FROM cart WHERE id = ?";
        jdbcTemplate.update(sql, cartId);
    }

    public void editCartItem(CartItem cartItem) {
        String sql = "UPDATE cart SET item_id = ?, number = ? WHERE id = ?";
        jdbcTemplate.update(sql, cartItem.findItemId(), cartItem.getNumber(), cartItem.getId());
    }

    public CartItem getCartItemById(int id) {
        final Item item = new Item();
        final CartItem cartItem = new CartItem();
        String sql = "SELECT item.*,cart.*,category.* FROM item,cart,category" +
                " WHERE cart.id = ? AND cart.item_id = item.id AND item.category_id = category.id";

        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                int categoryId = rs.getInt("category.id");
                String categoryName = rs.getString("category.category_name");
                item.setId(rs.getInt("item_id"));
                item.setBarcode(rs.getString("item.barcode"));
                item.setName(rs.getString("item.name"));
                item.setPrice(rs.getDouble("item.price"));
                item.setUnit(rs.getString("item.unit"));
                item.setCategory(new Category(categoryId, categoryName));
                cartItem.setItem(item);
                cartItem.setNumber(rs.getDouble("cart.number"));
            }
        });
        return cartItem;
    }

    public List<CartItem> getCartItems() {

        final List<CartItem> cartItems = new ArrayList<CartItem>();

        String sql = "SELECT item.*,cart.*,category.* FROM item,cart,category" +
                " WHERE cart.item_id = item.id AND item.category_id = category.id";

        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Category category = new Category(rs.getInt("category.id"), rs.getString("category.category_name"));
                Item item = new Item(rs.getInt("item.id"), rs.getString("item.barcode"), rs.getString("item.name"),
                        rs.getString("item.unit"), rs.getDouble("item.price"), category);
                int id = rs.getInt("cart.id");
                CartItem cartItem = new CartItem(id,item, rs.getDouble("cart.number"));
                cartItems.add(cartItem);
            }
        });
        return cartItems;
    }

    public CartItem getCartItemByItemId(int id) {
        String sql = "SELECT id,cart.number from cart where item_id = ?";
        final CartItem cartItem = new CartItem();
        jdbcTemplate.query(sql, new Object[] {id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
               cartItem.setId(rs.getInt("id"));
                cartItem.setNumber(rs.getDouble("cart.number"));
            }
        });

        return cartItem;
    }
}
