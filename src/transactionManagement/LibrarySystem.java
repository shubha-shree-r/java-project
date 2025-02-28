package transactionManagement;

import bookManagement.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem extends Book {

//    static List<Book> bookcollection = new ArrayList<Book>();
private Book book;


public LibrarySystem(){
    super();
}

    public Book findBooksByISBN(String isbn){
         for(Book book : super.bookCollection){
             if (book.ISBN.equals(isbn)) {

                 return  book;
             }
         }
         return null;
    }

    public void borrowBooks(String isbn){
        Book book = findBooksByISBN(isbn);
        System.out.println(book);

    }
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter ISBN of the book you want to borrow : " );
        String input = scan.nextLine();

    }

    public static void main(int isbn) {

    }
}
