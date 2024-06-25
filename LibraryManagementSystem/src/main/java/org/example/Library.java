package org.example;

import java.util.Objects;
import java.util.Scanner;

public class Library {
    LibraryManagementSystem _lib = null;

    Library(){
        _lib = new LibraryManagementSystem();
        _lib.addBook(new Book("renz", "water melon", "123-45"));
        _lib.addBook(new Book("renz", "papaya water", "123-45"));
        _lib.addBook(new Book("renz", "water banana", "123-45"));
        _lib.addBook(new Book("renz", "water melon water", "123-45"));
        _lib.addBook(new Book("renz", "water", "123-45"));
    }

    Library(LibraryManagementSystem lib){
        _lib = lib;
    }

    public void start() {
        // Print the options for the menu
        menuBanner();

        // Ask for user Input
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
                        _lib.displayBooks();
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
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String userQuery = scanner.nextLine();

            if (Objects.equals(userQuery, "0")) {
                break;
            }
            try {
                int result = _lib.search(userQuery);
                if (result == 0) {
                    System.out.println("[/] Found " + result + " results");
                }
                _lib.showResultAll();

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

        _lib.addBook(new Book(Author, Title, ISBN));
    }

    private void deleteMenu() {
        _lib.displayBooks();
        System.out.println("(-1 to cancel) > ");

        // Delete a book if it exists, otherwise, return
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("> ");
            int userInput = scanner.nextInt();
            if (userInput == -1) {
                return;
            }
            _lib.deleteBook(userInput);

        } catch (Exception e) {
            System.out.println("[!] Out of Bounce");
        }

    }

    private void menuBanner() {
        String decoration = "=".repeat(10);
        System.out.println(decoration + "LibraryManagementSystem Management System" + decoration);
        System.out.println("1. Search");
        System.out.println("2. Display All Books");
        System.out.println("3. Add new Book");
        System.out.println("4. Delete a Book");
        System.out.println("5. Exit");

        System.out.println(decoration + "=".repeat(25) + decoration);
    }
}
