package org.stratpoint.service;
import org.stratpoint.model.ProductItem;

public interface StoreService {
    void addProduct(ProductItem item);
    int search(String query) throws Exception;
    void displayProducts();
    void displaySearchResult();
    void displayMinimalProduct();
    void displayCartItems();
    boolean deleteCartItem(int index);
    void modifyCartItem(int index, int value);
    boolean addToCart(int index, int amount);
    boolean checkValidIndexProduct(int index);
}
