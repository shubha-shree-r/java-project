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

    public Book(String title, String author, String genre, String publicationDate, int numberOfCopies,String ISBN) {
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
        return title + " " + "by" + " " + author + " " + " (ISBN: " + ISBN + ")" + " " + genre + " " + publicationDate + " " + numberOfCopies;
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

                String genre = data[3].trim();
                String publicationDate = data[4].trim();
                int numberOfCopies = Integer.parseInt(data[5].trim());
                String ISBN = data[2].trim();
                if (isUniqueISBN(ISBN)) {
                    bookCollection.add(new Book(title, author, genre, publicationDate, numberOfCopies,ISBN));
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
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald",
                "Classic", "1925-04-10", 10,"978-0743273565");
        Scanner scan = new Scanner(System.in);


        book.readBooksFromCSV("./res/book.csv");
        book.displayBooks();
        System.out.println("Enter the book name for more details : ");
        String input = scan.nextLine();

        String bookDetails = "";
        int numberofcopy = 0;
        for (Book books : book.bookCollection) {
            bookDetails = books.title + " by " + books.author + " (ISBN: " + books.ISBN + ")" + books.genre + " " + books.publicationDate + " " + books.numberOfCopies;
           numberofcopy = books.numberOfCopies;
        }
        System.out.println();
        if (bookDetails.toLowerCase().contains(input.toLowerCase())) {
            System.out.println(bookDetails);
            if(numberofcopy == 0){
                System.err.println("Out of stock");
            }else{
                System.out.println("Copies avaivable :" + numberofcopy);
            }

        } else {
            System.out.println("Sorry you input doesn't match!");
        }

    }


}


