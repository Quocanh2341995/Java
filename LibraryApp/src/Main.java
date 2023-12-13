import DAO.Startup;
import models.Book;
import models.Borrower;
import models.BorrowerStatus;
import services.BookService;
import services.BorrowerService;
import views.AdminView;

import java.io.IOException;
import java.time.LocalDate;

import static DAO.Startup.init;
import static views.AdminView.selectAdminView;
import static views.LoginView.loginMenu;


public class Main {
    static AdminView adminView;
    public static void main(String[] args) {
        init();
        loginMenu();
    }
}