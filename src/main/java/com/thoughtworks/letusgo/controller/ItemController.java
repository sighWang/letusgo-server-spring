package com.thoughtworks.letusgo.controller;

import com.thoughtworks.letusgo.domain.Item;
import com.thoughtworks.letusgo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Item getItemById(@PathVariable("id") int id) {
        return itemService.getItemById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeItem(@PathVariable("id") int id) {
        itemService.removeItem(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editItem(@RequestBody Item item) {
        itemService.addItem(item);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestBody Item item) {
        itemService.addItem(item);
    }

    @ModelAttribute("item")
    public Item getItemParameter(){
        return new Item();
    }
}
