package utils;

import java.io.*;

/**
 * Assists in serializing objects.
 */
public class SerializerHelper {
    /**
     * Serializes object to a filename.dat file.
     * @param object to be serialized.
     * @param filename to save the object as.
     */
    public static void serializeObject(Object object, String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
            out.close();
            fileOut.close();
            // System.out.println("Serialised object stored as " + filename);
        }
        catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * To deserialize an object from filename.dat.
     * @param filename of object to be deserialized.
     * @return deserialized object.
     */
    public static Object deSerializeObject(String filename) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            bufferedInputStream.close();
            fileInputStream.close();
            // System.out.println("Object has been read in!");
            return object;
        }
        catch (FileNotFoundException f) {
            System.out.println("FileNotFoundException caught!");
            f.printStackTrace();
            return null;
        }
        catch (IOException i)
        {
            System.out.println("IOException caught!");
            i.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found!");
            c.printStackTrace();
            return null;
        }
    }
}
