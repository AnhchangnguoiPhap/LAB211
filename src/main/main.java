package main;

import Report.Report;
import product.DataProduct;
import product.Product;
import WareHouse.DataWareHouse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import testcase.Menu;
import testcase.Valid;

public class main {

    private static DataProduct dp = new DataProduct();
    private static DataWareHouse dw = new DataWareHouse();
    private static Report r = new Report();

    public static void main(String[] args) throws IOException, ParseException {

        int choice;
        dp.loadFromFile("./product.dat");
        do {
            menuMain();
            choice = Valid.getInteger("Input your choice: ", "Please input [1..5]", 1, 5);
            switch (choice) {
                case 1:
                    menu(choice);
                    break;
                case 2:
                    menu(choice);
                    break;
                case 3:
                    menu(choice);
                    break;
                case 4:
                    menu(choice);
                    break;
            }
        } while (choice != 5);
    }

    public static void menu(int choice) {
        switch (choice) {
            case 1:
                menuProduct();
                break;
            case 2:
                menuWareHouse();
                break;
            case 3:
                menuReport();
                break;
            case 4:
                dp.saveToFile();
                break;
        }
    }

    public static void menuMain() {
        System.out.println("====WELCOME TO STORE MANAGEMENT AT CONVENIENCE STORE=====");
        System.out.println("|              1. PRODUCT                               |");
        System.out.println("|              2. WAREHOUSE                             |");
        System.out.println("|              3. REPORT                                |");
        System.out.println("|              4. Store data to file                    |");
        System.out.println("|              5. Exit                                  |");
        System.out.println("=========================================================");
    }

    public static void menuProduct() {
        System.out.println("______PRODUCT_______");
        System.out.println("1. Add new product  ");
        System.out.println("2. Updates product  ");
        System.out.println("3. Delete product   ");
        System.out.println("4. Show all products");
        System.out.println("____________________");
        int choice = Valid.getInteger("Input your choice: ", "Please input [1..4]", 1, 4);
        switch (choice) {
            case 1:
                dp.addProduct();
                break;
            case 2:
                dp.updateProduct();
                break;
            case 3:
                dp.deleteProduct();
                break;
            case 4:
                dp.showAll();
                break;
        }
    }

    public static void menuWareHouse() {
        System.out.println("_________WAREHOUSE__________");
        System.out.println("1. Create an import receipt ");
        System.out.println("2. Create an export receipt ");
        System.out.println("____________________________");
        int choice = Valid.getInteger("Input your choice: ", "Please input [1..2]", 1, 2);
        switch (choice) {
            case 1:
                dw.exportReceipt();
                break;
            case 2:
                dw.exportReceipt();
                break;
        }
    }

    public static void menuReport() {
        System.out.println("_________REPORT__________");
        System.out.println("1. Products that have expired ");
        System.out.println("2. The products that the store is selling ");
        System.out.println("3. Products that are running out of stock");
        System.out.println("4. Import/export receipt of a product.");
        System.out.println("____________________________");
        int choice = Valid.getInteger("Input your choice: ", "Please input [1..4]", 1, 4);
        switch (choice) {
            case 1:
                r.ExpiredProduct(dp.pr);
                break;
            case 2:
                r.productAreSelling(dp.pr);
                break;
            case 3:
                r.outOfStock(dp.pr);
                break;
        }
    }
}
