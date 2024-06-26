package org.stratpoint.model;

public enum ProductType {
    FOOD("FOOD"),
    DRINK("DRINK"),
    GAME("GAME"),
    INGREDIENT("INGREDIENT");

    private String value;

    ProductType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
