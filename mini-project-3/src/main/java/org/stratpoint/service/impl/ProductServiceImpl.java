package org.stratpoint.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.stratpoint.model.ProductItem;
import org.stratpoint.service.ProductService;

import java.util.ArrayList;

@Getter @Setter @NoArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ArrayList<ProductItem> items = new ArrayList<>();
    private CacheServiceImpl<ProductItem> cache = new CacheServiceImpl<>();

    public ProductServiceImpl(CacheServiceImpl<ProductItem> cache) {
        this.cache = cache;
    }

    public void addProduct(ProductItem item){
        items.add(item);
        cache.add(item);
    }

    public int search(String query) throws Exception {
        var result = cache.search(query);
        var resultSize = result.size();
        if(resultSize == 0){
            return 0;
        }
        return resultSize;

    }
}
