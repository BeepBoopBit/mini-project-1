package org.stratpoint;

import org.stratpoint.model.ProductItem;
import org.stratpoint.model.ProductType;
import org.stratpoint.service.StoreService;
import org.stratpoint.service.impl.StoreServiceImpl;

import java.util.Scanner;

public class Main {
    StoreService store = new StoreServiceImpl();

    public String askForInput(){
        Scanner scanner = new Scanner(System.in);
        try{
            boolean inMenu = true;
            System.out.print("> ");
            return scanner.nextLine();
        }catch(Exception e){
            System.out.println("[!] Invalid Input");
        }
        return null;
    }

    public void setUp(){
        store.addProduct(new ProductItem("water", "lalo", ProductType.FOOD, 2));
        store.addProduct(new ProductItem("papaya", "melon", ProductType.FOOD, 2));
        store.addProduct(new ProductItem("banana", "melon", ProductType.FOOD, 2));
        store.addProduct(new ProductItem("fruit", "water", ProductType.FOOD, 2));
    }

    public void addProductMenu(){

        boolean isAdding = true;
        while(isAdding){

            // Get the Item Index of the product
            System.out.print("Enter item index (-1 to exit) ");
            Scanner scanner = new Scanner(System.in);

            int userInputIndex = 0;
            try{
                userInputIndex = scanner.nextInt();
            }catch(Exception e){
                System.out.println("[!] Invalid Input");
                continue;
            }

            // Exit
            if(userInputIndex == -1){
                break;
            }

            // If it's a valid index
            if(store.checkValidIndexProduct(userInputIndex)){

                // Change the amount if it exists it the cart
                System.out.print("Enter Amount: ");
                int userInputAmount = scanner.nextInt();
                boolean hasAdded = store.addToCart(userInputIndex, userInputAmount);
                if(hasAdded){
                    System.out.println("[/] Successfully added to cart ProductID: " + userInputIndex);
                }else{
                    System.out.println("[!] Stock Input Error");
                }
            }else{
                System.out.println("[!] Invalid Input");
            }
        }
    }

    public void checkProductsMenu(){
        store.displayMinimalProduct();
        String mainMenu = """
                =============== Check Product ===============
                1.) Add to an item to Cart
                2.) Search Products
                3.) Check Cart
                4.) Display Products
                5.) Exit
                =============================================
                """;
        String userInput = "";
        while(!userInput.equals("-exit")){
            System.out.println(mainMenu);
            userInput = askForInput();
            switch (userInput){
                case "1":{
                    addProductMenu();
                    break;
                }
                case "2":{
                    searchProductMenu();
                    break;
                }
                case "3":{
                    checkCartMenu();
                    break;
                }
                case "4":{
                    store.displayMinimalProduct();
                    break;
                }
                case "5":{
                    userInput = "-exit";
                    break;
                }
                default:{
                    break;
                }
            }
        }

    }

    public void searchProductMenu(){

        boolean isSearching = true;
        while(isSearching){

            // Get the searching query
            System.out.println("Enter your query (\"-exit\" to exit)");
            Scanner scanner = new Scanner(System.in);
            System.out.print("> ");
            String userInput = scanner.nextLine();

            // Exit
            if(userInput.equals("-exit")){
                isSearching = false;
                continue;
            }
            try {
                // if there is a result, print it
                int resultSize = store.search(userInput);
                System.out.println("[/] Found " + resultSize + " results");
                if(resultSize > 0){
                    store.displaySearchResult();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void modifyCartItemMenu(){
        if(store.isCartEmpty()){
            System.out.println("[!] Nothing in cart");
            return;
        }

        boolean inDeleting = true;
        while(inDeleting){

            // Get the index of the item in cart
            System.out.print("Enter Index to modify (-1 to exit): ");
            Scanner scanner = new Scanner(System.in);

            try{
                int userInputIndex = scanner.nextInt();

                // Exit
                if(userInputIndex == -1){
                    inDeleting = false;
                }
                else if (!store.checkValidIndexCart(userInputIndex)) {
                    System.out.println("[!] Invalid Index");
                }
                else{
                    System.out.print("Enter the new stocks to buy: ");

                    int userInputStocks = 0;
                    try{
                        userInputStocks = scanner.nextInt();
                    }catch (Exception e){
                        System.out.println("[!] Invalid Input");
                        continue;
                    }

                    // Modify the cart item if its exists, otherwise, invalid
                    if(store.modifyCartItem(userInputIndex, userInputStocks)){
                        System.out.println("[/] Successfully modified in Cart with ProductID of: " + userInputIndex);
                        break;
                    }else{
                        System.out.println("[!] Invalid Number");
                    }
                }
            }catch (Exception e){
                System.out.println("[!] Invalid Input");
            }
        }
    }

    public void deleteCartItemMenu(){
        if(store.isCartEmpty()){
            System.out.println("[!] Nothing in cart");
            return;
        }
        boolean inDeleting = true;
        while(inDeleting){
            System.out.print("Enter Index to delete (-1 to exit): ");
            Scanner scanner = new Scanner(System.in);
            try{
                int userInput = scanner.nextInt();
                if(userInput == -1){
                    inDeleting = false;
                }else{

                    // Delete if it exists in the cart
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
    }

    public void checkCartMenu(){
        store.displayCartItems();
        String mainMenu = """
                =============== Cart Items ===============
                1.) Modify an Item
                2.) Delete an Item
                3.) Display Cart Items
                4.) Exit
                ==========================================
                """;
        String userInput = "";
        while(!userInput.contains("-exit")){
            System.out.println(mainMenu);
            userInput = askForInput();
            switch (userInput){
                case "1":{
                    modifyCartItemMenu();
                    break;
                }
                case "2":{
                    deleteCartItemMenu();
                    break;
                }
                case "3":{
                    store.displayCartItems();
                    break;
                }
                case "4":{
                    userInput = "-exit";
                    break;
                }
                default:{
                    System.out.println("[!] Invalid Input");
                    break;
                }
            }
        }
    }

    public void menu(){
        String mainMenu = """
                =============== Store Service ===============
                1.) Check/Buy Products
                2.) Search Products
                3.) Check Cart
                4.) Exit
                =============================================
                """;
        String userInput = "";
        while(!userInput.equals("-exit")){
            System.out.println(mainMenu);
            userInput = askForInput();
            switch (userInput){
                case "1":{
                    checkProductsMenu();
                    break;
                }
                case "2":{
                    searchProductMenu();
                    break;
                }
                case "3":{
                    checkCartMenu();
                    break;
                }
                case "4":{
                    userInput = "-exit";
                    break;
                }
                default:{
                    System.out.println("[!] Wrong Input");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setUp();
        main.menu();
    }
}