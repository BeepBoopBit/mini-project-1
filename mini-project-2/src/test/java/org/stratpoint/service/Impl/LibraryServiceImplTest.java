package org.stratpoint.service.Impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stratpoint.model.Book;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceImplTest {
    LibraryServiceImpl library = new LibraryServiceImpl();
    ArrayList<Book> books = new ArrayList<>();

    @BeforeEach
    void setUp() {
        books.add(new Book("la", "water melon", "1234-52"));
        books.add(new Book("le", "water PAPAYA", "5234-52"));
        books.add(new Book("la", "BANANA papaya", "4234-52"));
        books.add(new Book("lo", "banana fruit", "3234-52"));
        books.add(new Book("lu", "salad water", "2234-52"));

        for(Book book: books){
            library.addBook(book);
        }

    }

    @Test
    void addBook() {
        ArrayList<Book> libraryBooks = library.getBooks();
        for(int i = 0; i < libraryBooks.size(); ++i){
            assertEquals(libraryBooks.get(i), books.get(i));
        }

    }

    @Test
    void deleteBook() {
        library.deleteBook(0);
        books.remove(0);

        library.deleteBook(2);
        books.remove(2);

        library.deleteBook(1);
        books.remove(1);

        ArrayList<Book> libraryBooks = library.getBooks();
        assertEquals(libraryBooks.size(), 2);

        for(int i = 0; i < libraryBooks.size(); ++i){
            assertEquals(libraryBooks.get(i), books.get(i));
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
                3,1,2,
                2,2,2,
                0,2,1,
                0,0,0
        };

        for(int i = 0; i < sizes.length; ++i){
            var result = library.search(testQueries[i]);
            assertEquals(result, sizes[i]);
        }
    }
}