package hk.edu.polyu.comp.comp2021.cvfs.model;

import java.io.*;

public class VirtualDisk implements Serializable {
    private static final long serialVersionUID = 1L;
    private CVFS cvfs;
    private static Directory root;

    public VirtualDisk() {
        this.cvfs = new CVFS(); // Initialize CVFS
        this.root = new Directory("root");
    }


    public static void save(String filename) {
        if (filename.isEmpty() || !filename.contains(".")) {
            System.out.println("Invalid filename. Please provide a name that includes an extension (e.g., .txt, .bin).");
            return;
        }

        try (OutputStream outputStream = new FileOutputStream(filename);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(root);
            objectOutputStream.flush();
            System.out.println("Stored the current virtual disk successfully in " + filename);
        } catch (IOException e) {
            System.err.println("Error storing the virtual disk: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static VirtualDisk load(String filename) {
        File file = new File(filename);

        if (!file.exists() || !file.isFile()) {
            System.err.println("Error: The file '" + filename + "' does not exist or is not a valid file.");
            return null;
        }

        try (InputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            VirtualDisk loadedDisk = (VirtualDisk) objectInputStream.readObject();
            System.out.println("Loaded the virtual disk successfully from " + filename);
            return loadedDisk;
        } catch (IOException e) {
            System.err.println("Error loading the virtual disk from '" + filename + "': " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Class not found during loading from '" + filename + "': " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}