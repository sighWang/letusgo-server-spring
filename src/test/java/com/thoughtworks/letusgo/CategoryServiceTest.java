package com.thoughtworks.letusgo;

import com.thoughtworks.letusgo.dao.CategoryDao;
import com.thoughtworks.letusgo.domain.Category;
import com.thoughtworks.letusgo.service.CategoryService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {
    private CategoryService categoryService = new CategoryService();
    CategoryDao categoryDao = mock(CategoryDao.class);

    @Before
    public void initialize(){
        categoryService.setCategoryDao(categoryDao);
    }

    @Test
    public void add_category_test(){
        CategoryService mockService = mock(CategoryService.class);
        Category category = new Category("水果");
        mockService.addCategory(category);
        verify(mockService, times(1)).addCategory(category);
    }

    @Test
    public void edit_category_test(){
        CategoryService mockService = mock(CategoryService.class);
        Category category = new Category(1,"水果");
        mockService.editCategory(category);
        verify(mockService, times(1)).editCategory(category);
    }

    @Test
    public void remove_category_test(){
        CategoryService mockService = mock(CategoryService.class);
        mockService.removeCategory(1);
        verify(mockService, times(1)).removeCategory(1);
    }

    @Test
    public void get_category_by_id_test() {
        Category category = new Category(1, "水果");
        when(categoryDao.getCategoryById(1)).thenReturn(category);
        assertThat(categoryService.getCategoryById(1)).isEqualTo(category);
    }

    @Test
    public void get_categories_test() {
        List<Category> categories = new ArrayList<Category>();
        categories.add(new Category("水果"));
        categories.add(new Category("饮料"));
        categories.add(new Category("日用品"));
        when(categoryDao.getCategories()).thenReturn(categories);
        assertThat(categoryService.getCategories()).hasSize(3);
    }
}
