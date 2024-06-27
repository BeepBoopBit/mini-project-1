package org.stratpoint.service.impl;

import lombok.Getter;
import lombok.Setter;
import org.stratpoint.model.ProductItem;
import org.stratpoint.service.CartService;
import org.stratpoint.service.StoreService;

import java.util.ArrayList;
import java.util.HashMap;

// TODO: You can't remove a product until it's in stock.

@Getter @Setter
public class StoreServiceImpl implements StoreService {
    private final ArrayList<ProductItem> items = new ArrayList<>();
    private final CacheServiceImpl<ProductItem> cache;
    private final CartService cart;
    private HashMap<ProductItem, Integer> searchResult = null;

    public StoreServiceImpl(){
        this.cache = new CacheServiceImpl<>();
        this.cart = new CartServiceImpl();
    }
    public StoreServiceImpl(CacheServiceImpl<ProductItem> cache, CartService cart) {
        this.cache = cache;
        this.cart = cart;
    }

    public ProductItem getProductItem(int index){
        return items.get(index);
    }

    public boolean checkValidIndexProduct(int index){
        if(items.size() <= index){
            return false;
        }
        return true;
    }

    public void addProduct(ProductItem item){
        item.setId(items.size());
        items.add(item);
        cache.add(item);
    }

    public boolean addToCart(int index, int amount){

        if(checkValidIndexProduct(index)){
            cart.addToCart(items.get(index), amount);
            return true;
        }

        return false;
    }

    public int search(String query) throws Exception {
        var result = cache.search(query);
        searchResult = result;

        if(result == null){
            return 0;
        }
        return result.size();
    }

    public void displayProducts(){
        for (ProductItem item : items) {
            System.out.println("Product Id: " + item.getId());
            System.out.println("Product Name: " + item.getProductName());
            System.out.println("Product Type: " + item.getType());
            System.out.println("Description: " + item.getDescription());
            System.out.println("Stock: " + item.getStock());
        }
    }

    public void displayMinimalProduct(){
        for(int i = 0; i < items.size(); ++i){
            ProductItem item = items.get(i);
            System.out.print("[" + i + "] ");
            System.out.print("Product Name: " + item.getProductName());
            System.out.println("(" + item.getType() + ")");
        }
    }

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

    public void displayCartItems(){
        cart.displayCartItems();
    }

    public boolean deleteCartItem(int index){

        var result = items.stream().filter((item) -> (item.getId() == index)).findFirst();
        if(result.isPresent()){
            cart.removeToCart(result.get());
            return true;
        }
        return false;
    }

    public void modifyCartItem(int index, int value){
        cart.modifyCartItemStock(items.get(index), value);
    }
}
