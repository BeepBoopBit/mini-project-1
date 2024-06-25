package org.example.Books;

public class Fiction extends Book {
    String _genre = "Fiction";

    public Fiction(String author, String title, String ISBN) {
        super(author, title, ISBN);
    }

    public String getGenre(){
        return _genre;
    }

    // Used for the cache
    public String getAllString(){
        return getISBN() + " " + getTitle() + " " + getAuthor() + " " + getGenre();
    }

    // Used by the library
    public void prettyPrint(){
        System.out.println("Author: " + getAuthor());
        System.out.println("Title: " + getTitle());
        System.out.println("ISBN: " + getISBN());
        System.out.println("Genre: " + getGenre());
    }
}
