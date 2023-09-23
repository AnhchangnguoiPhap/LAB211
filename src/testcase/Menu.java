package testcase;

import java.util.ArrayList;

public class Menu {

    private ArrayList<String> Options = new ArrayList<>();

    public Menu() {
    }

    public void addOptions(String newOptions) {
        Options.add(newOptions);
    }

    public void print() {
        for (String x : Options) {
            System.out.println(x);
        }
    }

    public int Choice() {
        int maxOption = Options.size();
        String inputMsg = "Enter your choice [1-" + maxOption + "]: ";
        String errorMsg = "Please enter your choice from 1 to " + maxOption + "!";
        return Valid.getInteger(inputMsg, errorMsg, 1, maxOption);
    }

}
