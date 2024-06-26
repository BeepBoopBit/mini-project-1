package org.stratpoint.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stratpoint.model.Book;
import org.stratpoint.service.LibraryService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstract the management of library
 */
public class LibraryServiceImpl implements LibraryService {
    private final ArrayList<Book> books;
    private final CacheServiceImpl cacheServiceImpl;
    private HashMap<Book, Integer> searchResult = null;
    private final Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);

    public LibraryServiceImpl() {
        books = new ArrayList<>();
        cacheServiceImpl = new CacheServiceImpl();
    }

    public void addBook(Book newBook) {
        cacheServiceImpl.add(newBook);
        books.add(newBook);
        logger.info("Successfully Added new Book to the cacheServiceImpl and list");
    }

    public void deleteBook(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= books.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        // Remove the book from the cacheServiceImpl and list
        books.get(index).setISBN(null); // CacheServiceImpl Deletion
        books.remove(index);
    }

    /**
     * Search the library for a specific books given a query
     *
     * @param query the string that contains the query
     * @return The number of searched result
     * @throws Exception if the query is wrong
     */
    public int search(String query) throws Exception {

        // Search for the user query.
        searchResult = cacheServiceImpl.search(query);

        // If there's nothing, return 0, otherwise, return the size
        if (searchResult == null) {
            logger.info("No Result found on Query: " + query);
            return 0;
        }

        return searchResult.size();
    }

    public void displayBooks() {
        int count = 0;
        for (Book book : books) {
            if (book.getISBN() == null) {
                System.out.println("[!] Found a Deleted Record in the CacheServiceImpl" );
                continue;
            }
            printPretty(book, count);
            count++;
        }
    }

    public void showResultAll() {
        int count = 0;
        for (Book book: searchResult.keySet()) {
            if (book.getISBN() == null) {
                System.out.println("[!] Found a Deleted Record in the CacheServiceImpl" );
                continue;
            }
            printPretty(book, count);
            count++;
        }
    }

    private void printPretty(Book book, int count) {
        final String decoration = "=".repeat(20);
        System.out.println(decoration);
        System.out.println("[" + count + "]");
        book.prettyPrint();
        System.out.println(decoration);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}