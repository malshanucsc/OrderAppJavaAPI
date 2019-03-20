package com.sysco.order.controller;

import com.sysco.order.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Item Rest End Points", description = "Shows the item info")
public class ItemController {

    @Autowired
    private ItemService itemService;

    //retreiving all items
    @ApiOperation(value="Returns all items")
    @GetMapping("/items")
    public ResponseEntity items(){
        return itemService.getAllItems();
    }

    //retrieve single item
    @ApiOperation(value="Returns an item when id given")
    @GetMapping("/items/{id}")
    public ResponseEntity item(@PathVariable String id){
        return itemService.getSingleItem(id);
    }

//    //create item
//    @PostMapping("/item")
//    public ResponseEntity create(@RequestBody Item newItem){
//
//        return itemService.createItem(newItem);
//
//
//    }
//
//    //deleting an item
//    @DeleteMapping(value="/item/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity delete(@PathVariable String id){
//
//        return itemService.deleteItem(id);
//
//    }
//
//    //updating the item
//    @PutMapping(value="/item", produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity update(@RequestBody Item currentItem){
//
//        return itemService.updateItem(currentItem);
//
//
//    }

}
