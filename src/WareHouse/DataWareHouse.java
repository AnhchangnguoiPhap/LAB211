package WareHouse;

import product.Product;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static product.DataProduct.pr;
import testcase.Valid;

public class DataWareHouse extends ArrayList<Warehouse> {

    private boolean saved = false;

    public DataWareHouse() {
        super();
    }

    public int searchCode1(String code) {
        if (this.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCode().equalsIgnoreCase(code)) {
                return i;
            }
        }
        return -1;
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

    public void exportReceipt() {
        String check;
        String choose;
        do {
            String code = Valid.checkDuplicate("Input code: ", "Duplicated code! Please input again", pr);

            String name = Valid.getString("Input name of product: ", "Name cannot be blank!.");

            Date date = Valid.dateFormat("Input date (dd/MM/yyyy): ", "Invalid date form");
            Date exp = Valid.dateFormat("Input exp (dd/MM/yyyy): ", "Invalid date");

            int quantity = Valid.getInteger("Input quantity: ", "Quantity must be more than 0", 0, Integer.MAX_VALUE);

            double price = Valid.getDouble("Input price: ", "Invalid price!", 0, Double.MAX_VALUE);
            Warehouse y = new Warehouse(code, name, date, exp, quantity, price);
            this.add(y);

            System.out.println("Products has been added succesfully !");
            choose = Valid.getString("Do you want to print the import receipt[yes, no] ?: ", "Please enter your choice!");
            if (choose.equalsIgnoreCase("yes")) {
                System.out.println("                                       EXPORT RECEIPT                                                          ");
                System.out.println("================================================================================================================");
                System.out.printf("|%-12s|%-15s|%-28s|%-28s|%-10s|%-10s|\n",
                        "Code", "Name", "Date(Ngay san xuat)", "Exp(Han su dung)", "Quantity", "Price");
                System.out.println("================================================================================================================");
                for (Warehouse b : this) {
                    System.out.println(b);
                }
                System.out.println("================================================================================================================");
            }
            check = Valid.getString("Add new Product ?(yes/no)?", "Cannot be blank");
        } while (!check.equalsIgnoreCase("no"));
    }


    public static void importReceipt() {
        ArrayList<Product> pr = new ArrayList<>();
        boolean add = true;

        while (add) {
            System.out.println("Add a new product to the export receipt");
            Product product = getProductDetails();

            if (isDuplicateProduct(product)) {
                System.out.println("The product already exists in the export receipt.");
            }else {
                pr.add(product);
                System.out.println("Product added successfully to the export receipt.");
            }
            String continueAdding = Valid.getString("Add new Product ?(yes/no)?", "Cannot be blank");
            add = continueAdding.equalsIgnoreCase("yes");
        }

        System.out.println("===================================Import Receipt============================");
        if (pr.size() > 0) {
            for (Product product : pr) {
                System.out.println(product.toString());
            }
            System.out.println("Export receipt created successfully.");
        } else {
            System.out.println("No products added to the export receipt.");
        }
    }
    
    private static Product getProductDetails() {
        String code = Valid.checkDuplicate("Input code: ", "Duplicated code! Please input again", pr);

        String name = Valid.getString("Input name of product: ", "Name cannot be blank!.");

        Date date = Valid.dateFormat("Input date (dd/MM/yyyy): ", "Invalid date form");
        Date exp = Valid.dateFormat("Input exp (dd/MM/yyyy): ", "Invalid date");

        int quantity = Valid.getInteger("Input quantity: ", "Quantity must be more than 0", 0, Integer.MAX_VALUE);

        double price = Valid.getDouble("Input price: ", "Invalid price!", 0, Double.MAX_VALUE);

        return new Product(code, name, date, exp, quantity, price);
    }

    private static boolean isDuplicateProduct(Product newProduct) {
        for (Product product : pr) {
            if (product.getCode().equals(newProduct.getCode())) {
                return true;
            }
        }
        return false;
    }

    public boolean saveToFile(String File) {
        File fos = new File(File);
        try {
            FileWriter fw = new FileWriter(fos);
            for (Warehouse b : this) {
                fw.write(b.toString() + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("File Product.txt error !");
            return false;
        }
        return true;
    }

    public void saveToFile() {
        String file = "./warehouse.dat";
        if (saveToFile(file) == true) {
            System.out.println("Save succesfully!\n");
        } else {
            System.out.println("Save failed");
        }
    }

}
