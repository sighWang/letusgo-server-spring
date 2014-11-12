package com.thoughtworks.letusgo;

import com.thoughtworks.letusgo.dao.ItemDao;
import com.thoughtworks.letusgo.domain.Category;
import com.thoughtworks.letusgo.domain.Item;
import com.thoughtworks.letusgo.service.ItemService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ItemServiceTest {


    private ItemService itemService;

    private ItemDao itemDao = mock(ItemDao.class);
    private Category category = new Category(1, "饮料");
    private Item item = new Item(1, "ITEM000001", "雪碧", "瓶", 3.50, category);
    @Before
    public void initialize() {
        itemService = new ItemService();
        List<Item> items = new ArrayList<Item>();

        items.add(new Item("ITEM000001", "雪碧", "瓶", 3.50, category));
        items.add(new Item("ITEM000002", "苹果", "斤", 10.00, category));
        items.add(new Item("ITEM000003", "香蕉", "斤", 5.00, category));

        when(itemDao.getItems()).thenReturn(items);
        when(itemDao.getItemById(1)).thenReturn(item);
        when(itemDao.getItemsByCategoryId(1)).thenReturn(items);
        itemService.setItemDao(itemDao);
    }
    
    @Test
    public void get_items_test() {
        assertThat(itemService.getItems()).hasSize(3);
    }

    @Test
    public void get_item_by_id_test() {
        assertThat(itemService.getItemById(1)).isEqualTo(item);
    }

    @Test
    public void add_item_test() {
        Item item2 = new Item("ITEM000001", "雪碧", "瓶", 3.50, category);
        when(itemDao.getItemById(1)).thenReturn(item2);
        ItemService mockItemService = mock(ItemService.class);
        mockItemService.addItem(item2);
        verify(mockItemService, times(1)).addItem(item2);
    }

    @Test
    public void edit_item_test() {
        ItemService mockItemService = mock(ItemService.class);
        mockItemService.editItem(item);
        verify(mockItemService, times(1)).editItem(item);
    }

    @Test
    public void remove_item_test() {
        ItemService mockItemService = mock(ItemService.class);
        mockItemService.removeItem(1);
        verify(mockItemService, times(1)).removeItem(1);
    }

    @Test
    public void get_items_by_category_id_test() {
        assertThat(itemService.getItemsByCategoryId(1)).hasSize(3);
    }
}
