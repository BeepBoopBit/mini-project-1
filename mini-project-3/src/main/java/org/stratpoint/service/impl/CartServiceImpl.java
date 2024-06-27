package org.stratpoint.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.stratpoint.model.ProductItem;
import org.stratpoint.service.CartService;
import java.util.HashMap;


@Getter @Setter @NoArgsConstructor
public class CartServiceImpl implements CartService {
    private final HashMap<ProductItem, Integer> cart = new HashMap<>();

    public void addToCart(ProductItem item, Integer amount){
        cart.put(item, amount);
    }

    public void removeToCart(ProductItem item){
        cart.remove(item);
    }

    public void modifyCartItemStock(ProductItem item, Integer stock){
        cart.put(item, stock);
    }

    public void displayCartItems(){
        cart.forEach(
                (item, bought) ->{
                    System.out.println("Product Name: " + item.getProductName());
                    System.out.println("Product Type: " + item.getType());
                    System.out.println("Description: " + item.getDescription());
                    System.out.println("Buying: " + bought);
                }
        );
    }

}
