package org.stratpoint.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ProductItem implements Executable{

    int id;
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

    public boolean buyStock(int quantity){
        if(stock >= quantity){
            stock -= quantity;
            return true;
        }
        return false;
    }

    public void addStock(int quantity){
        stock += quantity;
    }

    @Override
    public String getAllString() {
        if(type == null){
            return null;
        }
        return productName + " " + type + " " + description;
    }
}
