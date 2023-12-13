package views;

import models.*;
import services.AuthService.LoginService;
import services.AuthService.RegisterService;
import services.UserService;
import utils.AppUtils;
import utils.ListView;

import static utils.ListView.loginMenuList;

public class LoginView {
    public static User currentUser = new User();

    public static void loginMenu() {
        try {
            ListView.printMenu(loginMenuList);
            int choice = AppUtils.getIntWithBound("Enter choice : ", 0, 2);
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
        System.out.println("-------- REGISTER -------");
        String name = validateName(AppUtils.getString("Enter Name"));
        String address = AppUtils.getString("Enter Address");
        String phoneNumber;
        phoneNumber = AppUtils.getStringWithPattern(EPattern.PHONE_PATTERN);

        String password = AppUtils.getStringWithPattern(EPattern.PASSWORD_PATTERN);
        User user = new User(name, address, phoneNumber, password, Role.CUSTOMER, UserStatus.ALLOW);
        if (RegisterService.register(user)) {
            System.out.println("Register successful!!");
            loginMenu();
        } else {
            System.out.println("Register error!! Please try again");
            register();
        }
    }


//    public Role checkRole(){
//        return currentUser.getRole();
//    }

    static String validateName(String value) {
        if (value.matches("[0-9]+")) {
            System.out.println("Name Invalid!!!");
            value = AppUtils.getString("Enter Name");
            validateName(value);
        }
        return value;
    }

//    static String validatePhone(String value){
//        if(!value.matches("[0-9]+")) {
//            System.out.println("Phone number is Invalid!!!");
//            value = AppUtils.getString("Enter Phone number:");
//            validatePhone(value);
//        }
//
//        return value;
//    }
}
