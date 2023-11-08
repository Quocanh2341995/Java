package view;

import model.Borrower;
import model.User;
import service.BookService;
import service.BorrowerService;
import service.UserService;
import utils.AppUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;

import static view.AdminView.scanner;
import static view.AdminView.selectAdminView;

public class UserView {
    static UserService userService = new UserService();
    static BorrowerService borrowerService = new BorrowerService();
    static BookService bookService = new BookService();



    public static void userMenu() {
        try {
            int choice;
            do {
                System.out.println("+------------------------------------------+");
                System.out.println("|          Books Manager Menu              |");
                System.out.println("+------------------------------------------+");
                System.out.println("| 1. Show User Account                     |");
                System.out.println("| 2. Show Borrowed Book List               |");
                System.out.println("| 3. Confirm Return Borrowed Book          |");
                System.out.println("| 4. Show Borrower Near The Expired        |");
                System.out.println("| 5. Show Borrowed Expired                 |");
                System.out.println("| 6. Show Revenue Of Day                   |");
                System.out.println("| 7. Show Revenue Of Expired               |");
                System.out.println("| 0. Exit                                  |");
                System.out.println("+------------------------------------------+");

                choice = AppUtils.getIntWithBound("Input choice: ", 0, 6);
                switch (choice) {
                    case 1 -> showUserAccount();
                    case 2 -> showBorrowedBookList();
                    case 3 -> confirmReturnBook();
                    case 4 -> showBorrowerNearTheExpired();
                    case 5 -> showBorrowerTheExpired();
                    case 6 -> showRevenueOfDay();
                    case 7 -> showRevenueOfExpired();
                    case 0 -> selectAdminView();
                }
            }
            while (choice != 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public static boolean isValidLocalDate(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate.parse(dateString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    private static void showRevenueOfDay() {
        System.out.println("Please enter the date you want to see the revenue: ");
        String s;
        do {
            String inputDay = scanner.nextLine();
            if(isValidLocalDate(inputDay)){
                s = inputDay;
                break;
            }
            System.out.println("Please enter a valid date. EX:yyyy-mm-dd ");
        }while (true);

        LocalDate now = LocalDate.parse(s);
        List<Borrower> borrowerList = borrowerService.getAll();
        double total = 0;
        for (int i = 0; i < borrowerList.size(); i++) {
            if (now.compareTo(borrowerList.get(i).getBorrowDate()) ==0){
                int bookId = borrowerList.get(i).getBookId();
                double price = bookService.getBookDetail(bookId).getPrice();
                total += price * borrowerList.get(i).getQuantity();
//                System.out.println(userService.getByID(borrowerList.get(i).getUserId()).toString());
            }
        }
        System.out.println("Total Revenue is: " + total + "vnd");


    }
    private static void showRevenueOfExpired() {
        List<Borrower> borrowerList = borrowerService.showBorrowerTheExpired();
        double total = 0;
        for (int i = 0; i < borrowerList.size(); i++) {
            int bookId = borrowerList.get(i).getBookId();
            double price = bookService.getBookDetail(bookId).getPrice();
            total += price/5 * borrowerList.get(i).getQuantity();
//            System.out.println(userService.getByID(borrowerList.get(i).getUserId()).toString());

        }
        System.out.println("Total Revenue Expried is: " + total + "vnd");
    }




    public static void showUserAccount() {
        List<User> userList = userService.getAll();
        System.out.println("╔------------------------------------------------- LIST USER --------------------------------------------------╗");
        System.out.println(String.format("| %-7s | %-13s | %-15s | %-20s | %-10s | %-10s | %-15s |",
                "ID","Name","Address","Phonenumber","Password" ,"Role","UserStatus"));
        System.out.println("|---------*---------------*-----------------*----------------------*------------*------------*-----------------|");
        for (User user : userList) {
            System.out.println(user.toString());
            System.out.println("╚---------*---------------*-----------------*----------------------*------------*------------*-----------------╝");
        }
    }

    public static void showBorrowedBookList() {
        List<Borrower> borrowerList = borrowerService.getAll();
        System.out.println("╔-------------------------------- LIST BORROWER  -------------------------------------------╗");
        System.out.println(String.format("| %-7s | %-7s | %-7s | %-13s | %-13s | %-8s | %-13s |",
                "UserId","BorrwerID" ,"BookId" ,"BorrowDate", "ExpDate", "Quantity" ,"BorrowerStatus"));
        System.out.println("|---------*--------*---------*---------------*---------------*----------*------------------|");
        for (Borrower borrower : borrowerList) {
            System.out.println(borrower.toString());
            System.out.println("╚---------*--------*---------*---------------*---------------*----------*------------------╝");
        }
    }

    public static void confirmReturnBook() {
        showBorrowedBookList();
        try {
            System.out.println("Enter Borrowed ID:");
            int id = Integer.parseInt(scanner.nextLine());
            borrowerService.confirmReturnBook(id);
            System.out.println("Successful!!");

        } catch (NumberFormatException e) {
            System.out.println("Please Enter a Number!");
            confirmReturnBook();
        } catch (InputMismatchException inputMismatchException) {
            System.out.println(inputMismatchException);
        }
    }

    public static void showBorrowerNearTheExpired() {
        System.out.println("╔------------------------ LIST BORROWER NEAR THE EXPIRED ------------------------╗");
        String str = String.format("| %-7s | %-7s | %-13s | %-13s | %-8s | %-15s |",
                "Id", "Book Id", "BorrowDate", "ExpDate", "Quantity", "Borrower Status");

        System.out.println(str);
        System.out.println("|---------*---------*---------------*---------------*----------*-----------------|");
        List<Borrower> borrowerNearList = borrowerService.showBorrowerNearTheExpired();
        for (Borrower bor: borrowerNearList
        ) {
            System.out.println(bor.toString());
            System.out.println("╚---------*---------*---------------*---------------*----------*-----------------╝");
        }
    }

    public static void showBorrowerTheExpired() {
        System.out.println("╔------------------------ LIST BORROWER  THE EXPIRED --------------------------------------╗");
        String str = String.format("| %-7s | %-7s | %-7s | %-13s | %-13s | %-8s | %-13s |",
                "UserId","BorrwerID" ,"BookId" ,"BorrowDate", "ExpDate", "Quantity" ,"BorrowerStatus");
        System.out.println(str);
        System.out.println("|---------*--------*---------*---------------*---------------*----------*------------------|");
        List<Borrower> borrowerNearList = borrowerService.showBorrowerTheExpired();
        for (Borrower bor: borrowerNearList
        ) {
            System.out.println(bor.toString());
            System.out.println("╚---------*--------*---------*---------------*---------------*----------*------------------|╝");
        }
    }
}
