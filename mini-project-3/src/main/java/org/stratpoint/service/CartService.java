package org.stratpoint.service;

import org.stratpoint.model.ProductItem;
import org.stratpoint.service.impl.CartServiceImpl;

public interface CartService {
    boolean addToCart(ProductItem item, Integer amount);
    void removeToCart(ProductItem item) throws Exception;
    boolean modifyCartItemStock(ProductItem item, Integer stock);
    void displayCartItems();
    boolean checkItemIfInCart(ProductItem item);
    boolean isEmpty();
}
