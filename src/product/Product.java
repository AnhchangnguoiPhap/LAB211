package product;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import testcase.Valid;

public class Product {

    private String code;
    private String name;
    private Date date;
    private Date exp;
    private int quantity;
    private double price;

    public Product() {
    }

    public Product(String code, String name, Date date, Date exp, int quantity, double price) {
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateOfManufactureStr = sdf.format(date);
        String expirationDateStr = sdf.format(exp);
        return String.format("|%-12s|%-15s|%-25s|%-25s|%-10s|%-10.3f|",
                code, name, dateOfManufactureStr, expirationDateStr, quantity, price);
    }

}
