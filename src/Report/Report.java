package Report;

import java.util.*;
import product.DataProduct;
import product.Product;

public class Report {

    Product a = new Product();
    DataProduct dp = new DataProduct();

    public void ExpiredProduct(ArrayList<Product> lp) {
        Date dateNow = new Date();
        for (Product pr : lp) {
            if (pr.getExp().compareTo(dateNow) < 0) {
                dp.infoExp(pr);
            }
        }
    }

    public void productAreSelling(List<Product> lp) {
        Date dateNow = new Date();
        System.out.println("                                                 PRODUCT ARE SELLING                                            ");
        System.out.println("================================================================================================================");
        System.out.printf("|%-12s|%-16s|%-28s|%-29s|%-10s|%-10s|\n",
                "Code", "Name", "Date", "Exp", "Quantity", "Price");
        System.out.println("================================================================================================================");
        for (Product pr : lp) {
            if (pr.getQuantity() > 0 && pr.getExp().compareTo(dateNow) > 0) {
                System.out.println(pr);
            }
        }
        System.out.println("================================================================================================================");
    }

    public void outOfStock(List<Product> lp) {
        ArrayList<Product> stock = new ArrayList<>();
        for (Product pr : lp) {
            if (pr.getQuantity() < 3) {
                stock.add(pr);
            }

        }
        Comparator<Product> c = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Integer.compare(o1.getQuantity(), o2.getQuantity());
            }
        };
        Collections.sort(stock, c);
        for (Product pro : stock) {
            dp.infoExp(pro);
        }
    }

    public Product searchByCode(String code, List<Product> lp) {
        if (lp.isEmpty()) {
            return null;
        }
        for (int i = 0; i < lp.size(); i++) {
            if (lp.get(i).getCode().equalsIgnoreCase(code)) {
                return lp.get(i);
            }
        }
        return null;
    }

}
