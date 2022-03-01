package Util;

import java.util.Scanner;

public class IO {
    public static int inputInt() {
        Scanner reader = new Scanner(System.in);

        int choice = 0;
        String option = "";

        try {
            option = reader.nextLine();
            choice = Integer.parseInt(option);

        } catch (NumberFormatException e) {

            throw new NumberFormatException(String.format("The input : \"" +
                    " %s \" is not a number !!", option));
        }
        return choice;
    }

}
