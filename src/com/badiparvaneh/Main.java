/*
    This is a Java application that allows users manage files. Functions include adding a new file,
    searching for an existing file, deleting a file, and navigating back to the main menu.

    Developed by Badi Parvaneh. All rights reserved.
*/

package com.badiparvaneh;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static ArrayList<String> sortedFileNames = new ArrayList<>();
    public static HashSet<String> fileNames;
    public static void main(String[] args) {
        //First check if files folder exists from previous execution, and load file names in the array list.
        refreshHashSet();

        //Introduction of the program
        System.out.println("\n");
        System.out.println("******** Welcome to Lockers Pvt. Ltd. File Management System ********");
        System.out.println("\t****** This application is developed by Badi Parvaneh ******\n\n");

        //print main menu to either display current files, or enter the file management system.
        printMainMenu();

    }

    public static void printMainMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please choose a number from the following menu: ");
        System.out.println("\t 1. Display existing files in ascending order\n "
                     + "\t 2. Go to file management menu\n "
                     + "\t 3. Exit the program");

        int optionSelected = 0;
        try {
            optionSelected = input.nextInt();
            if (optionSelected < 1 || optionSelected > 3) {
                System.out.println("** Invalid selection; please choose 1, 2, or 3! **\n");
                printMainMenu();
            }
        } catch (Exception e) {
            System.out.println("** Invalid input; please enter a number! **\n");
            printMainMenu();
        }

        switch (optionSelected) {
            case 1:
                if (sortedFileNames == null || sortedFileNames.size() == 0) {
                    //sortedFileNames = new ArrayList<>();
                    System.out.println("** There is currently no file saved in the system! Try adding a file from the file management menu! **\n");
                    printMainMenu();
                }
                else {
                    System.out.println("\n** Here is the list of existing files in the directory **");
                    for (String sortedFileName : sortedFileNames) {
                        System.out.println("\t" + sortedFileName);
                    }
                System.out.println();
                printMainMenu();
                }
                break;
            case 2:
                printFileMenu();
                break;
            case 3:
                System.out.println("*** Thanks for using Lockers Pvt. Ltd. File Management System ***");
                System.out.println("\t\t******** Now exiting the program ********");
        }
    }

     public static void printFileMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please choose an option: ");
        System.out.println("\t 1. Add a new file to the directory\n "
                     + "\t 2. Delete a file from the directory\n "
                     + "\t 3. Search for a file in the directory\n "
                     + "\t 4. Go back to main menu\n");
        int optionSelected = 0;

        try {
            optionSelected = input.nextInt();
        } catch (Exception e) {
            System.out.println("** Invalid input; please enter a number! **\n");
            printFileMenu();
        }

         switch (optionSelected) {
             case 1 -> addFile();
             case 2 -> deleteFile();
             case 3 -> searchFile();
             case 4 -> printMainMenu();
         }
    }

    public static void addFile () {
        Scanner input = new Scanner(System.in);
        System.out.println(" Please enter a name for your file: ");

        try {
            String fileName = input.nextLine().toLowerCase();
            while (fileNames.contains(fileName)) {
                System.out.println("** The file name you are trying to add currently exists, try a different name or type 'back' to return to the menu **");
                fileName = input.nextLine().toLowerCase();
                if (fileName.equals("back")) {
                    printFileMenu();
                }
            }

            try {
                Path p = Paths.get("./files");
                File folder = new File("files");
                if (!Files.exists(p)) {
                    folder.mkdir();
                }
                try {
                        fileName.toLowerCase();
                        File newFile = new File(folder, fileName);
                        newFile.createNewFile();
                        fileNames.add(fileName);
                        sortedFileNames.add(fileName);
                        Collections.sort(sortedFileNames);
                    } catch (Exception e) {
                        System.out.println(" ** Error: file could not be added; try again! **\n");
                        printFileMenu();
                    }

                } catch (Exception e) {
                    System.out.println("** Incorrect file path; try again! **\n");
                }
            System.out.println(" ** File: " + fileName + " was successfully added to the directory!");
            printFileMenu();
        } catch (Exception e) {
            System.out.println(" ** ERROR: File could not be added! Try again!");
            printFileMenu();
        }

    }

    public static void deleteFile () {
        Scanner input = new Scanner(System.in);
        System.out.println(" Please enter the name of the file you would like to delete: ");

        String fileName = input.nextLine().toLowerCase();
        while (!fileNames.contains(fileName)) {
            System.out.println("** The file name you are trying to delete doesn't exists, try a different name or 'back' to return to the menu**");
            fileName = input.nextLine().toLowerCase();
            if (fileName.equals("back")) {
                printFileMenu();
                return;
            }
        }

        try {
            Path p = Paths.get("./files");
            File folder = new File("files");
            if (!Files.exists(p)) {
                System.out.println("** There are currently no files! **\n");
                printFileMenu();
            }
            try {
                    File file = new File(folder, fileName);
                    System.out.println("** Are you sure you'd like to delete " + fileName + "? (Y/N)");
                    Scanner safetyInput = new Scanner(System.in);
                    String check = safetyInput.nextLine().toLowerCase();

                    while (!check.equals("y") && !check.equals("n")  && !check.equals("back")) {
                        System.out.println("** Invalid selection; type Y or N to confirm file deletion, or type 'back' to return to the menu **\n");
                        check = safetyInput.nextLine().toLowerCase();
                    }
                    if (check.equals("y")) {
                        if (file.delete()) {
                            fileNames.remove(fileName);
                            sortedFileNames.remove(fileName);
                            System.out.println("** File: " + fileName + " was successfully deleted! **");
                            printFileMenu();
                        }
                    } else if (check.equals("n")) {
                        printFileMenu();
                    }
                } catch (Exception e) {
                    System.out.println(" ** Error: file could not be deleted; try again! **\n");
                    printFileMenu();
                }

            } catch (Exception e) {
                System.out.println("** Incorrect file path; try again! **\n");
            }

    }

    public static void searchFile () {
        Scanner input = new Scanner(System.in);
        System.out.println(" Please enter the name of the file you would like to search: ");

        String fileName = input.nextLine().toLowerCase();
        while (!fileNames.contains(fileName)) {
            System.out.println("** The file name you are searching for doesn't exists, try a different name or 'back' to return to the menu**");
            fileName = input.nextLine().toLowerCase();
            if (fileName.equals("back")) {
                printFileMenu();
                return;
            }
        }

        System.out.println("** File: " + fileName + " was found in the directory under ./files/" + fileName + " **\n");
        printFileMenu();
    }

    public static void refreshHashSet (){
        if (fileNames == null) {
            fileNames = new HashSet<>();
        }
        Path p = Paths.get("./files");
        if (Files.exists(p)) {
            File folder = new File("files");
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    fileNames.add(file.getName());
                    sortedFileNames.add(file.getName());
                }
                Collections.sort(sortedFileNames);
            }
        }
    }
}
