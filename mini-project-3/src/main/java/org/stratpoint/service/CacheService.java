package org.stratpoint.service;

import java.util.HashMap;

public interface CacheService<T> {
    void add(T ref);
    HashMap<T, Integer> search(String input) throws Exception;
    HashMap<String, HashMap<T, Integer>> getWordBank();
}
