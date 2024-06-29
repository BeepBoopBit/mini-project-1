package org.stratpoint.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stratpoint.model.ProductItem;
import org.stratpoint.model.ProductType;
import org.stratpoint.service.CacheService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CacheServiceImplTest {
    CacheServiceImpl<ProductItem> cache;
    ArrayList<String> myWords = new ArrayList<>();

    @BeforeEach
    void setUp() {
        cache = new CacheServiceImpl<>();
        int[] id = {
                0,1,2,3,4,5,6,7,8
        };
        String[] products = {
                "water", "melon","papaya",
                "banana", "fruit", "salad",
                "things", "mouse", "key"
        };
        myWords.addAll(Arrays.asList(products));

        ProductType[] types = {
                ProductType.FOOD,
                ProductType.FOOD,
                ProductType.FOOD,
                ProductType.FOOD,
                ProductType.FOOD,
                ProductType.FOOD,
                ProductType.INGREDIENT,
                ProductType.FOOD,
                ProductType.DRINK
        };

        for (ProductType type : types) {
            myWords.add(type.getValue());
        }

        String[] description = {
                "a", "b", "c",
                "d", "e", "f",
                "g", "h", "i",
        };

        myWords.addAll(Arrays.asList(description));

        int[] stocks = {
                1,2,3,
                1,2,3,
                1,2,3,
        };

        for(int i = 0 ; i < types.length; ++i){
            cache.add(new ProductItem(products[i],description[i], types[i], stocks[i]));
        }

        for(var data: myWords){
            System.out.println(data);
        }
    }

    @Test
    void add() {
        // Set-up already called add

        // Check if all the words are in the word bank
        for(var word: myWords){
            assert(cache.getWordBank().containsKey(word.toLowerCase()));
        }
    }

    @Test
    void search() throws Exception {

        String[] queries = {
                "water", "WaTeR",
                "MeLON", "MELON",
                "papaya", "asdf",
                "1", "2", "ijwef",
                "FOOD", "food", "ingredient"
        };

        int[] resultSize = {
                1,1,1,1,1,0,0,0,0,7,7,1,
        };

        for(int i = 0; i < resultSize.length; ++i){
            var resultValue = cache.search(queries[i]);
            if(resultValue == null){
                assertEquals(resultSize[i], 0);
                continue;
            }
            int result = resultValue.size();
            assertEquals(resultSize[i], result);
        }
    }
}