package com.thoughtworks.letusgo.service;

import com.thoughtworks.letusgo.dao.ItemDao;
import com.thoughtworks.letusgo.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemDao itemDao;

    public ItemService() {

    }

    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }


    public List<Item> getItems() {
        return itemDao.getItems();
    }

    public Item getItemById(int id) {
        return itemDao.getItemById(id);
    }

    public void addItem(Item item) {
        itemDao.addItem(item);
    }

    public void editItem(Item item) {
        itemDao.editItem(item);
    }

    public void removeItem(int id) {
        itemDao.removeItem(id);
    }

    public List<Item> getItemsByCategoryId(int id) {

        return itemDao.getItemsByCategoryId(id);

    }
}
