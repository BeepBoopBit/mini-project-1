package org.example;

import org.example.Books.Book;
import org.example.LMS.LibraryManagementSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryManagementSystemTest {

    @Test
    void deleteAddBook() {
        LibraryManagementSystem lms = new LibraryManagementSystem();
        lms.addBook(new Book("water", "melon", "papaya"));
        lms.addBook(new Book("water", "melon", "papaya"));
        assertEquals(lms.getBooks().size(), 2);
        lms.deleteBook(0);
        assertEquals(lms.getBooks().size(), 1);
    }
}