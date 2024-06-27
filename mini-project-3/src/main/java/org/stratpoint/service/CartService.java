package org.stratpoint.service;

import org.stratpoint.model.ProductItem;
import org.stratpoint.service.impl.CartServiceImpl;

public interface CartService {
    void addToCart(ProductItem item, Integer amount);
    void removeToCart(ProductItem item);
    void modifyCartItemStock(ProductItem item, Integer stock);
    void displayCartItems();
}
