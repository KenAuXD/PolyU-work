package hk.edu.polyu.comp.comp2021.cvfs;
import hk.edu.polyu.comp.comp2021.cvfs.model.CVFS;
import hk.edu.polyu.comp.comp2021.cvfs.model.VirtualDisk;
import java.util.Scanner;

public class Application {
    private static CVFS cvfs = new CVFS();
    private static CVFS.Directory directory;
    VirtualDisk virtualDisk = new VirtualDisk();
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("Welcome to the CVFS!");
        System.out.println("Input quit to leave");
        while (running) {
            System.out.println("Input Command:");
            String input = scanner.nextLine();
            String[] commands = input.split(" ");

            if (commands.length == 0) {
                System.out.println("No command entered.");
                continue;
            }

            switch (commands[0]) {
                case "newDisk":
                    if (commands.length > 1) {
                        try {
                            int size = Integer.parseInt(commands[1]);
                            cvfs.newDisk(size);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid size. Please enter a valid integer.");
                        }
                    } else {
                        System.out.println("Usage: newDisk <size>");
                    }
                    break;

                case "newDoc":
                    if (commands.length > 3) {
                        cvfs.newDoc(commands[1], commands[2], commands[3]);
                    } else {
                        System.out.println("Usage: newDoc <name> <type> <content>");
                    }
                    break;

                case "newDir":
                    if (commands.length > 1) {
                        cvfs.newDir(commands[1]);
                    } else {
                        System.out.println("Usage: newDir <name>");
                    }
                    break;

                case "delete":
                    if (commands.length > 1) {
                        cvfs.delete(commands[1]);
                    } else {
                        System.out.println("Usage: delete <name>");
                    }
                    break;

                case "rename":
                    if (commands.length > 2) {
                        cvfs.rename(commands[1], commands[2]);
                    } else {
                        System.out.println("Usage: rename <oldName> <newName>");
                    }
                    break;

                case "changeDir":
                    if (commands.length > 1) {
                        cvfs.changeDir(commands[1]);
                    } else {
                        System.out.println("Usage: changeDir <name>");
                    }
                    break;

                case "list":
                    cvfs.list();
                    break;


                case "rList":
                    cvfs.rList(CVFS.Directory.getParent(), 0);
                    break;

                case "newSimpleCri":
                    if (commands.length > 4) {
                        cvfs.newSimpleCri(commands[1], commands[2], commands[3], commands[4]);
                    } else {
                        System.out.println("Usage: newSimpleCri <name> <attrName> <operation> <value>");
                    }
                    break;

                case "newNegation":
                    cvfs.newNegation(commands[1], commands[2]);
                    break;

                case "newBinaryCri":
                    cvfs.newBinaryCri(commands[1], commands[2], commands[3], commands[4]);
                    break;

                case "printAllCriteria":
                    cvfs.printAllCriteria();
                    break;

                case "search":
                    cvfs.search(commands[1]);
                    break;

                case "rSearch":
                    if (commands.length > 1) {
                        String criName = commands[1];
                        CVFS.Directory currentDir = CVFS.Directory.getParent();
                        int level = 0; // Starting level
                        cvfs.rSearch(criName, currentDir, level);
                    }
                    else {
                        System.out.println("Usage: rSearch <criterionName>");
                    }
                    break;

                case "save":
                    if(commands.length > 1) {
                        VirtualDisk.save(commands[1]);
                    }
                    else {
                        System.out.println("Usage: save <filename>");
                    }
                    break;

                case "load":
                    if(commands.length > 1) {
                        VirtualDisk.load(commands[1]);
                    }
                    else {
                        System.out.println("Usage: load <filename>");
                    }
                    break;

                case "quit":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid Command");
                    break;
            }
        }

        scanner.close();
    }

}