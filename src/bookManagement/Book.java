package bookManagement;

import java.util.HashSet;
import java.util.Set;

public class Book {
    private String title;
    private String author;
    private String ISBN;
    private String genre;
    private String publicationDate;
    private int numberOfCopies;
    private Set<String> isbnSet;

    public Book(String title, String author, String ISBN, String genre, String publicationDate, int numberOfCopies) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.numberOfCopies = numberOfCopies;
        this.isbnSet = new HashSet<>();
    }


    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    // Display Book Details
    public void displayBookInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + ISBN);
        System.out.println("Genre: " + genre);
        System.out.println("Publication Date: " + publicationDate);
        System.out.println("Number of Copies: " + numberOfCopies);
    }
    // Method to check if ISBN is unique
    public boolean isUniqueISBN(String isbn) {
        return !isbnSet.contains(isbn);
    }

    // Method to add a new book
    public boolean addBook(String isbn) {
        if (isUniqueISBN(isbn)) {
            isbnSet.add(isbn);
            return true; // Successfully added
        }
        return false; // ISBN already exists
    }

    public static void main(String[] args) {
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565",
                "Classic", "1925-04-10", 10);
        book.displayBookInfo();
        System.out.println(book.addBook("978-0743273565"));
        System.out.println(book.addBook("978-0743273562"));
        System.out.println(book.addBook("978-0743273565"));
    }
}
