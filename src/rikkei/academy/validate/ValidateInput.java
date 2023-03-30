package rikkei.academy.validate;

import rikkei.academy.config.Config;

import java.util.InputMismatchException;

public class ValidateInput {
    public static int validateInt() {
        int choice = 0;
        while (true) {
            try {
                choice = Config.scanner().nextInt();
                return choice;
            } catch (InputMismatchException e) {
                System.err.println("|     Nhập định dạng phải là số, vui lòng nhập lại:           |");
                System.out.print("|     ");
            }
        }
    }

    public static double validateDouble() {
        double choice = 0;
        while (true) {
            try {
                choice = Config.scanner().nextDouble();
                return choice;
            } catch (InputMismatchException e) {
                System.err.println("Nhập định dạng phải là số, vui lòng nhập lại: ");
            }
        }
    }
}
