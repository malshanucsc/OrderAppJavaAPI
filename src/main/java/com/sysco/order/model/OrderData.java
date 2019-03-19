package com.sysco.order.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

//getters and setters are automatically generated at compile time using lombok

@Getter
@Setter
public class OrderData {


    private int id;
    private String orderName;
    private String customerName;
    private double grandTotal;
    private String address;
    private List<Item> itemsList;




    @Override
    public String toString() {
        return String.format(
                "Order[id=%s, OrderName='%s', customerName='%s', grandTotal='%s, address='%s' itemList='%s']",
                id, orderName, customerName, grandTotal, address, itemsList);
    }




}
