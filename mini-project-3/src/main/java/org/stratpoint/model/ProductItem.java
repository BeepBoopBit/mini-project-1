package org.stratpoint.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ProductItem implements Executable{

    String productName;
    ProductType type;
    int stock;
    String description;

    public ProductItem(String productName, String description, ProductType type, int stock) {
        this.productName = productName;
        this.type = type;
        this.stock = stock;
        this.description = description;
    }

    @Override
    public String getAllString() {
        if(type == null){
            return null;
        }
        return productName + " " + type;
    }
}
