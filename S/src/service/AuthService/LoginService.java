package service.AuthService;

import model.Role;
import model.User;
import service.UserService;
import utils.AppUtils;
import view.AdminView;
import view.CustomerView;

import java.io.IOException;

public class LoginService {
    public static User currentUser;
    static CustomerView customerView ;

    public static void
    login() throws IOException {
        String phoneNumber = AppUtils.getString("Enter phone number: ");
        String password = AppUtils.getString("Enter password: ");
        User user = UserService.getByPhoneNumber(phoneNumber);
        if ( user != null && user.getPassword().equals(password) && user.getRole().equals(Role.ADMIN)) {
            currentUser = UserService.getByPhoneNumber(phoneNumber);
            AdminView.selectAdminView();
        } else if ( user != null && user.getPassword().equals(password) && user.getRole().equals(Role.CUSTOMER)) {
            currentUser = UserService.getByPhoneNumber(phoneNumber);
            CustomerView.selectCustomerView();
        } else {
            System.out.println("Invalid account!");
            System.out.println("Please re-enter a valid account and password!!");
            LoginService.login();
        }
    }
}