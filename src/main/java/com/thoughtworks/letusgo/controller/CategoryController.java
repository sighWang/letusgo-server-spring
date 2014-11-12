package com.thoughtworks.letusgo.controller;

import com.thoughtworks.letusgo.domain.Category;
import com.thoughtworks.letusgo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Category getCategoryById(@PathVariable("id") int id) {
        return categoryService.getCategoryById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeCategory(@PathVariable("id") int id) {
        categoryService.removeCategory(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editCategory(@RequestBody Category category) {
        categoryService.editCategory(category);
    }
}
