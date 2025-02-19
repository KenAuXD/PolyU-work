package hk.edu.polyu.comp.comp2021.cvfs.model;

import java.io.*;
import java.util.ArrayList;


public class CVFS {
    public int maxSize;
    public int curSize;
    public Directory root;
    public Directory curDir;
    public ArrayList<Criterion> crit;

    class Document {
        private String name;
        private String type;
        public String content;

        public Document(String name, String type, String content) {
            this.name = name;
            this.type = type;
            this.content = content;
        }

        public int getSize() {
            return 40 + content.length() * 2;
        }

        public String getName() {
            return name;
        }

        public void setName(String newName) {
            this.name = newName;
        }

        public String getType() {
            return type;
        }

    }
    class Criterion {
        private String criName;
        private String attrName;
        private String op;
        private String val;

        public Criterion(String criName, String attrName, String op, String val) {
            this.criName = criName;
            this.attrName = attrName;
            this.op = op;
            this.val = val;
        }

        public String getCriName() {
            return criName;
        }

        public boolean compare(Object files) {
            switch (attrName) {
                case "isDocument":
                    return files instanceof Document;
                case "name":
                    if (files instanceof Document) {
                        Document doc = (Document) files;
                        return op.equals("contains") && doc.getName().contains(val);
                    }
                    else if (files instanceof Directory) {
                        Directory dir = (Directory) files;
                        return op.equals("contains") && dir.getName().contains(val);
                    }
                    break;
                case "type":
                    if (files instanceof Document) {
                        Document doc = (Document) files;
                        return op.equals("equals") && doc.getType().equals(val);
                    }
                    break;
                case "size":
                    int docSize;
                    if (files instanceof Document) {
                        Document doc = (Document) files;
                        docSize = doc.getSize();
                    }
                    else if (files instanceof Directory) {
                        Directory dir = (Directory) files;
                        docSize = dir.getSize();
                    }
                    else {
                        return false;
                    }
                    int value = Integer.parseInt(val);
                    switch (op) {
                        case ">":
                            return docSize > value;
                        case "<":
                            return docSize < value;
                        case ">=":
                            return docSize >= value;
                        case "<=":
                            return docSize <= value;
                        case "==":
                            return docSize == value;
                        case "!=":
                            return docSize != value;
                    }
                    break;
            }
            return false;
        }

        public String getDetail() {
            if ("IsDocument".equals(criName)) {
                return criName + ": IsDocument";
            }
            return criName + ":   " + attrName + " " + op + " " + val;
        }
    }

    class NegationCriterion extends Criterion {
        private Criterion criterion;

        public NegationCriterion(String criName, Criterion criterion) {
            super(criName, "Negation", null, null);
            this.criterion = criterion;
        }

        @Override
        public boolean compare(Object files) {
            return !criterion.compare(files);
        }

        @Override
        public String getDetail() {
            return getCriName() + ":    ~" + criterion.getCriName();
        }

    }

    class BinaryCriterion extends Criterion {
        private Criterion criterion1;
        private Criterion criterion2;
        private String logicOp;

        public BinaryCriterion(String criName, Criterion criterion1, Criterion criterion2, String logicOp) {
            super(criName, "Binary", logicOp, null);
            this.criterion1 = criterion1;
            this.criterion2 = criterion2;
            this.logicOp = logicOp;
        }

        @Override
        public boolean compare(Object files) {
            if ("&&".equals(logicOp)) {
                return criterion1.compare(files) && criterion2.compare(files);
            } else if ("||".equals(logicOp)) {
                return criterion1.compare(files) || criterion2.compare(files);
            }
            return false;
        }

        @Override
        public String getDetail() {
            return getCriName() + ": (" + criterion1.getCriName() + " " + logicOp + " " + criterion2.getCriName()+ ")";
        }
    }


    public CVFS() {
        this.crit = new ArrayList<>();
    }

    public static class Directory {
        private String name;
        private ArrayList<Object> files;
        private static Directory parent;

        public Directory(String name) {
            this.name = name;
            this.files = new ArrayList<>();
            this.parent = null;
        }



        public String getName() {
            return name;
        }

        public int getSize() {
            int size = 40;
            for (Object file : files) {
                if (file instanceof Document) {
                    size += ((Document) file).getSize();
                }
                else if (file instanceof Directory) {
                    size += ((Directory) file).getSize();
                }
            }
            return size;
        }

        public void setName(String newName) {
            this.name = newName;
        }

        public ArrayList<Object> getFiles() {
            return files;
        }

        public static Directory getParent() {
            return parent;
        }

        public void setParent(Directory parent) {
            this.parent = parent;
        }

    }
    public void newDisk(int size) {
        this.crit.clear();
        this.root = new Directory("root");
        this.curDir = root;
        Criterion isDocumentCriterion = new Criterion("IsDocument", "isDocument", "equals", "document");
        crit.add(isDocumentCriterion);
        this.maxSize = size;
        this.curSize = 0;
        this.root = new Directory("root");
        this.curDir = root;
        System.out.println("Disk created with size: " + size);
    }

