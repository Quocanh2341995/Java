package views;

import models.*;
import services.BookService;
import utils.AppUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;

import static views.AdminView.scanner;
import static views.AdminView.selectAdminView;

public class BookView {
    static BookService bookService = new BookService();

    public static void bookViewMenu() throws IOException {
        int choice;
        do {
            System.out.println("+------------------------------------------+");
            System.out.println("|      Quoc Anh Library                  |");
            System.out.println("+------------------------------------------+");
            System.out.println("| 1. Show Library                          |");
            System.out.println("| 2. Add Book                                |");
            System.out.println("| 3. Delete Book                            |");
            System.out.println("| 4. Edit Book                                |");
            System.out.println("| 0. Exit                                          |");
            System.out.println("+------------------------------------------+");

            choice = AppUtils.getIntWithBound("Input choice : ", 0, 4);
            switch (choice) {
                case 1:
                    showBook();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    editBook();
                    break;
                case 0:
                    System.out.println("Back to Menu");
                    selectAdminView();
                    break;
            }
        }
        while (choice != 0);
    }

    public static void showBook() {
        List<Book> bookList = bookService.getAll();
        System.out.println("╔---------------------------------------------------------------------------------------------------- List Book -------------------------------------------------------------------------------------------------------------------------------╗");
        System.out.println(String.format("| %-5s | %-23s | %-13s | %-10s | %-96s | %-11s | %-17s | %-16s | %-8s | %-10s |",
                " ID", "FullName", "Quantity", "Category", "Description", "Author", "Publisher", "Price", "Status", "ImportDate"));
        System.out.println("|-------*-------------------------*---------------*------------*--------------------------------------------------------------------------------------------------*-------------*-------------------*-------------------*------------*----------|");
        for (Book book : bookList) {
            System.out.println(book.toString());
            System.out.println("╚-------*-------------------------*---------------*------------*--------------------------------------------------------------------------------------------------*-------------*------------------*-----------------*------------*----------╝");
        }
    }

