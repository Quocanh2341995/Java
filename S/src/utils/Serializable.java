package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializable {
    public static Object deserialize(String fileName) {
        Object obj = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            var ois = new ObjectInputStream(fis);
            obj = ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("Errors File: " + fileName);
        }
        return obj;
    }

    public static void serialize(Object obj, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            fos.close();
        } catch (Exception e) {
            System.out.println("Errors File: " + e.getMessage());
        }
    }
}
