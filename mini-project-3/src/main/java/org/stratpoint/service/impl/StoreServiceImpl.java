package org.stratpoint.service.impl;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stratpoint.model.ProductItem;
import org.stratpoint.service.CartService;
import org.stratpoint.service.StoreService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Store service.
 */
@Getter @Setter
public class StoreServiceImpl implements StoreService {
    private final ArrayList<ProductItem> items = new ArrayList<>();
    private final CacheServiceImpl<ProductItem> cache;
    private final CartService cart;
    private HashMap<ProductItem, Integer> searchResult = null;
    private final Logger logger = LoggerFactory.getLogger(StoreService.class);

    /**
     * Instantiates a new Store service.
     */
    public StoreServiceImpl(){
        this.cache = new CacheServiceImpl<>();
        this.cart = new CartServiceImpl();
    }

    /**
     *  Check if the cart is empty
     * @return `boolean` about the state of the cart
     */
    public boolean isCartEmpty(){
        return cart.isEmpty();
    }

    /**
     * Check if the index of the product is valid.
     * @param index: index of the product
     * @return `boolean` if the product is valid or not
     */
    public boolean checkValidIndexProduct(int index){
        if(items.size() <= index){
            return false;
        }
        return true;
    }

    /**
     * Check if the index of the item in cart is valid
     * @param index: Index of the cart item
     * @return `boolean` if the product is in cart
     */
    public boolean checkValidIndexCart(int index){
        return cart.checkItemIfInCart(items.get(index));
    }

    /**
     * Add the product to both the cache and item
     * @param item: The product item
     */
    public void addProduct(ProductItem item){
        item.setId(items.size());
        items.add(item);
        cache.add(item);
        logger.info("Adding values an item to the store (" + item.getId() + ")");
    }

    /**
     * Add a product item to the cart>
     * @param index: Index of the product at the items list
     * @param amount: Amount of stock to buy
     * @return a boolean if it successfully add the item to the cart
     */
    public boolean addToCart(int index, int amount){
        logger.info("Adding an item to the cart (" + index + ":" + amount + ")");
        if(checkValidIndexProduct(index)) return cart.addToCart(items.get(index), amount);
        return false;
    }

    /**
     * Search for all the products in the query base on all of its attributes
     * @param query: A string of keywords
     * @return the size of the search result
     * @throws Exception if there's an input error
     */
    public int search(String query) throws Exception {
        var result = cache.search(query);
        searchResult = result;

        if(result == null){
            logger.info("Search Result with query [" + query + "] is null");
            return 0;
        }
        logger.info("Search Result with query [" + query + "] have " + result.size());
        return result.size();
    }

    /**
     * Display all the information of the product;
     */
    public void displayProducts(){
        for (ProductItem item : items) {
            System.out.println("Product Id: " + item.getId());
            System.out.println("Product Name: " + item.getProductName());
            System.out.println("Product Type: " + item.getType());
            System.out.println("Description: " + item.getDescription());
            System.out.println("Stock: " + item.getStock());
        }
    }

    /**
     * Display the product in a minimalistic way for easy menu selection
     */
    public void displayMinimalProduct(){
        for(int i = 0; i < items.size(); ++i){
            ProductItem item = items.get(i);
            System.out.print("[" + i + "] ");
            System.out.println(item.getProductName() + ". " + item.getType() + " (Stock: " + item.getStock() + ")");
        }
    }

    /**
     * Display the result of the search items. Will be called each search query
     */
    public void displaySearchResult(){
        if(searchResult == null){
            return;
        }
        searchResult.forEach((item, val) -> {
            System.out.println("Product Id: " + item.getId());
            System.out.println("Product Name: " + item.getProductName());
            System.out.println("Product Type: " + item.getType());
            System.out.println("Description: " + item.getDescription());
            System.out.println("Stock: " + item.getStock());
        });
    }

    /**
     * Display all the items in the cart.
     */
    public void displayCartItems(){
        cart.displayCartItems();
    }

    /**
     * Delete at item to the cart if it exists
     * @param index the selected item index
     * @return `boolean` if it successfully deleted an item
     */
    public boolean deleteCartItem(int index){

        // Find the index in the items and get it. Remove it to the cart if it exists
        var result = items.stream().filter((item) -> (item.getId() == index)).findFirst();
        if(result.isPresent()){
            logger.info("Removing Item to the cart (" + index + ")");
            if(!cart.checkItemIfInCart(items.get(index))){
                return false;
            }
            try{
                cart.removeToCart(result.get());
            }catch(Exception e){
                return false;
            }
            return true;
        }

        logger.info("Item does not exists in the cart (" + index + ")");
        return false;
    }

    /**
     * Modify the number of stocks to be bought by a user in the cart
     * @param index: The product index
     * @param value: THe amount of value to change
     * @return `boolean` if it successfuly modified the item
     */
    public boolean modifyCartItem(int index, int value){
        logger.info("Modifying cart item (" + index + ") to [" + value + "]");
        return cart.modifyCartItemStock(items.get(index), value);
    }

    /**
     * Display the cart items for checkout.
     */
    public void displayCheckoutCart(){
        cart.displayCheckoutCart();
    }

    /**
     * Process the checkout process in the cart with the user money.
     * @param userMoney The amount of money of the user
     * @return the change or -1 if it fails
     */
    public double checkoutCart(double userMoney){
        return cart.checkoutCart(userMoney);
    }
}
