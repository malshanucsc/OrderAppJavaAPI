package com.sysco.order.service;

import com.sysco.order.dao.ItemDetailDao;
import com.sysco.order.exception.EmptyItemException;
import com.sysco.order.model.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@PrepareForTest({ItemService.class})
public class ItemServiceTest {

    private Item item1 = new Item();
    private Item item2 = new Item();
    private List<Item> itemList = new ArrayList<>();

    @Mock
    ItemDetailDao itemDetailDao;

    @InjectMocks
    ItemService itemService;

    @Before
    public void setUp(){

        item1.setId(1);
        item1.setItemName("Carrot");
        item1.setUnit("Kg");
        item1.setUnitValue(1.7);

        item2.setId(2);
        item2.setItemName("Cabbage");
        item2.setUnit("Kg");
        item2.setUnitValue(1.8);

        itemList.add(item1);
        itemList.add(item2);
    }


    @Test
    public void getAllItems_Should_ReturnResponseEntityWithItemsList(){

        when(itemDetailDao.getAllItems()).thenReturn(itemList);
        ResponseEntity itemListResponse = itemService.getAllItems();
        Assert.assertNotNull(itemListResponse);
        Assert.assertEquals(itemList, itemListResponse.getBody());
        Assert.assertEquals(200,itemListResponse.getStatusCodeValue());
    }

    @Test
    public void getAllItems_Should_ReturnResponseEntityWithAnExceptionMessage(){
        when(itemDetailDao.getAllItems()).thenThrow(EmptyItemException.class);
        ResponseEntity itemListResponse = itemService.getAllItems();
        Assert.assertNotNull(itemListResponse);
        Assert.assertNull(itemListResponse.getBody());
        Assert.assertEquals(200,itemListResponse.getStatusCodeValue());
    }

    @Test
    public void getSingleItems_Should_ReturnResponseEntityWithAnItem_When_CorrectIdGiven(){

        when(itemDetailDao.getSingleItem("1")).thenReturn(item1);
        ResponseEntity itemListResponse = itemService.getSingleItem("1");
        Assert.assertNotNull(itemListResponse);
        Assert.assertEquals(item1, itemListResponse.getBody());
        Assert.assertEquals(200,itemListResponse.getStatusCodeValue());
    }

    @Test
    public void getSingleItems_Should_ReturnResponseEntityWithAnExceptionMessage_When_IncorrectIdGiven(){

        when(itemDetailDao.getSingleItem("12")).thenThrow(EmptyItemException.class);
        ResponseEntity itemListResponse = itemService.getSingleItem("12");
        Assert.assertNotNull(itemListResponse);
        Assert.assertNull(itemListResponse.getBody());
        Assert.assertEquals(404,itemListResponse.getStatusCodeValue());
    }


}
