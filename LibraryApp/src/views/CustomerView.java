package views;

import models.Book;
import models.Borrower;
import models.BorrowerStatus;
import services.AuthService.LoginService;
import services.BookService;
import services.BorrowerService;
import utils.AppUtils;
import utils.ListView;

import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static views.BookView.bookService;
import static views.LoginView.loginMenu;

public class CustomerView {
    static Scanner scanner = new Scanner(System.in);
    static BookService bookService = new BookService();
    static BorrowerService borrowerService = new BorrowerService();

    public static void selectCustomerView() throws IOException {
        int choice = -1;
        do {
            System.out.println();

            System.out.println();
            System.out.println("\u001B[35m            ╔══════════════════════════════════════════════════════════╗\u001B[0m");
            System.out.println("\u001B[35m                                                             \u001B[0m");
            System.out.println("\u001B[35m                        [1]    Show Book List              \u001B[0m");
            System.out.println("\u001B[35m                        [2]    Search By Book Name            \u001B[0m");
            System.out.println("\u001B[35m                        [3]    Borrow Books                 \u001B[0m");
            System.out.println("\u001B[35m                        [4]    Check the history of borrowed books           \u001B[0m");
            System.out.println("\u001B[35m                        [0]    Login                      \u001B[0m");
            System.out.println("\u001B[35m                                                             \u001B[0m");
            System.out.println("\u001B[35m            ╚══════════════════════════════════════════════════════════╝\u001B[0m");
            System.out.println();
            System.out.println();
            choice = AppUtils.getIntWithBound("Input choice", 0, 4);
            switch (choice) {
                case 1:
                    showBook();
                    break;
                case 2:
                    searchBook();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    displayBorrowerBook();
                    break;
                case 0:
                    loginMenu();
                    break;
                default:
                    System.out.println("Please Enter Valid Function!!");
//                    select();
                    break;
            }


        }
        while (choice != 0);
    }


    public static void showBook() {
        List<Book> bookList = bookService.getAll();
        System.out.println("\u001B[31m╔---------------------------------------------------------------------------------------------------- LIST BOOK -------------------------------------------------------------------------------------------------------------------------------╗\u001B[31m");
        System.out.println(String.format("\u001B[33m| %-5s | %-23s | %-13s | %-10s | %-96s | %-11s | %-17s | %-16s | %-8s | %-10s |\u001B[33m",
                " ID", "FullName", "Quantity", "Category", "Description", "Author", "Publisher", "Price", "Status", "ImportDate"));
        System.out.println("\u001B[35m|-------*-------------------------*---------------*------------*--------------------------------------------------------------------------------------------------*-------------*-------------------*-------------------*------------*----------|\u001B[35m");
        for (Book book : bookList) {
            System.out.println(book.toString());
            System.out.println("\u001B[35m╚-------*-------------------------*---------------*------------*--------------------------------------------------------------------------------------------------*-------------*------------------*-----------------*------------*----------╝\u001B[35m");
        }
    }

    public static void searchBook() {
        showBook();
        System.out.println("Enter book name:");
        String search = scanner.next();
        List<Book> list = bookService.getByBookName(search);
        System.out.println("\u001B[31m╔---------------------------------------------------------------------------------------------------- LIST BOOK -------------------------------------------------------------------------------------------------------------------------------╗\u001B[31m");
        System.out.println(String.format("\u001B[33m| %-5s | %-23s | %-13s | %-10s | %-96s | %-11s | %-17s | %-16s | %-8s | %-10s |\u001B[33m",
                " ID", "FullName", "Quantity", "Category", "Description", "Author", "Publisher", "Price", "Status", "ImportDate"));
        System.out.println("\u001B[35m|-------*-------------------------*---------------*------------*--------------------------------------------------------------------------------------------------*-------------*-------------------*-------------------*------------*----------|\u001B[35m");
        for (Book book : list) {
            System.out.println(book.toString());
            System.out.println("\u001B[35m╚-------*-------------------------*---------------*------------*--------------------------------------------------------------------------------------------------*-------------*------------------*-----------------*------------*----------╝\u001B[35m");
        }
    }

    public static void borrowBook() throws IOException {
        showBook();
        int bookId;
        int quantity;
        do {
            System.out.println("Enter book Id:");
            try {
                bookId = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("ID is number!");
                continue;
            }
            if (!BookView.checkExistId(bookId)) {
                System.out.println("ID Book Invalid!");
                continue;
            }
            break;
        } while (true);

        if (!bookService.checkBookStatus(bookId)) {
            System.out.println("...");
            selectCustomerView();
        }

        do {
            System.out.println("Enter quantity: ");
            quantity = Integer.parseInt(scanner.next());
            if (BookView.checkQuantity(quantity, bookId)) {
                break;
            }
            System.out.println("Invalid number of borrowed books!!");
        } while (true);
        borrowerService.borrowBook(Integer.parseInt(String.valueOf(bookId)), LoginService.currentUser, Integer.parseInt(String.valueOf(quantity)));
    }

    public static void displayBorrowerBook() {
        List<Borrower> borrowerList = borrowerService.displayBorrowerBook(LoginService.currentUser);
        System.out.println("\u001B[31m╔------------------------ HISTORY LIST OF BORROWED BOOKS ------------------------╗\u001B[31m");
        String str = String.format("| %-7s | %-7s | %-13s | %-13s | %-8s | %-15s |",
                "Id", "Book Id", "BorrowDate", "ExpDate", "Quantity", "Borrower Status");
        System.out.println(str);
        System.out.println("\u001B[33m|---------*---------*---------------*---------------*----------*-----------------|\u001B[33m");
        for (Borrower borrower : borrowerList) {
            System.out.println(borrower.toString());
            System.out.println("\u001B[35m╚---------*---------*---------------*---------------*----------*-----------------╝\u001B[35m");
        }
    }

}
