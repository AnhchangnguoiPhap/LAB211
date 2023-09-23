package WareHouse;

import java.util.Date;

public class Warehouse {

    protected String code;
    protected String name;
    public Date date;
    public Date exp;
    protected int quantity;
    protected double price;

    public Warehouse() {
    }

    public Warehouse(String code, String name, Date date, Date exp, int quantity, double price) {
        this.code = code;
        this.name = name;
        this.date = date;
        this.exp = exp;
        this.quantity = quantity;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("|%-12s|%-15s|%-25s|%-25s|%-10s|%-10.3f|",
                code, name, date, exp, quantity, price);
    }

}
