package bookManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Book {
    private String title;
    private String author;
    private String ISBN;
    private String genre;
    private String publicationDate;
    private int numberOfCopies;
    private Set<String> isbnSet;
    private List<Book> bookCollection = new ArrayList<>();

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
        if (getISBN() != isbn1) {
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

    @Override
    public String toString() {
        return title + "by" + author + " (ISBN: " + ISBN + ")";
    }


    public void readBooksFromCSV(String filePath) {
        int added = 0, skipped = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true; // To skip header row

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(","); // Splitting CSV by comma
                if (data.length != 6) ;

                String title = data[0].trim();
                String author = data[1].trim();
                String ISBN = data[2].trim();
                String genre = data[3].trim();
                String publicationDate = data[4].trim();
                int numberOfCopies = Integer.parseInt(data[5].trim());

                if (isUniqueISBN(ISBN)) {
                    bookCollection.add(new Book(title, author, ISBN, genre, publicationDate, numberOfCopies));
                    isbnSet.add(ISBN);
                    added++;
                } else {
                    skipped++;
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Books Added: " + added);
        System.out.println("Books Skipped (Duplicate ISBNs): " + skipped);
    }

    public void displayBooks() {
        System.out.println();
        for (Book book : bookCollection) {

            System.out.println(book);
        }
    }

    public static void main(String[] args) {
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565",
                "Classic", "1925-04-10", 10);
        Scanner scan = new Scanner(System.in);


        book.readBooksFromCSV("./res/book.csv");
        book.displayBooks();

        String input = scan.nextLine();

        String bookDetails = "";
        for (Book books : book.bookCollection) {
            bookDetails = books.title + " by " + books.author + " (ISBN: " + books.ISBN + ")";
        }
        System.out.println();
        if (bookDetails.toLowerCase().contains(input.toLowerCase())) {
            System.out.println(bookDetails);

        } else {
            System.out.println("Sorry you input doesn't match!");

        }

    }


}


