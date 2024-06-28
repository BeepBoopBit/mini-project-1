package org.stratpoint.service;
import org.stratpoint.model.ProductItem;

public interface StoreService {
    void addProduct(ProductItem item);
    int search(String query) throws Exception;
    void displaySearchResult();
    void displayMinimalProduct();
    void displayCartItems();
    boolean deleteCartItem(int index);
    boolean modifyCartItem(int index, int value);
    boolean addToCart(int index, int amount);
    boolean checkValidIndexProduct(int index);
    boolean checkValidIndexCart(int index);
    boolean isCartEmpty();
}
