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



    public OrderData() {}

    public OrderData(String orderName, String customerName, double grandTotal,String address,List<Item> itemsList) {

        this.orderName = orderName;
        this.customerName = customerName;
        this.grandTotal = grandTotal;
        this.address = address;
        this.itemsList = itemsList;
    }

    @Override
    public String toString() {
        return String.format(
                "Order[id=%s, orderName='%s', customerName='%s', grandTotal='%s, address='%s' itemList='%s']",
                id, orderName, customerName, grandTotal, address, itemsList);
    }




}
