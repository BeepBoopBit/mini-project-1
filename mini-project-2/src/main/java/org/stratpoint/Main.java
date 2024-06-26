package org.stratpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stratpoint.model.Book;
import org.stratpoint.service.Impl.LibraryServiceImpl;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    private LibraryServiceImpl lib = null;
    private final Logger logger = LoggerFactory.getLogger(Main.class);

    public Main(){
        lib = new LibraryServiceImpl();
        lib.addBook(new Book("la", "WATER melon", "123-45"));
        lib.addBook(new Book("le", "papaya water", "123-465"));
        lib.addBook(new Book("la", "water BANANA", "123-457"));
        lib.addBook(new Book("lo", "water melon water", "123-458"));
        lib.addBook(new Book("lu", "water", "123-459"));
        logger.info("Default Initialize model");
    }

    public Main(LibraryServiceImpl lib){
        this.lib = lib;
        logger.info("Manual Initialization of model");
    }

    public void start() {
        // Print the options for the menu
        menuBanner();

        // Process menu option
        boolean inMenu = true;
        while (inMenu) {
            System.out.print("> ");

            Scanner scanner = new Scanner(System.in);
            try {
                int userInput = scanner.nextInt();
                switch (userInput) {
                    case 1: {
                        searchMenu();
                        menuBanner();
                        break;
                    }
                    case 2: {
                        lib.displayBooks();
                        menuBanner();
                        break;
                    }
                    case 3: {
                        addMenu();
                        menuBanner();
                        break;
                    }
                    case 4: {
                        deleteMenu();
                        menuBanner();
                        break;
                    }
                    case 5: {
                        inMenu = false;
                        break;
                    }
                    default: {
                        System.out.println("Invalid choice");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid choice");
            }
        }
    }

    private void searchMenu() {
        System.out.println("Enter your query (0 - exit)");

        // Get the result from the _cache base on the query
        boolean isSearching = true;
        while (isSearching) {

            // Get user Input
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String userQuery = scanner.nextLine();

            // Check if the user wants to stop
            if (Objects.equals(userQuery, "0")) {
                break;
            }

            // Try to search the library base on the query
            try {
                int result = lib.search(userQuery);
                if (result == 0) {
                    System.out.println("[/] Found " + result + " results");
                }
                lib.showResultAll();

            } catch (Exception e) {
                // Do nothing
            }
        }

    }

    private void addMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the ISBN: ");
        String ISBN = scanner.nextLine();

        System.out.print("Enter Author: ");
        String Author = scanner.nextLine();

        System.out.print("Enter Title: ");
        String Title = scanner.nextLine();

        lib.addBook(new Book(Author, Title, ISBN));
    }

    private void deleteMenu() {
        lib.displayBooks();
        System.out.println("(-1 to cancel) > ");

        // Delete a book if it exists, otherwise, return
        Scanner scanner = new Scanner(System.in);
        try {

            // Get User Input
            System.out.print("> ");
            int userInput = scanner.nextInt();

            // If the user wants to cancel
            if (userInput == -1) {
                return;
            }
            lib.deleteBook(userInput);

        } catch (Exception e) {
            System.out.println("[!] Out of Bounce");
        }

    }

    private void menuBanner() {
        String decoration = "=".repeat(10);
        System.out.println(decoration + "LibraryServiceImpl Management System" + decoration);
        System.out.println("1. Search");
        System.out.println("2. Display All model");
        System.out.println("3. Add new Book");
        System.out.println("4. Delete a Book");
        System.out.println("5. Exit");

        System.out.println(decoration + "=".repeat(25) + decoration);
    }

    public static void main(String[] args) {
        Main lib = new Main();
        lib.start();
    }
}