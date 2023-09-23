/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import product.Product;

/**
 *
 * @author asus
 */
public class Valid {

    private static Scanner sc = new Scanner(System.in);
    public static SimpleDateFormat dF = new SimpleDateFormat("dd/MM/yyyy");

    //check Code
    public static String getString(String inputMsg, String errorMsg) {
        String s;
        while (true) {
            System.out.print(inputMsg);
            s = sc.nextLine();
            if (s.length() == 0 || s.isEmpty()) {
                System.out.println(errorMsg);
            } else {
                return s;
            }
        }
    }

    //check Date/Exp
    public static String getFormat(String inputdate, String error, String format) {
        String s;
        boolean date;
        while (true) {
            System.out.print(inputdate);
            s = sc.nextLine();
            date = s.matches(s);
            if (s.length() == 0 || s.isEmpty() || date == false) {
                System.out.println(error);
            } else {
                return s;
            }
        }
    }

    public static int getInteger(String msg, String error, int lower, int up) {
        int n;
        while (true) {
            try {
                System.out.print(msg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lower || n > up) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(error);
            }

        }
    }

    public static double getDouble(String inputMsg, String errorMsg, double lowerBound, double upBound) {
        double a, b;
        if (lowerBound > upBound) {
            a = lowerBound;
            lowerBound = upBound;
            upBound = a;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                b = Double.parseDouble(sc.nextLine());
                if (b > 0) {
                    return b;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static Date dateFormat(String msg, String error) {
        while (true) {
            try {
                System.out.print(msg);
                String dateString = sc.nextLine().trim();
                String[] datePart = dateString.split("/");

                if (datePart.length != 3) {
                    throw new Exception();
                }
                int day = Integer.parseInt(datePart[0]);
                int month = Integer.parseInt(datePart[1]);
                int year = Integer.parseInt(datePart[2]);

                if (isValidDate(day, month, year)) {
                    Date date = dF.parse(dateString);
                    return date;
                } else {
                    System.out.println("Invalid day or month.");
                }
            } catch (Exception e) {
                System.err.println(error);
            }

        }
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12) {
            return false;
        }
        return !(day < 1 || day > getMonth(month, year));
    }

    private static int getMonth(int month, int year) {
        int m[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (getYear(year) && getYear(year)) {
            return 29;
        }
        return m[month];
    }

    private static boolean getYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
    }

    private static boolean checkDuplicate(ArrayList<Product> p, String id) {
        for (Product pr : p) {
            if (pr.getCode().equalsIgnoreCase(id)) {
                return false;
            }
        }
        return true;
    }

    public static String checkDuplicate(String msg, String error, ArrayList<Product> p) {
        while (true) {
            String productID = getString(msg, error);

            if (checkDuplicate(p, productID) == false) {
                System.out.println("Duplicated Code. Please input another one!");
                continue;
            }
            return productID;
        }
    }
}
