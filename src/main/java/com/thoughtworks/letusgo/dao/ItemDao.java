package com.thoughtworks.letusgo.dao;

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
public class ItemDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Item> getItems() {

        final List<Item> items = new ArrayList<Item>();

        String sql = "SELECT * FROM item, category WHERE item.category_id=category.id";

        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                String categoryName = rs.getString("category.category_name");
                int categoryId = rs.getInt("item.category_id");
                Category category = new Category(categoryId, categoryName);

                Item item = new Item(rs.getInt("item.id"), rs.getString("item.barcode"), rs.getString("item.name"),
                        rs.getString("item.unit"), rs.getDouble("item.price"), category);

                items.add(item);
            }
        });

        return items;
    }

    public Item getItemById(final int id) {

        final Item item = new Item();

        String sql = "SELECT item.id, item.name, item.barcode, item.unit, item.price, item.category_id, category.id, category.category_name " +
                "FROM item,category WHERE item.id=? AND item.category_id = category.id";

        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                int categoryId = rs.getInt("item.category_id");
                String categoryName = rs.getString("category.category_name");
                Category category = new Category(categoryId, categoryName);
                item.setId(id);
                item.setName(rs.getString("item.name"));
                item.setBarcode(rs.getString("item.barcode"));
                item.setUnit(rs.getString("item.unit"));
                item.setPrice(rs.getDouble("item.price"));
                item.setCategory(category);
            }
        });

        return item;
    }

    public void addItem(Item item) {
        String sql = "INSERT INTO item (barcode, name, unit, price, category_id)" +
                "VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, item.getBarcode(), item.getName(),
                item.getUnit(), item.getPrice(), item.getCategory().getId());
    }

    public void editItem(Item item) {
        String sql = "UPDATE item SET item.barcode=?, item.name=?, item.unit=?, item.price=?, item.category_id=?" +
                "WHERE item.id=?";

        jdbcTemplate.update(sql, item.getBarcode(), item.getName(),
                item.getUnit(), item.getPrice(), item.getCategory().getId());
    }

    public void removeItem(int id) {
        String sql = "DELETE FROM item WHERE id=?";

        jdbcTemplate.update(sql, id);
    }

    public List<Item> getItemsByCategoryId(int id) {

        final List<Item> items = new ArrayList<Item>();

        String sql = "SELECT COUNT(*) FROM item, category WHERE category_id = ?";
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                String categoryName = rs.getString("category.category_name");
                int categoryId = rs.getInt("item.category_id");
                Category category = new Category(categoryId, categoryName);

                Item item = new Item(rs.getInt("item.id"), rs.getString("item.barcode"), rs.getString("item.name"),
                        rs.getString("item.unit"), rs.getDouble("item.price"), category);

                items.add(item);
            }
        });
        return items;
    }
}
