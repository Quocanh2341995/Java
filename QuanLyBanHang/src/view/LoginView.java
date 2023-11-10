package view;

import model.EPattern;
import model.Role;
import model.User;
import model.UserStatus;
import service.AuthService.LoginService;
import service.AuthService.RegisterService;
import utils.AppUtils;
import utils.ListView;

import static utils.ListView.loginMenuList;
import static view.LoginView.register;

public class LoginView {
    public static User currentUser = new User();
    public static void loginMenu() {
        try {
            ListView.printMenu(loginMenuList);
            int choice  = AppUtils.getNumberMinMax("Vui long lua chon", 0, 2);
            if (choice == 0) System.exit(1);
            if (choice == 1) {
                LoginService.login();
            } else {
                register();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            loginMenu();
        }
    }

    public static void register() {
        System.out.println("-------- Dang ky -------");
        String name = validateName(AppUtils.getString("Nhap ten tai khoan:"));
        String address = AppUtils.getString("Nhap dia chi:");
        String phone;
        phone = AppUtils.getStringWithPattern(EPattern.PHONE_PATTERN);
        String pass = AppUtils.getStringWithPattern(EPattern.PASSWORD_PATTERN);
        User user = new User( name, address, phone, pass, Role.CLIENT, UserStatus.ACTIVE);
        if (RegisterService.register(user)) {
            System.out.println("Dang ky thanh cong!!");
            loginMenu();
        } else {
            System.out.println("Dang ky loi! Vui long thu lai!!!");
            register();
        }
    }
    static String validateName(String value) {
        if(value.matches("[0-9]+")) {
            System.out.println("Name Invalid!!!");
            value = AppUtils.getString("Nhap ten tai khoan:");
            validateName(value);
        }
        return value;
    }
}
