package org.stratpoint.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stratpoint.model.Executable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Optimize version of querying. Implemented an inverted indexing approach using Hashmap
 *  - Insert -> O(N) Time
 *      > where 'N' is the number of words in whole book (including authors, description, ISBN, etc...)
 *  - Search -> O(N) Time
 *      > Where 'N' is the number of words in your query
 *  - [Drawback] Memory O(N) Space
 *      > Where 'N' is the number of unique words/symbols/etc... separated by space in all the books
 */
@Getter @Setter @NoArgsConstructor
public class CacheServiceImpl<T extends Executable> {
    // Contains all the words that are present in all the books
    private final HashMap<String, HashMap<T, Integer>> wordBank = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    /**
     * Add all the words present in the book to the wordBank
     *
     * @param ref The reference book to be processed. (Should be Shallow copy)
     */
    public void add(T ref){

        // Get the string that can be used for searching
        String[] allInputs = ref.getAllString().split(" ");

        // Add all the fields in the books
        for(String word: allInputs){
            addWord(word, ref);
        }
    }

    /**
     * Add the specific word with the reference book to the wordBank
     *
     * @param word the word
     * @param ref  the ref
     */
    private void addWord(String word, T ref){
        // Check if the word is existing
        HashMap<T, Integer> myHash = wordBank.get(word);
        boolean isExisting = myHash != null;

        // Add a new word if it's not
        if(!isExisting){
            wordBank.put(word, new HashMap<>(){{put(ref,1);}});
            logger.info("Added new word [{}] to the wordBank", word);
        }

        // Otherwise, add 1 to the existing word if the book exists
        else{
            isExisting = myHash.get(ref) != null;
            if(isExisting){
                myHash.put(ref, myHash.get(ref)+1);
            }else{
                myHash.put(ref,1);
            }
        }

    }

    /**
     * Optimize Querying for books. Scalable for billions of books, but with more required space
     *
     * @param input the input
     * @return the hash map
     */
    public HashMap<T, Integer> search(String input) throws Exception {

        // Get the individual words in the input
        String[] words = input.split(" ");

        // Get all the books base on the query
        ArrayList<HashMap<T, Integer>> booksWithWords = new ArrayList<>();
        boolean isOperationAND = false;
        for(String word: words){

            // If '&&' exists, this means we need to perform an AND operation to only show words with
            // having all the words present in the input
            if(Objects.equals(word, "&&")){
                // Note: You only need one (&&) in the whole input to make this true
                isOperationAND = true;
                logger.info("Setting [AND] Operation in query: " + input);
                continue;
            }

            // Add the collection of Books from the wordBank if it exists
            HashMap<T, Integer> myHash = wordBank.get(word);
            if(myHash != null){
                booksWithWords.add(wordBank.get(word));
            }
        }

        // No Result
        if(booksWithWords.isEmpty()){
            return null;
        }

        // Use as the returning value.
        int previousSize = Integer.MAX_VALUE, lowestIndex = 0;
        HashMap<T, Integer> result = new HashMap<T, Integer>(booksWithWords.get(lowestIndex));

        // If we need to perform AND operations
        if(isOperationAND){

            // Get the lowest size of the collection of books.
            // This is done for optimizing AND operations
            for(int i = 0; i < booksWithWords.size(); ++i){
                int currentBookSize = booksWithWords.get(i).size();
                if (previousSize >= currentBookSize) {
                    result = booksWithWords.get(i);
                    previousSize = booksWithWords.size();
                    lowestIndex = i;
                }
            }

            // Do the AND operation
            result = new HashMap<T, Integer>(result);
            for(int i = 0; i < booksWithWords.size(); ++i){
                if(i != lowestIndex){
                    result.keySet().retainAll(booksWithWords.get(i).keySet());
                }
            }
        }else{

            // Concatenate everything
            for(int i = 0; i < booksWithWords.size(); ++i){
                if(i != lowestIndex){
                    result.putAll(booksWithWords.get(i));
                }
            }
        }

        return result;
    }

    public HashMap<String, HashMap<T, Integer>> getWordBank() {
        return wordBank;
    }
}
