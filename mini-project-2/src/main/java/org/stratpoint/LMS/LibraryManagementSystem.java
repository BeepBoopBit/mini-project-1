package org.stratpoint.LMS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stratpoint.Books.Book;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstract the management of library
 */
public class LibraryManagementSystem {
    private final ArrayList<Book> _books;
    private final Cache _cache;
    private HashMap<Book, Integer> _searchResult = null;
    private final Logger logger = LoggerFactory.getLogger(LibraryManagementSystem.class);

    public LibraryManagementSystem() {
        _books = new ArrayList<>();
        _cache= new Cache();
    }

    public void addBook(Book newBook) {
        _cache.add(newBook);
        _books.add(newBook);
        logger.info("Successfully Added new Book to the cache and list");
    }

    public void deleteBook(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= _books.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        // This is done to ensure that the cache wouldn't look at the (none existing) reference book
        // The book is still there at the 'Cache' and still requires a clean-up.
        // TODO: Clean-up method for the cache (Optional)
        _books.get(index).setISBN(null);
        _books.get(index).setAuthor(null);
        _books.get(index).setTitle(null);
        logger.warn("Deleting Information: Need to clean-up the cache");

        // Remove book
        _books.remove(index);
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
        _searchResult = _cache.search(query);

        // If there's nothing, return 0, otherwise, return the size
        if (_searchResult == null) {
            logger.info("No Result found on Query: " + query);
            return 0;
        }

        return _searchResult.size();
    }

    public void displayBooks() {
        int count = 0;
        for (Book book : _books) {
            if (book.getISBN() == null) {
                System.out.println("[!] Found a Deleted Record in the Cache" );
                continue;
            }
            printPretty(book, count);
            count++;
        }
    }

    public void showResultAll() {
        int count = 0;
        for (Book book: _searchResult.keySet()) {
            if (book.getISBN() == null) {
                System.out.println("[!] Found a Deleted Record in the Cache" );
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
        return _books;
    }
}