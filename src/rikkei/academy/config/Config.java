package rikkei.academy.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Config<T> {
    public static Scanner scanner() {
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }


    public List<T> readFormFile(String pathFile) {
        List<T> tList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(pathFile);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                tList = (List<T>) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
        } catch (FileNotFoundException f) {
            System.err.println("File Not Found !");
        } catch (IOException i) {
            System.err.println("IOE Exception (Danh sách không có dữ liệu)!");
        } catch (ClassNotFoundException c) {
            System.err.println("Class Not Found !");
        }
        return tList;
    }

    public void writeToFile(String pathFile, List<T> tList) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tList);
            fileOutputStream.close();
            objectOutputStream.close();

        } catch (FileNotFoundException f) {
            System.err.println("File Not Found !");
        } catch (IOException i) {
            System.err.println("IOE Exception !");
        }
    }

    /**
     * <p><b><u}>Description detail</u></b> </p>
     * <p>getString()       ==>> Return a string value from the user.</p>
     * <p>getChar()         ==>> Return a character value from the user</p>
     * <p>getBoolean()	    ==>> Return a boolean value from the user.</p>
     * <p>getByte()	        ==>> Return a byte value from the user.</p>
     * <p>getShort()	    ==>> Return a short value from the user.</p>
     * <p>getInteger()	    ==>> Return a integer value from the user.</p>
     * <p>getLong()	        ==>> Return a long value from the user.</p>
     * <p>getFloat()	    ==>> Return a float value from the user.</p>
     * <p>getDouble()	    ==>> Return a double value from the user.</p>
     */
    public static final class InputConfig {
        private static final String ERROR_ALERT = "===>> Number format invalid or Out of Range! Please try again...";
        private static final String EMPTY_ALERT = "===>> Please do not empty this field! Please try again...";
        private static final String CONTINUE = "===>> ...Press any key to continue... <<===";
        private static final String BLUE_ITALIC = "\033[3;34m";   // BLUE
        private static final String RESET = "\033[0m";  // Text Reset
        /*========================================Input Method Start========================================*/

        /**
         * getString()  Return a String value from the user.
         */
        public static String getString() {
            String result = getInput().trim();
            if (result.equals("")) {
    //            System.err.println(EMPTY_ALERT);
                System.out.print("|     " + ColorConfig.RED + "Chuỗi nhập không được bỏ trống! Hãy nhập lại:" + ColorConfig.RESET + "           |\n" +
                        "|     ");
                return getString();
            }
            return result;
        }

        /**
         * getChar()  Return a Character value from the user.
         */
        public static char getChar() {
            return getString().charAt(0);
        }

        /**
         * getBoolean()  Return a Boolean value from the user.
         */
        public static boolean getBoolean() {
            String result = getString();
            return result.equalsIgnoreCase("true");
        }

        /**
         * getByte()  Return a Byte value from the user.
         */
        public static byte getByte() {
            try {
                return Byte.parseByte(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
                return getByte();
            }
        }

        /**
         * getShort()  Return a Short value from the user.
         */
        public static short getShort() {
            try {
                return Short.parseShort(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
                return getShort();
            }
        }

        /**
         * getInteger()  Return a Integer value from the user.
         */
        public static int getInteger() {
            try {
                return Integer.parseInt(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
                return getInteger();
            }
        }

        /**
         * getLong()  Return a Long value from the user.
         */
        public static long getLong() {
            try {
                return Long.parseLong(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
                return getLong();
            }
        }

        /**
         * getFloat()  Return a Float value from the user.
         */
        public static float getFloat() {
            try {
                return Float.parseFloat(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
                return getFloat();
            }
        }

        /**
         * getDouble()  Return a Double value from the user.
         */
        public static double getDouble() {
            try {
                return Double.parseDouble(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
                return getDouble();
            }
        }
        /*========================================Input Method End========================================*/

        /**
         * getInput()  Return any String value from the user.
         */
        private static String getInput() {
            return new Scanner(System.in).nextLine();
        }

        /**
         * pressAnyKey()  Press any key to continue....
         */
        public static void pressAnyKey() {
            System.out.println(BLUE_ITALIC + CONTINUE + RESET);
            getInput();
        }
        /*========================================Other Method========================================*/
    }
}
