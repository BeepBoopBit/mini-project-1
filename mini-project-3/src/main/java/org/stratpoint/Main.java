package org.stratpoint;

import org.stratpoint.model.ProductItem;
import org.stratpoint.model.ProductType;
import org.stratpoint.service.StoreService;
import org.stratpoint.service.impl.StoreServiceImpl;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Main {
    StoreService store = new StoreServiceImpl();

    public void askForInput(String mainMenu, ArrayList<Callable<Void>> func){
        System.out.println(mainMenu);
        Scanner scanner = new Scanner(System.in);
        try{
            boolean inMenu = true;
            while(inMenu){
                System.out.print(">");
                int userInput = scanner.nextInt();

                boolean hasCalled = false;
                int functionSize = func.size();
                if(functionSize > userInput){
                    func.get(userInput).call();
                }else if(functionSize == userInput){
                    inMenu = false;
                }
                else{
                    System.out.println("[!] Invalid Input");
                }
            }
        }catch(Exception e){
            System.out.println("[!] Invalid Input");
        }
    }

    public void setUp(){
        store.addProduct(new ProductItem("water", "melon", ProductType.FOOD, 2));
        store.addProduct(new ProductItem("water", "melon", ProductType.FOOD, 2));
        store.addProduct(new ProductItem("water", "melon", ProductType.FOOD, 2));
        store.addProduct(new ProductItem("water", "melon", ProductType.FOOD, 2));
        store.displayProducts();
    }

    public Callable<Void> addProductMenu(){
        System.out.print("Enter item index (-1 to exit)");

        boolean isAdding = true;
        while(isAdding){
            Scanner scanner = new Scanner(System.in);
            int userInputIndex = scanner.nextInt();
            if(store.checkValidIndex(userInputIndex)){
                int userInputAmount = scanner.nextInt();
                boolean hasAdded = store.addToCart(userInputIndex, userInputAmount);
                if(hasAdded){
                    System.out.println("[/] Successfully added to cart ProductID: " + userInputIndex);
                }else{
                    System.out.println("[!] Not enough stock");
                }
            }else{
                System.out.println("[!] Invalid Input");
            }
        }
        return null;
    }

    public Callable<Void> checkProductsMenu(){
        store.displayMinimalProduct();
        String mainMenu = """
                =============== Check Product ===============
                1.) Add to an item to Cart
                2.) Search Products
                3.) Check Cart
                4.) Exit
                =============================================
                """;
        ArrayList<Callable<Void>> functions = new ArrayList<>(){{
            add(addProductMenu());
            add(searchProductMenu());
            add(checkCartMenu());
        }};
        askForInput(mainMenu, functions);
        return null;
    }

    public Callable<Void> searchProductMenu(){
        System.out.println("Enter your query (\"\\exit\" to exit)");
        Scanner scanner = new Scanner(System.in);

        boolean isSearching = true;
        while(isSearching){
            System.out.print("> ");
            String userInput = scanner.nextLine();
            try {
                int resultSize = store.search(userInput);
                System.out.println("[/] Found " + resultSize + " results");
                store.displaySearchResult();

                if(Objects.equals(userInput, "\\exit")){
                    isSearching = false;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    public Callable<Void> modifyCartItemMenu(){
        boolean inDeleting = true;
        while(inDeleting){
            System.out.print("Enter Index to modify (-1 to exit): ");
            Scanner scanner = new Scanner(System.in);

            try{
                int userInputIndex = scanner.nextInt();
                if(userInputIndex == -1){
                    inDeleting = false;
                }else{
                    System.out.print("Enter the number of stocks to buy: ");
                    int userInputStocks = scanner.nextInt();

                    store.modifyCartItem(userInputIndex, userInputStocks);
                    System.out.println("[/] Successfully modified in Cart with ProductID of: " + userInputIndex);
                }
            }catch (Exception e){
                System.out.println("[!] Invalid Input");
            }
        }
        return null;
    }

    public Callable<Void> deleteCartItemMenu(){
        boolean inDeleting = true;
        while(inDeleting){
            System.out.print("Enter Index to delete (-1 to exit): ");
            Scanner scanner = new Scanner(System.in);
            try{
                int userInput = scanner.nextInt();
                if(userInput == -1){
                    inDeleting = false;
                }else{
                    boolean hasDeleted = store.deleteCartItem(userInput);
                    if(hasDeleted){
                        System.out.println("[/] Successfully Deleted in Cart with ProductID of: " + userInput);
                    }else{
                        System.out.println("[!] Index Out Of Bounce");
                    }
                }
            }catch (Exception e){
                System.out.println("[!] Invalid Input");
            }
        }
        return null;
    }

    public Callable<Void> checkCartMenu(){
        store.displayCartItems();
        String mainMenu = """
                =============== Cart Items ===============
                1.) Modify an Item
                2.) Delete an Item
                3.) Exit
                ==========================================
                """;

        ArrayList<Callable<Void>> functions = new ArrayList<>(){{
            add(modifyCartItemMenu());
            add(deleteCartItemMenu());
        }};
        askForInput(mainMenu, functions);

        return null;
    }

    public void menu(){
        String mainMenu = """
                =============== Store Service ===============
                1.) Check Products
                2.) Search Products
                3.) Check Cart
                4.) Exit
                =============================================
                """;
        ArrayList<Callable<Void>> functions = new ArrayList<>(){{
            add(checkProductsMenu());
            add(searchProductMenu());
            add(checkCartMenu());
        }};
        askForInput(mainMenu, functions);
    }

    public static void main(String[] args) {
        // TODO: fix callable
        Main main = new Main();
        main.setUp();
        main.menu();
    }
}