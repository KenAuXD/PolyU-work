package hk.edu.polyu.comp.comp2021.cvfs.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Directory implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Directory parent;
    private ArrayList<Object> files;

    public Directory(String name) {
        this.name = name;
        this.files = new ArrayList<>();
        this.parent = null; // Initially, the parent is null
    }

    public String getName() {
        return name;
    }

    // Method to set the directory name
    public void setName(String name) {
        this.name = name;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public Directory getParent() {
        return parent;
    }

    public ArrayList<Object> getFiles() {
        return files;
    }

    public int getSize() {
        int size = 40; // Base size for directory
        for (Object file : files) {
            if (file instanceof CVFS.Document) {
                size += ((CVFS.Document) file).getSize();
            } else if (file instanceof Directory) {
                size += ((Directory) file).getSize();
            }
        }
        return size;
    }

    public void addFile(Object file) {
        files.add(file);
        if (file instanceof Directory) {
            ((Directory) file).setParent(this);
        }
    }

    public void removeFile(Object file) {
        files.remove(file);
    }

    public Object findFile(String name) {
        for (Object file : files) {
            if (file instanceof CVFS.Document && ((CVFS.Document) file).getName().equals(name)) {
                return file; // Found the document
            } else if (file instanceof Directory && ((Directory) file).getName().equals(name)) {
                return file; // Found the directory
            }
        }
        return null; // Not found
    }
}