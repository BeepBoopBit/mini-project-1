package org.stratpoint.service;

import org.stratpoint.model.Book;

import java.util.ArrayList;

public interface LibraryService {
    void addBook(Book newBook);
    void deleteBook(int index) throws ArrayIndexOutOfBoundsException;
    int search(String query) throws Exception;
    void displayBooks();
    void showResultAll();
    ArrayList<Book> getBooks();
}
