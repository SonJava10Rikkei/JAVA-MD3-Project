package rikkei.academy.config.validate;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.Config;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class ValidateInput {
    public static int validateInt() {
        int choice = 0;
        while (true) {
            try {
                choice = Config.scanner().nextInt();
                return choice;
            } catch (InputMismatchException e) {
                System.out.print("" + ColorConfig.RED + "|     Nhập định dạng phải là số, vui lòng nhập lại:           |" + ColorConfig.RESET + "\n" +
                        "|     ");
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
                System.out.print("" + ColorConfig.RED + "|     Nhập định dạng phải là số, vui lòng nhập lại:           |" + ColorConfig.RESET + "\n" +
                        "|     ");
            }
        }
    }

    public static boolean validateEmail(String email) {
        String pattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.matches(pattern, email);
    }

    public static boolean validateYesOrNo(String yesOrNo) {
        String pattern = "^[yn]+$";
        return Pattern.matches(pattern, yesOrNo);
    }
    public static boolean isNullOrWhiteSpace(String input) {
        return input == null || input.trim().isEmpty();
    }
}
