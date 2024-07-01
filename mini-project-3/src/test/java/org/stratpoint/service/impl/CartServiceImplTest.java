package org.stratpoint.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stratpoint.model.ProductItem;
import org.stratpoint.model.ProductType;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceImplTest {
    CartServiceImpl cart = new CartServiceImpl();
    ArrayList<ProductItem> items = new ArrayList<>();

    @BeforeEach
    void setUp() {
        cart.getCart().clear();
        items.clear();

        String[] products = {
                "water", "melon","papaya",
                "banana", "fruit", "salad",
                "things", "mouse", "key"
        };
        ProductType[] types = {
                ProductType.FOOD,
                ProductType.FOOD,
                ProductType.FOOD,
                ProductType.FOOD,
                ProductType.FOOD,
                ProductType.FOOD,
                ProductType.INGREDIENT,
                ProductType.FOOD,
                ProductType.DRINK
        };
        String[] description = {
                "a", "b", "c",
                "d", "e", "f",
                "g", "h", "i",
        };
        int[] stocks = {
                1,2,3,
                1,2,3,
                1,2,3,
        };

        double[] prices = {
                1,2,3,
                1.1,2.2,3.3,
                1.4,2.5,3.6,
        };

        for(int i = 0 ; i < types.length; ++i){
            items.add(new ProductItem(products[i],description[i], types[i], stocks[i], prices[i]));
            cart.addToCart(items.get(i), 1);
        }
    }

    @Test
    void addToCart() {
        // Set-up already called add to cart
        int[] productNewStockValue = {
                0,1,2,
                0,1,2,
                0,1,2,
        };

        for(int i = 0; i < items.size(); ++i){
            // The value in the items should change even though we only change the cart in set-up
            assertEquals(productNewStockValue[i], items.get(i).getStock());
        }
    }

    @Test
    void removeToCart() throws Exception {
        for (ProductItem item : items) {
            cart.removeToCart(item);

            // Check if the item is still in the cart after removal
            assert (!cart.checkItemIfInCart(item));
        }
    }

    @Test
    void modifyCartItemStock() {
        // Set-up already called add to cart
        int[] productNewStockValue = {
                0,1,2,
                0,1,2,
                0,1,2,
        };

        for(int i = 0; i < productNewStockValue.length; ++i){
            cart.modifyCartItemStock(items.get(i), 1);
            assertEquals(productNewStockValue[i], items.get(i).getStock());
        }
    }

    @Test
    void checkoutCart(){
        double[] prices = {
                1,2,3,
                1.1,2.2,3.3,
                1.4,2.5,3.6,
        };

        double totalPrices = Arrays.stream(prices).sum();
        double userMoney = 100;
        double change = userMoney - totalPrices;

        double cartResult = cart.checkoutCart(userMoney);

        assertEquals(change, cartResult);

        this.setUp();

        userMoney = 10;
        cartResult = cart.checkoutCart(10);
        assertEquals(-1, cartResult);

    }
}