    public static void addBook() {
        List<Book> bookList = bookService.getAll();
        int category, publisher, quantity;

        Book book = new Book();
        String bookName, descriptionBook, author;
        int id = 0;
        if (bookList.size() == 0) {
            id = 1;
        } else id = bookList.get(bookList.size() - 1).getId() + 1;
        book.setId(id);


        do {

            System.out.println("Enter Book Name: ");
            bookName = scanner.nextLine();
            book.setFullName(bookName);
        } while (bookName == "");

        try {
            quantity = AppUtils.getInt("Enter quantity: ");
            book.setQuantity(quantity);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            boolean checkCategory = false;
            do {
                System.out.println("Choose Category: ");
                System.out.println("[1] Novel");
                System.out.println("[2] Short");
                System.out.println("[3] Memoir");
                System.out.println("[4] Scifi");
                System.out.println("[5] Dective");
                String input = scanner.nextLine();

                if (AppUtils.checkInt(input)) {
                    category = Integer.parseInt(input);
                } else {
                    continue;
                }
                switch (category) {
                    case 1:
                        book.setCategory(Category.NOVEL);
                        checkCategory = true;
                        break;
                    case 2:
                        book.setCategory(Category.SHORT);
                        checkCategory = true;
                        break;
                    case 3:
                        book.setCategory(Category.COMICS);
                        checkCategory = true;
                        break;
                    case 4:
                        book.setCategory(Category.DETECTIVE);
                        checkCategory = true;
                        break;
                    case 5:
                        book.setCategory(Category.HORROR);
                        checkCategory = true;
                        break;
                    default:
                        System.out.println("Please Enter Valid Function!");
                        checkCategory = false;
                }
            } while (!checkCategory);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        do {
            System.out.println("Enter Description: ");
            descriptionBook = scanner.nextLine();
            book.setDescription(descriptionBook);
        } while (descriptionBook == "");


        do {
            System.out.println("Enter Author: ");
            author = scanner.nextLine();
            book.setAuthor(author);
        } while (author == "");


        try {
            boolean checkPublisher = false;
            do {
                System.out.println("Choose Publisher:");
                System.out.println("[1] Kim Đồng");
                System.out.println("[2] Phương Nam");
                System.out.println("[3] Thế Kỷ");
                String input = scanner.nextLine();
                if (AppUtils.checkInt(input)) {
                    publisher = Integer.parseInt(input);
                } else {
                    continue;
                }
                switch (publisher) {
                    case 1:
                        book.setPublisher(Publisher.NHAXUATBANVANHOC);
                        checkPublisher = true;
                        break;
                    case 2:
                        book.setPublisher(Publisher.NHAXUATBANKIMDONG);
                        checkPublisher = true;
                        break;
                    case 3:
                        book.setPublisher(Publisher.NHAXUATBANTRE);
                        checkPublisher = true;
                        break;
                    default:
                        System.out.println("Please Enter Valid Function!");
                }
            } while (!checkPublisher);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        book.setStatus(Status.INSTOCK);
        LocalDate date = LocalDate.now();
        book.setImportDate(date);
        bookService.add(book);
        System.out.println("Successfully added book!!");

    }

    public static void deleteBook() {
        try {
            showBook();
            int bookId = AppUtils.getInt("Input book id to remove: ");
            if (!bookService.isExist(bookId)) {
                System.out.printf("Not found %d.\n", bookId);
                deleteBook();
            }
            bookService.delete(bookId);
            System.out.println("Remove book successfully!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean checkExistId(int idBook) {
        List<Book> bookList = bookService.getAll();
        for (Book book : bookList) {
            if (book.getId() == idBook) {
                return true;
            }
        }
        return false;
    }


    public static void editBook() {
        int category, publisher, quantity, price;

        showBook();
        int id;
        boolean exists;

        do {
            System.out.println("Enter Book ID: ");
            id = Integer.parseInt(scanner.nextLine());
            exists = checkExistId(id);

            if (!exists) {
                System.out.println("Invalid Book ID! Please try again.");
            }
        } while (!exists);

        Book book = bookService.getByID(id);
        System.out.println("Enter Book Name:");

        boolean flag;
        String namenew = scanner.nextLine();
        do {
            flag = false;
            if (namenew.equals("")) {
                System.out.println("Error!! Enter Name: ");
                flag = true;
                namenew = scanner.nextLine();
            }
        } while (flag);
        book.setFullName(namenew);

        System.out.println("Enter Description: ");
        boolean check;
        String descriptionNew = scanner.nextLine();
        do {
            check = false;
            if (descriptionNew.equals("")) {
                System.out.println("Error!! Enter Description: ");
                check = true;
                descriptionNew = scanner.nextLine();
            }
        } while (check);
        book.setDescription(descriptionNew);

        System.out.println("Enter Author: ");
        boolean lol;
        String authorNew = scanner.nextLine();
        do {
            lol = false;
            if (authorNew.equals("")) {
                System.out.println("Error!! Enter Author: ");
                lol = true;
                authorNew = scanner.nextLine();
            }
        } while (lol);
        book.setAuthor(authorNew);

        try {
            boolean checkCategory = false;
            do {
                System.out.println("Choose Category: ");
                System.out.println("[1] Novel");
                System.out.println("[2] Short");
                System.out.println("[3] Memoir");
                System.out.println("[4] Scifi");
                System.out.println("[5] Dective");
                String input = scanner.nextLine();

                if (AppUtils.checkInt(input)) {
                    category = Integer.parseInt(input);
                } else {
                    continue;
                }
                switch (category) {
                    case 1:
                        book.setCategory(Category.NOVEL);
                        checkCategory = true;
                        break;
                    case 2:
                        book.setCategory(Category.SHORT);
                        checkCategory = true;
                        break;
                    case 3:
                        book.setCategory(Category.COMICS);
                        checkCategory = true;
                        break;
                    case 4:
                        book.setCategory(Category.DETECTIVE);
                        checkCategory = true;
                        break;
                    case 5:
                        book.setCategory(Category.HORROR);
                        checkCategory = true;
                        break;
                    default:
                        System.out.println("Please Enter Valid Function!");
                        checkCategory = false;
                }
            } while (!checkCategory);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            boolean checkPublisher = false;
            do {
                System.out.println("Choose Publisher:");
                System.out.println("[1] Kim Đồng");
                System.out.println("[2] Phương Nam");
                System.out.println("[3] Thế Kỷ");
                String input = scanner.nextLine();
                if (AppUtils.checkInt(input)) {
                    publisher = Integer.parseInt(input);
                } else {
                    continue;
                }
                switch (publisher) {
                    case 1:
                        book.setPublisher(Publisher.NHAXUATBANVANHOC);
                        checkPublisher = true;
                        break;
                    case 2:
                        book.setPublisher(Publisher.NHAXUATBANKIMDONG);
                        checkPublisher = true;
                        break;
                    case 3:
                        book.setPublisher(Publisher.NHAXUATBANTRE);
                        checkPublisher = true;
                        break;
                    default:
                        System.out.println("Please Enter Valid Function!");
                }
            } while (!checkPublisher);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        book.setStatus(Status.INSTOCK);
        LocalDate date = LocalDate.now();
        book.setImportDate(date);
        try {
            System.out.println("Enter quantity: ");
            quantity = Integer.parseInt(scanner.nextLine());
            book.setQuantity(quantity);
        } catch (NumberFormatException e) {
            e.printStackTrace();

        }
        try {
            System.out.println("Enter price: ");
            price = Integer.parseInt(scanner.nextLine());
            book.setPrice(price);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        bookService.save();
        System.out.println("Book information updated successfully!");

    }


    public static boolean checkQuantity(int quantity, int bookId) {
        Book book = bookService.getByID(bookId);
        if (quantity <= book.getQuantity()) {
            return true;
        }
        return false;
    }
}
