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

    public static final String PATH_PRODUCT = "src/rikkei/academy/database/product.txt";


    public List<T> readFormFile(String path) {
        List<T> tList = new ArrayList<>();
//        File file = new File(path);
        try {
//            if(!file.exists()){
//                file.createNewFile();
//            }
            FileInputStream fileInputStream = new FileInputStream(path);
//            if (fileInputStream.available() != 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                tList = (List<T>) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
//            }
        } catch (FileNotFoundException f) {
            System.err.println("File Not Found !");
        } catch (IOException i) {
            System.err.println("IOE Exception !");
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

}
