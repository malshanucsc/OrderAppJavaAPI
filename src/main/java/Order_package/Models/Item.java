package Order_package.Models;

public class Item {
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

    public Item(String name,Double quantity, double unit_value, double subtot){
        this.itemName = name;
        this.quantity = quantity;
        this.unitValue = unit_value;
        this.subTotal = subtot;

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(double unitValue) {
        this.unitValue = unitValue;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
