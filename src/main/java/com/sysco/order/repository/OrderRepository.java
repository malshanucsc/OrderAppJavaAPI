//package com.sysco.order.repository;
//
//import com.sysco.order.model.OrderData;
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//import java.util.List;
//
//public interface OrderRepository extends MongoRepository<OrderData, String> {
//
//    List<OrderData> findByOrderName(String OrderName);
//    List<OrderData> findByCustomerName(String customerName);
//    OrderData findById(String id);
//
//
//}
