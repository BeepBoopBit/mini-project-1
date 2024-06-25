package org.example;

public class Book{
    private String ISBN = "";
    private String Title = "";
    private String Author =  "";

    public Book(String author, String title, String ISBN) {
        Author = author;
        Title = title;
        this.ISBN = ISBN;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }
}
