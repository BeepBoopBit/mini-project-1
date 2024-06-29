package org.stratpoint.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stratpoint.model.ProductItem;
import org.stratpoint.service.CartService;
import java.util.HashMap;


@Getter @Setter @NoArgsConstructor
public class CartServiceImpl implements CartService {
    private final HashMap<ProductItem, Integer> cart = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(CartService.class);

    /**
     * Add the item to the cart with its quantity
     * @param item: The product object
     * @param amount: Quantity of bought item
     * @return `boolean` if it was added in the cart
     */
    public boolean addToCart(ProductItem item, Integer amount){
        // Check if it's an invalid amount
        if(amount == 0){
            return false;
        }

        // Check if we have enough stock
        if(!item.buyStock(amount)){
            logger.info("Failed to add stock with item (" + item.getId() + ")");
            return false;
        }else{

            logger.info("Adding stock with item (" + item.getId() + ")");
            // Replace/Add if we have
            cart.put(item, amount);
            return true;
        }
    }

    /**
     * Check the cart is empty
     * @return `boolean` if the cart is empty
     */
    public boolean isEmpty(){
        return cart.isEmpty();
    }

    /**
     * Check if an item is in the cart
     * @param item: The product object
     * @return `boolean` if product is in the cart
     */
    public boolean checkItemIfInCart(ProductItem item){
        return cart.get(item) != null;
    }

    /**
     * Remove an item to the cart
     * @param item: The product object
     */
    public void removeToCart(ProductItem item) throws Exception {
        logger.info("Removing stock (" + item.getId() + ")");
        if(!cart.containsKey(item)){
            throw new Exception("Item doesn't exists in the cart (only happen when adding is not done through the store)");
        }
        item.addStock(cart.get(item));
        cart.remove(item);
    }

    /**
     * Modify the item in the stock
     * @param item: The product object
     * @param stock: The new stock value to be bought
     * @return `boolean` if the operation is successful
     */
    public boolean modifyCartItemStock(ProductItem item, Integer stock){
        if(stock == 0){
            return false;
        }

        // Check if the current stock is enough for the modification of item to buy
        int currentStockBought = cart.get(item);
        item.addStock(currentStockBought);
        if(!item.buyStock(stock)){
            logger.info("Failed to modify stock at (" + item.getId() + ")");
            return false;
        }else{
            logger.info("Modifying stock at (" + item.getId() + ")");
            cart.put(item, stock);
            return true;
        }
    }

    public void displayCartItems(){
        cart.forEach(
                (item, bought) ->{
                    System.out.println("Product Id: " + item.getId());
                    System.out.println("Product Name: " + item.getProductName());
                    System.out.println("Product Type: " + item.getType());
                    System.out.println("Description: " + item.getDescription());
                    System.out.println("Buying: " + bought);
                }
        );
    }

}