    public void newDoc(String docName, String docType, String docContent) {
        Document doc = new Document(docName, docType, docContent);
        if (fileExists(docName)) {
            System.out.println("Name already exists.");
        } else if (!(docName.length() <= 10 && docName.matches("[a-zA-Z0-9]+") &&
                (docType.equals("txt") || docType.equals("java") || docType.equals("html") || docType.equals("css")))) {
            System.out.println("Invalid document name or type.");
        } else if (curSize + doc.getSize() > maxSize) {
            System.out.println("Disk full");
        } else {
            curDir.getFiles().add(doc);
            curSize += doc.getSize();
            System.out.println("Document created: " + docName + "." + docType);
        }
    }

    public void newDir(String dirName) {
        Directory dir = new Directory(dirName);

        if (fileExists(dirName)) {
            System.out.println("Name already exists.");
        } else if (!(dirName.matches("[a-zA-Z0-9]+"))) {
            System.out.println("Invalid directory name.");
        } else if (curSize + 40 > maxSize) {
            System.out.println("Disk full.");
        } else {
            dir.setParent(curDir);
            curDir.getFiles().add(dir);
            curSize += 40;
            System.out.println("Directory created: " + dirName);
        }
    }

    private boolean fileExists(String name) {
        for (Object file : curDir.getFiles()) {
            if ((file instanceof Document && ((Document) file).getName().equals(name)) ||
                    (file instanceof Directory && ((Directory) file).getName().equals(name))) {
                return true;
            }
        }
        return false;
    }

    private Object getFile(String name) {
        for (Object file : curDir.getFiles()) {
            if ((file instanceof Document && ((Document) file).getName().equals(name)) ||
                    (file instanceof Directory && ((Directory) file).getName().equals(name))) {
                return file;
            }
        }
        return null;
    }


    public void delete(String fileName) {
        if (fileExists(fileName)) {
            Object file = getFile(fileName);
            curDir.getFiles().remove(file);
            if (file instanceof Document) {
                curSize -= ((Document) file).getSize();
            } else if (file instanceof Directory) {
                curSize -= ((Directory) file).getSize();
            }
            System.out.println("File deleted.");
        } else {
            System.out.println("File not found.");
        }
    }

    public void rename(String oldFileName, String newFileName) {
        if (fileExists(oldFileName)) {
            if (fileExists(newFileName)) {
                System.out.println("New name already exists");
                return;
            }
            Object file = getFile(oldFileName);
            if (file instanceof Document) {
                ((Document) file).setName(newFileName);
            } else if (file instanceof Directory) {
                ((Directory) file).setName(newFileName);
            }
            System.out.println("File renamed from " + oldFileName + " to " + newFileName);
        } else {
            System.out.println("File not found");
        }
    }

    public void changeDir(String dirName) {
        if (dirName.equals("..")) {
            if (curDir == root) {
                System.out.println("Already at root directory.");
            } else {
                curDir = curDir.getParent();
                System.out.println("Moved to parent directory.");
            }
        } else {
            Object targetDir = getFile(dirName);
            if (targetDir == null) {
                System.out.println("Directory not found ");
            } else if (!(targetDir instanceof Directory)) {
                System.out.println("This is not a directory");
            } else {
                curDir = (Directory) targetDir;
                System.out.println("Changed directory to: " + dirName);
            }
        }
    }

    public void list() {
        int totalSize = 0;
        int totalFiles = 0;

        for (Object file : curDir.getFiles()) {
            if (file instanceof Document) {
                Document doc = (Document) file;
                System.out.println("Document: " + doc.getName() + "." + doc.getType() + ", Size: " + doc.getSize() + " bytes");
                totalSize += doc.getSize();
                totalFiles++;
            } else if (file instanceof Directory) {
                Directory dir = (Directory) file;
                System.out.println("Directory: " + dir.getName() + ", Size: " + dir.getSize());
                totalSize += dir.getSize();
                totalFiles++;
            }
        }
        System.out.println("Total files: " + totalFiles + ", Total size: " + totalSize);
    }

    public void rList(Directory directory, int level) {
        int totalSize = 0;
        int totalFiles = 0;

        for (Object file : directory.getFiles()) {
            if (file instanceof Document) {
                Document doc = (Document) file;
                System.out.println("    ".repeat(level) + "Document: " + doc.getName() + "." + doc.getType() + ", Size: " + doc.getSize() + " bytes");
                totalSize += doc.getSize();
                totalFiles++;
            } else if (file instanceof Directory) {
                Directory dir = (Directory) file;
                System.out.println("    ".repeat(level) + "Directory: " + dir.getName() + ", Size: " + dir.getSize());
                totalSize += dir.getSize();
                totalFiles++;
                rList(dir,level + 1);
            }
        }
        if (level == 0) {
            System.out.println("Total files: " + totalFiles + ", Total size: " + totalSize);
        }
    }


