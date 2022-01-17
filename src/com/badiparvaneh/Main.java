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
import java.util.Scanner;

public class Main {

    public static ArrayList<String> fileNames;

    public static void main(String[] args) {
        //First check if files folder exists from previous execution, and load file names in the array list.
        refreshArrayList();

        //Introduction of the program
        System.out.println("\n");
        System.out.println("******** Welcome to Lockers Pvt. Ltd. File Management System ********");
        System.out.println("\t****** This application is developed by Badi Parvaneh ******\n\n");

        //print main menu to either display current files, or enter the file management system.
        printMainMenu();

        return;
    }

    public static void printMainMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please choose a number from the following menu: ");
        System.out.println("\t 1. Display existing files in ascending order\n "
                     + "\t 2. Go to file management menu");

        int optionSelected = 0;
        try {
            optionSelected = input.nextInt();
            if (optionSelected < 1 || optionSelected > 2) {
                System.out.println("** Invalid selection; please choose either 1 or 2! **\n");
                printMainMenu();
            }
        } catch (Exception e) {
            System.out.println("** Invalid input; please enter a number! **\n");
            printMainMenu();
        }

        switch (optionSelected) {
            case 1:
                if (fileNames == null) {
                    fileNames = new ArrayList<>();
                    System.out.println("There is currently no file saved in the system! Try adding a file from the file management menu!\n");
                    printMainMenu();
                }
                else {
                    Collections.sort(fileNames);
                    for (int i = 0; i < fileNames.size(); i++) {
                        System.out.println("\t" + fileNames.get(i));
                    }
                System.out.println();
                printMainMenu();
                }
                break;
            case 2:
                printFileMenu();
                break;
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
            case 1:
                addFile();
                break;
            case 2:
                deleteFile();
                break;
            case 3:
                searchFile();
                break;
            case 4:
                printMainMenu();
                break;
        }
    }

    public static void addFile () {
        Scanner input = new Scanner(System.in);
        System.out.println(" Please enter a name for your file: ");

        try {
            String fileName = input.nextLine();
            while (fileNames.contains(fileName)) {
                System.out.println("** The file name you are trying to add currently exists, try a different name **");
                fileName = input.nextLine();
            }

            try {
                Path p = Paths.get("./files");
                File folder = new File("files");
                if (!Files.exists(p)) {
                    folder.mkdir();
                }
                try {
                        File newFile = new File(folder, fileName);
                        newFile.createNewFile();
                        fileNames.add(fileName);
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

        return;
    }

    public static void deleteFile () {
        return;
    }

    public static void searchFile () {
        return;
    }

    public static void refreshArrayList (){
        if (fileNames == null) {
            fileNames = new ArrayList<>();
        }
        Path p = Paths.get("./files");
        if (Files.exists(p)) {
            File folder = new File("files");
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    fileNames.add(file.getName());
                }
            }
        }
        return;
    }
}
