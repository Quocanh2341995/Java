import view.AdminView;
import view.ProductView;

import java.io.IOException;

import static S.Startup.init;
import static view.LoginView.loginMenu;

public class demo {
    static AdminView adminView;

    public static void main(String[] args) {
        init();
        loginMenu();
    }
}
