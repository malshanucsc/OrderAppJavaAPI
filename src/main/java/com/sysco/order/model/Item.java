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
    private String unit;
    private double unitValue;

    public Item(){

    }

    public Item(int id,String itemName,String unit, double unit_value, double quantity){

        this.id = id;
        this.itemName = itemName;
        this.unit = unit;
        this.unitValue = unit_value;
        this.quantity = quantity;


    }



    public Item(String name,String unit, double unit_value, double subtot,int itemNumber){

        this.itemName = name;
        this.unit = unit;
        this.unitValue = unit_value;


    }

}
