package org.stratpoint.service.Impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stratpoint.model.Book;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CacheServiceImplTest {
    CacheServiceImpl cache = new CacheServiceImpl();
    String[] myWords = null;

    @BeforeEach
    void setUp() {
        StringBuilder str = new StringBuilder();

        List<String> authors = new ArrayList<String>() {{
            add("la");
            add("le");
            add("la");
            add("lo");
            add("lu");
        }};
        authors.forEach((myStr) -> str.append(myStr).append(" "));

        List<String> titles = new ArrayList<String>() {{
            add("WATER melon");
            add("papaya water");
            add("water BANANA");
            add("water melon water");
            add("water");
        }};
        titles.forEach((myStr) -> str.append(myStr.toLowerCase()).append(" "));

        List<String> isbns = new ArrayList<String>() {{
            add("123-45");
            add("123-465");
            add("123-457");
            add("123-458");
            add("123-459");
        }};
        isbns.forEach((myStr) -> str.append(myStr).append(" "));

        myWords = str.toString().split(" ");

        for(int i = 0; i < 5; ++i){
            String author = authors.get(i).toLowerCase();
            String title = titles.get(i).toLowerCase();
            String isbn = isbns.get(i).toLowerCase();

            cache.add(new Book(author,title,isbn));
        }
    }

    @Test
    void add() {

        var wordBank = cache.getWordBank();
        var keySet = wordBank.keySet().stream().toList();
        for (String bookKey : keySet) {
            bookKey = bookKey;

            // Check if they have the same hash key
            assert (Arrays.asList(myWords).contains(bookKey));

            // Check if all the values are the same
            var keySetValues = wordBank.get(bookKey);
            for (Book book : keySetValues.keySet()) {
                var title = book.getTitle().split(" ");
                for(var value: title){
                    value = value;
                    assert (Arrays.asList(myWords).contains(value));
                }

                String[] author = book.getAuthor().split(" ");
                for(var value: author){
                    value = value;
                    assert (Arrays.asList(myWords).contains(value));
                }

                String[] isbn = book.getISBN().split(" ");
                for(var value: isbn){
                    value = value;
                    assert (Arrays.asList(myWords).contains(value));
                }
            }
        }

    }

    @Test
    void search() throws Exception {
        String[] testQueries = {
                "water", "melon", "papaya",
                "banana", "BANANA", "PAPAYA",
                "123-45", "la", "lu",
                "123-458", "none", "watermelon"
        };

        Integer[] sizes = {
                5,2,1,
                1,1,1,
                1,2,1,
                1,0,0
        };

        for(int i = 0; i < sizes.length; ++i){
            var result = cache.search(testQueries[i]);

            if(result == null){
                assertEquals(0, sizes[i]);
                continue;
            }

            // Assert if the size is true
            assertEquals(result.size(), sizes[i]);
        }
    }
};