package Order_package.Models;

import org.springframework.data.annotation.Id;

import java.util.List;


public class OrderData {

    @Id
    public String id;

    public String orderName;
    public String customerName;
    public double grandTotal;
    public String address;
    public List<Item> itemsList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderName() {
        return this.orderName;
    }

    public void setOrderName(String OrderName) {
        this.orderName = OrderName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public OrderData() {}

    public OrderData(String OrderName, String customerName, double grandTotal,String address,List<Item> itemsList) {

        this.orderName = OrderName;
        this.customerName = customerName;
        this.grandTotal = grandTotal;
        this.address = address;
        this.itemsList = itemsList;
    }

    @Override
    public String toString() {
        return String.format(
                "Order[id=%s, OrderName='%s', customerName='%s', grandTotal='%s, address='%s' itemList='%s']",
                id, orderName, customerName, grandTotal, address, itemsList);
    }




}
