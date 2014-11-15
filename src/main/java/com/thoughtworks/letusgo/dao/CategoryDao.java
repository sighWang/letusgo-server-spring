package com.thoughtworks.letusgo.dao;

import com.thoughtworks.letusgo.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addCategory(Category category) {
        String sql = "INSERT INTO category (category_name) VALUES (?)";

        jdbcTemplate.update(sql, category.getCategoryName());
    }

    public void editCategory(Category category) {
        String sql = "UPDATE category SET category_name=? WHERE id=?";

        jdbcTemplate.update(sql, category.getCategoryName(), category.getId());
    }

    public void removeCategory(int id) {
        String sql = "DELETE FROM category WHERE id = ?";
        System.out.println(id);
        jdbcTemplate.update(sql, id);
    }

    public Category getCategoryById(final int id) {

        final Category category = new Category();
        String sql = "SELECT id, category_name FROM category WHERE id=?";

        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                category.setId(id);
                category.setCategoryName(rs.getString("category_name"));
            }
        });
        return category;
    }

    public List<Category> getCategories() {

        final List<Category> categories = new ArrayList<Category>();

        String sql = "SELECT id, category_name FROM category";

        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Category category = new Category(rs.getInt("id"), rs.getString("category_name"));
                categories.add(category);
            }
        });
        return categories;
    }
}
