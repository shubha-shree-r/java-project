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


    public String getISBN() {
        return ISBN;
    }

    public boolean isUniqueISBN(String isbn1) {
        if(getISBN() != isbn1){
            return !isbnSet.contains(isbn1);
        }
        return false;
    }

    public boolean addBook(String isbn1) {
        if (isUniqueISBN(isbn1)) {
            isbnSet.add(isbn1);

            return true; // Successfully added
        }

        return false; // At least one ISBN already exists
    }

    public static void main(String[] args) {
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565",
                "Classic", "1925-04-10", 10);
        System.out.println(book.addBook("978-0743273565"));
        System.out.println(book.addBook("978-0743273569"));
        System.out.println(book.addBook("978-0743273565"));
    }
}