    public void newSimpleCri(String criName, String attrName, String op, String val) {
        if (attrName.equals("size")) {
            try {
                Integer.parseInt(val);
            } catch (NumberFormatException e) {
                System.out.println("Value must be an integer.");
            }
        }
        if (!criName.matches("[a-zA-Z]{2}")) {
            System.out.println("Invalid name.");
        }
        else if (!(attrName.equals("name") || (attrName.equals("type") || (attrName.equals("size"))))) {
            System.out.println("Invalid attribute name.");
        }
        else if ((attrName.equals("name") && !op.equals("contains")) ||
                (attrName.equals("type") && !op.equals("equals")) ||
                (attrName.equals("size") && !op.matches("[<>=!]{1,2}"))) {
            System.out.println("Invalid operation for attribute.");
        }
        else {
            Criterion criterion = new Criterion(criName, attrName, op, val);
            crit.add(criterion);
            System.out.println("Criterion created: " + criName);
        }
    }
    public void newNegation(String criName1, String criName2) {
        Criterion criterion = getCriterion(criName2);
        if (criterion == null) {
            System.out.println("Criterion not found: " + criName2);
        }
        else {
            NegationCriterion negationCriterion = new NegationCriterion(criName1, criterion);
            crit.add(negationCriterion);
            System.out.println("Negation criterion created: " + criName1);
        }
    }

    public void newBinaryCri(String criName1, String criName3, String logicOp, String criName4) {
        Criterion criterion1 = getCriterion(criName3);
        Criterion criterion2 = getCriterion(criName4);
        if (criterion1 == null || criterion2 == null) {
            System.out.println("One or more criteria not found.");
            return;
        }
        else if(!(logicOp.equals("&&") || logicOp.equals("||"))) {
            System.out.println("Logical operator found.");
        }
        BinaryCriterion binaryCriterion = new BinaryCriterion(criName1, criterion1, criterion2, logicOp);
        crit.add(binaryCriterion);
        System.out.println("Binary criterion created: " + criName1);
    }

    public void printAllCriteria() {
        //System.out.println("Printing all criteria...");
        for (Criterion criterion : crit) {
            System.out.println(criterion.getDetail());
        }
    }

    private Criterion getCriterion(String criName) {
        for (Criterion criterion : crit) {
            if (criterion.getCriName().equals(criName)) { // Fixed comparison
                return criterion;
            }
        }
        return null;
    }

    public void search(String criName) {
        Criterion criterion = getCriterion(criName);
        if (criterion == null) {
            System.out.println("Criterion not found: " + criName);
        } else {
            int totalSize = 0;
            int totalFiles = 0;

            for (Object file : curDir.getFiles()) {
                if (criterion.compare(file)) {
                    if (file instanceof Document) {
                        Document doc = (Document) file;
                        System.out.println("File: " + doc.getName() + ", Size: " + doc.getSize() + " bytes");
                        totalSize += doc.getSize();
                        totalFiles++;
                    } else if (file instanceof Directory) {
                        Directory dir = (Directory) file;
                        System.out.println("Directory: " + dir.getName() + ", Size: " + dir.getSize() + " bytes");
                        totalSize += dir.getSize();
                        totalFiles++;
                    }
                }
            }
            System.out.println("Total files: " + totalFiles + ", Total size: " + totalSize + " bytes");
        }
    }

    public void rSearch(String criName,Directory Directory, int level) {
        Criterion criterion = getCriterion(criName);
        if (criterion == null) {
            System.out.println("Criterion not found: " + criName);
        }
        else {
            int totalSize = 0;
            int totalfile = 0;

            for (Object file : Directory.getFiles()) {
                if (criterion.compare(file)) {
                    if (file instanceof Document) {
                        String name = ((Document) file).getName();
                        int size = ((Document) file).getSize();
                        System.out.println("    ".repeat(level) + "File: " + name + ", Size: " + size);
                        totalSize += size;
                        totalfile++;
                    }
                    else if(file instanceof Directory) {
                        String name = ((Directory) file).getName();
                        int size = ((Directory) file).getSize();
                        System.out.println("    ".repeat(level) + "File: " + name + ", Size: " + size);
                        totalSize += size;
                        totalfile++;
                        rSearch(criName, (Directory)file,level + 1);
                    }
                }
            }
            System.out.println("Total files: " + totalfile + ", Total size: " + totalSize);
        }
    }
    public void save(String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(root);
            System.out.println("Virtual disk saved successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving virtual disk: " + e.getMessage());
        }
    }


    public void load(String filePath) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            root = (Directory) in.readObject();
            System.out.println("Virtual disk loaded successfully from " + filePath);
        } catch (IOException e) {
            System.err.println("Error loading virtual disk: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        }
    }
}