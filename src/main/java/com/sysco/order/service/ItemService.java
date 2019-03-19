package com.sysco.order.service;

import com.sysco.order.dao.ItemDetailDao;
import com.sysco.order.exception.EmptyItemException;
import com.sysco.order.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemDetailDao itemDetailDao;

    public ResponseEntity getAllItems(){
        try{
            List<Item> allItems = itemDetailDao.getAllItems();
            return ResponseEntity.status(200).body(allItems);
        }catch (EmptyItemException eie){
            return  ResponseEntity.status(200).body(eie.getMessage());
        }
    }

    public ResponseEntity getSingleItem(String id){
        try{
            Item singleItem = itemDetailDao.getSingleItem(id);
            return ResponseEntity.status(200).body(singleItem);
        }catch(EmptyItemException eie){
            return  ResponseEntity.status(404).body(eie.getMessage());
        }
    }

//    public ResponseEntity createOrder(OrderData newOrder){
//
//        try{
//
//            orderDetailDao.createOrder(newOrder);
//            return ResponseEntity.status(201).body(newOrder);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return  ResponseEntity.status(404).body("order_not_saved");
//        }
//
//    }
//
//    public ResponseEntity deleteOrder(String id){
//
//        try{
//
//            orderDetailDao.deleteOrder(id);
//            return ResponseEntity.status(201).body("order -deleted_successfully");
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return  ResponseEntity.status(404).body("order_not_deleted");
//        }
//
//    }
//
//    public ResponseEntity updateOrder(OrderData currentOrder){
//
//        try{
//
//            orderDetailDao.updateOrder( currentOrder,currentOrder.getId());
//            return ResponseEntity.status(200).body(currentOrder);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return  ResponseEntity.status(404).body("order_not_updated");
//        }
//
//    }


}
