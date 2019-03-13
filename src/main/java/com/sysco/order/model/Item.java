package com.sysco.order.model;

import lombok.Getter;
import lombok.Setter;
//getters and setters are automatically generated at compile time using lombok
@Getter
@Setter
public class Item {

    private Integer id;
    private String itemName;
    private double quantity;
    private double unitValue;
    private double subTotal;

    public Item(){

        this.itemName = "";
        this.quantity = 0.0;
        this.unitValue = 0.0;
        this.subTotal = 0.0;

    }

    public Item(String name,Double quantity, double unit_value, double subtot,int itemNumber){
        this.id=itemNumber;
        this.itemName = name;
        this.quantity = quantity;
        this.unitValue = unit_value;
        this.subTotal = subtot;

    }

}
