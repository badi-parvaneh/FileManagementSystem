/*
    This is a Java application that allows users manage files. Functions include adding a new file,
    searching for an existing file, deleting a file, and navigating back to the main menu.

    Developed by Badi Parvaneh. All rights reserved.
*/

package com.badiparvaneh;
import java.io.File;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	System.out.println("\n");
    System.out.println("******** Welcome to Lockers Pvt. Ltd. File Management System ********");
    System.out.println("\t****** This application is developed by Badi Parvaneh ******\n\n");

    Scanner input = new Scanner(System.in);
    System.out.println("Please choose a number from the following menu: ");
    String optionSelected = input.nextLine();

    System.out.println("\t 1. Display existing files in ascending order "
                     + "\t 2. Go to file management menu");

    switch (optionSelected) {
        case "1":
            break;
        case "2":
            break;
    }

    return;
    }
}
