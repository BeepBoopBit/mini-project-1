package org.stratpoint.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stratpoint.model.ProductItem;
import org.stratpoint.model.ProductType;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StoreServiceImplTest {
    StoreServiceImpl store = new StoreServiceImpl();
    ArrayList<ProductItem> items = new ArrayList<>();

    @BeforeEach
    void setUp() {
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
            store.addProduct(items.get(i));
            store.addToCart(i, 1);
        }
    }

    @Test
    void addProduct() {
        // Add product was called in set-up
        for(int i = 0; i < items.size(); ++i){
            assertEquals(store.getItems().get(i),items.get(i));
        }
    }

    @Test
    void addToCart() {
        // Add product was called in set-up
        for(int i = 0; i < items.size(); ++i){
            // Check if the item is in the cart
            assert(store.getCart().checkItemIfInCart(items.get(i)));
            // Check if the index is a valid index
            assert(store.checkValidIndexCart(i));
        }
    }

    @Test
    void search() throws Exception {
        String[] queries = {
                "water", "WaTeR",
                "MeLON", "MELON",
                "papaya", "asdf",
                "1", "2", "ijwef",
                "FOOD", "food", "ingredient"
        };

        int[] resultSize = {
                1,1,1,1,1,0,0,0,0,7,7,1,
        };

        for(int i = 0; i < resultSize.length; ++i){
            var result = store.search(queries[i]);
            assertEquals(resultSize[i], result);
        }
    }

    @Test
    void deleteCartItem() {
        for(int i = 0; i < items.size(); ++i){
            store.deleteCartItem(i);

            assert (!store.checkValidIndexCart(i));
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

        double cartResult = store.checkoutCart(userMoney);

        assertEquals(change, cartResult);

        this.setUp();

        userMoney = 10;
        cartResult = store.checkoutCart(10);
        assertEquals(-1, cartResult);
    }
}