package product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import testcase.Valid;

public class DataProduct {

    private boolean saved = false;
    public static ArrayList<Product> pr;

    public DataProduct() {
        pr = new ArrayList<Product>();
    }

    public boolean loadFromFile(String path) {
        File f = new File(path);
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line;
            while ((line = bfr.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, "|");
                String id = st.nextToken().trim();
                String name = st.nextToken().trim();

                String expirationDateStr = st.nextToken().trim();
                String productDateStr = st.nextToken().trim();

                Date expirationDate = Valid.dF.parse(expirationDateStr);
                Date dateOfManufacture = Valid.dF.parse(productDateStr);
                int quantity = Integer.parseInt(st.nextToken().trim());
                double price = Double.parseDouble(st.nextToken().trim());
                pr.add(new Product(id, name, expirationDate, dateOfManufacture, quantity, price));
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error");
            return false;
        }
    }

    //search code de add product, neu trung code => error 
    public int searchCode(String code) {
        if (pr.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < pr.size(); i++) {
            if (pr.get(i).getCode().equalsIgnoreCase(code)) {
                return i;
            }
        }
        return -1;
    }

    //tim doi tuong = Code => updateProduct
    public Product searchByCode(String code) {
        if (pr.isEmpty()) {
            return null;
        }
        for (int i = 0; i < pr.size(); i++) {
            if (pr.get(i).getCode().equalsIgnoreCase(code)) {
                return pr.get(i);
            }
        }
        return null;
    }

    public static Date isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date;
    }

    public void addProduct() {
        String check;
        do {
            String code = Valid.checkDuplicate("Input code: ", "Duplicated code, please input again!", pr);
            String name = Valid.getString("Input name of product: ", "Name cannot be blank!.");
            Date date = Valid.dateFormat("Input date (dd/MM/yyyy): ", "Invalid date form");
            Date exp = Valid.dateFormat("Input exp (dd/MM/yyyy): ", "Invalid date");

            int quantity = Valid.getInteger("Input quantity: ", "Quantity must be more than 0", 0, Integer.MAX_VALUE);
            double price = Valid.getDouble("Input price: ", "Invalid price!", 0, Double.MAX_VALUE);
            pr.add(new Product(code, name, date, exp, quantity, price));
            System.out.println("Products has been added succesfully !");

            check = Valid.getString("Continue(yes/no)?", "Cannot be blank");

        } while (!check.equalsIgnoreCase("no"));
    }

    public void updateProduct() {
        if (pr.isEmpty()) {
            System.out.println("==================");
            System.out.println("    Empty list!   ");
            System.out.println("==================");
        } else {

            String code = Valid.getString("Input code to update: ", "Code can not be blank!");
            Product x = searchByCode(code);
            if (x == null) {
                System.out.println("Code " + code + " doesn't exist.\n");
            } else {
                System.out.println(x);
                x.setName(Valid.getString("Input new name: ", "Name can not be blank!"));
                x.setDate(Valid.dateFormat("Input new date: ", "Date can not be blank!"));
                x.setExp(Valid.dateFormat("Input new exp: ", "Exp can not be blank!"));
                x.setQuantity(Valid.getInteger("Input new quantity: ", "Quantity must be more than 0", 0, Integer.MAX_VALUE));
                x.setPrice(Valid.getDouble("Input price: ", "Price invalid!", 0, Double.MAX_VALUE));
                System.out.println("The product has been updated successfully!\n");
                System.out.println("================================================================================================================");
                System.out.printf("|%-12s|%-15s|%-25s|%-25s|%-10s|%-10s|\n",
                        "Code", "Name", "Date", "Exp", "Quantity", "Price");
                System.out.println("================================================================================================================");
                System.out.println(x);
            }
        }
    }

    public void deleteProduct() {
        if (pr.isEmpty()) {
            System.out.println("==================");
            System.out.println("    Empty List    ");
            System.out.println("==================");
        } else {
            System.out.println("================================================================================================================");
            System.out.printf("|%-12s|%-15s|%-25s|%-25s|%-10s|%-10s|\n",
                    "Code", "Name", "Date(Ngay san xuat)", "Exp(Han su dung)", "Quantity", "Price");
            System.out.println("================================================================================================================");
            for (Product product : pr) {
                System.out.println(product);
            }
            System.out.println("================================================================================================================");

            String code = Valid.getString("Input code to delete: ", "Code can not be blank");
            Product a = searchByCode(code);
            if (a == null) {
                System.out.println("Product not found!\n");
            } else {
                pr.remove(a);
                System.out.println("Product has deleted!\n");
            }
        }
    }

    public void showAll() {
        if (pr.isEmpty()) {
            System.out.println("==================");
            System.out.println("    Empty list!   ");
            System.out.println("==================");
            return;
        }
        System.out.println("================================================================================================================");
        System.out.printf("|%-12s|%-15s|%-25s|%-25s|%-10s|%-10s|\n",
                "Code", "Name", "Date(Ngay san xuat)", "Exp(Han su dung)", "Quantity", "Price");
        System.out.println("================================================================================================================");
        for (Product a : pr) {
            System.out.println(a);
        }
        System.out.println("================================================================================================================");
    }

    public boolean saveToFile(String File) {
        File fos = new File(File);
        try {
            FileWriter fw = new FileWriter(fos);
            for (Product a : pr) {
                fw.write(a.toString() + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("File Product.txt error !");
            return false;
        }
        return true;
    }

    public void saveToFile() {
        String file = "./product.dat";
        if (saveToFile(file) == true) {
            System.out.println("Save succesfully!\n");
        } else {
            System.out.println("Save failed");
        }
    }

    public void infoExp(Product pr) {
        System.out.println(pr);
    }

}
