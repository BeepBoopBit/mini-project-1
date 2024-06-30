package org.stratpoint.service;
import org.stratpoint.model.ProductItem;

public interface StoreService {
    int search(String query) throws Exception;
    double checkoutCart(double userMoney);
    void addProduct(ProductItem item);
    void displaySearchResult();
    void displayMinimalProduct();
    void displayCartItems();
    void displayCheckoutCart();
    boolean deleteCartItem(int index);
    boolean modifyCartItem(int index, int value);
    boolean addToCart(int index, int amount);
    boolean checkValidIndexProduct(int index);
    boolean checkValidIndexCart(int index);
    boolean isCartEmpty();
}
