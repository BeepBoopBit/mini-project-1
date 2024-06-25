package org.example;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstract the management of library
 */
public class LibraryManagementSystem {
    private final ArrayList<Book> _books;
    private final Cache _cache;
    private HashMap<Book, Integer> _searchResult = null;

    public LibraryManagementSystem() {
        _books = new ArrayList<>();
        _cache= new Cache();
    }

    public void addBook(Book newBook) {
        _cache.add(newBook);
        _books.add(newBook);
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
            return 0;
        }

        return _searchResult.size();
    }

    public void displayBooks() {
        int count = 0;
        for (Book book : _books) {
            if (book.getISBN() == null) {
                continue;
            }
            printPretty("[" + count++ + "]" + "\nISBN: " + book.getISBN() + "\nAuthor: " + book.getAuthor() + "\nTitle: " + book.getTitle());
        }
    }

    public void showResultAll() {
        int count = 0;
        for (Book keys : _searchResult.keySet()) {
            if (keys.getISBN() == null) {
                continue;
            }
            printPretty("[" + count++ + "]" + "\nISBN: " + keys.getISBN() + "\nAuthor: " + keys.getAuthor() + "\nTitle: " + keys.getTitle());
        }
    }

    private void printPretty(String str) {
        final String decoration = "=".repeat(20);
        System.out.println(decoration);
        System.out.println(str);
        System.out.println(decoration);
    }

    public ArrayList<Book> getBooks() {
        return _books;
    }
}