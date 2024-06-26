package org.stratpoint.service;
import org.stratpoint.model.ProductItem;

public interface ProductService{
    public void addProduct(ProductItem item);
    public int search(String query) throws Exception;
}
