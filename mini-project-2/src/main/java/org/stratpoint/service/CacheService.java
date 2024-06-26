package org.stratpoint.service;

import org.stratpoint.model.Book;

import java.util.HashMap;

public interface CacheService {
    public void add(Book ref);
    public HashMap<Book, Integer> search(String input) throws Exception;
    public HashMap<String, HashMap<Book, Integer>> getWordBank();

}
