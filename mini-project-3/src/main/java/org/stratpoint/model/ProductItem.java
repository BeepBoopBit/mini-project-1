package org.stratpoint.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ProductItem implements Executable{
    String productName;
    ProductType type;
    int stock;

    public ProductItem(String _productName, ProductType _type, int _stock) {
        this.productName = _productName;
        this.type = _type;
        this.stock = _stock;
    }

    @Override
    public String getAllString() {
        return productName + " " + type;
    }
}
