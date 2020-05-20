package cn.edu.njupt.clock.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectStreamUtils {
    public static <T> void outputObjectList(Class<T> clazz, List<T> list) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(clazz.getCanonicalName() + ".dat"));
            oos.writeObject(list);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static <T> List<T> inputObjectList(Class<T> clazz) {
        ObjectInputStream ois = null;
        List<T> list = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(clazz.getCanonicalName() + ".dat"));
            list = (List<T>)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
