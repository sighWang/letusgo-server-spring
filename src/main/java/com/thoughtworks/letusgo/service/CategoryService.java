package com.thoughtworks.letusgo.service;

import com.thoughtworks.letusgo.dao.CategoryDao;
import com.thoughtworks.letusgo.domain.Category;
import com.thoughtworks.letusgo.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ItemService itemService;


    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public void addCategory(Category category) {
        categoryDao.addCategory(category);
    }

    public void editCategory(Category category) {
        categoryDao.editCategory(category);
    }

    public void removeCategory(int id) {
        List<Item> items = itemService.getItemsByCategoryId(id);

        if(items.size() == 0) {
            categoryDao.removeCategory(id);
        }
    }

    public Category getCategoryById(int id) {
        return categoryDao.getCategoryById(id);
    }

    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }
}
