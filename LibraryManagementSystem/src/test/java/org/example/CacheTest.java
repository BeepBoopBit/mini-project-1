package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {

    @Test
    void search() {
        Cache cache = new Cache();
        cache.add(new Book("la", "water melon", "123-45"));
        cache.add(new Book("le", "papaya water", "123-465"));
        cache.add(new Book("la", "water banana", "123-457"));
        cache.add(new Book("lo", "water melon water", "123-458"));
        cache.add(new Book("lu", "water", "123-459"));
        try {
            var hash = cache.search("papaya && banana");
            assertEquals(hash.size(), 0);

            hash = cache.search("papaya banana");
            assertEquals(hash.size(), 2);

            hash = cache.search("water");
            assertEquals(hash.size(), 5);

            hash = cache.search("papaya");
            assertEquals(hash.size(), 1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}