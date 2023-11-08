import view.AdminView;

import static DAO.Startup.init;
import static view.LoginView.loginMenu;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static AdminView adminView;
    public static void main(String[] args) {
//            BorrowerService borrowerService = new BorrowerService();
//            Borrower borrower = borrowerService.findById(2);
//            borrower.setExpDate(LocalDate.of(2023,07, 13));
//            borrower.setBorrowerStatus(BorrowerStatus.BORRWED);
//            borrowerService.update(borrower);
//            BorrowerService.save();
        init();
        loginMenu();
    }
}