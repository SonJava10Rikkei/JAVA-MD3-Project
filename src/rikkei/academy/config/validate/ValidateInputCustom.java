package rikkei.academy.config.validate;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.Config;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidateInputCustom {
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


    public static boolean validateName(String name) {
        String pattern = "^\\S.{0,38}\\S$";
        return Pattern.matches(pattern, name);
    }

    public static String validateUserName = "^(?=\\S+$).{1,30}$";

    public static boolean validateEmail(String email) {
        String pattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.matches(pattern, email);
    }

    public static String validatePassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,10}$";

    public static boolean isNullOrWhiteSpace(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static String getString() {
        String result = getInput().trim();
        if (result.equals("")) {
            System.out.print("|     " + ColorConfig.RED + "Chuỗi nhập không được bỏ trống! Hãy nhập lại:" + ColorConfig.RESET + "           |\n" +
                    "|     ");
            return getString();
        }
        return result;
    }

    private static String getInput() {
        return new Scanner(System.in).nextLine();
    }

}
