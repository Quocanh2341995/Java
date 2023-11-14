package service.AuthService;

import model.Role;
import model.User;
import service.UserService;
import utils.AppUtils;
import view.AdminView;
import view.ClientView;

import java.io.IOException;

public class LoginService {
    public static User currentUser;
    static ClientView clientView;

    public static void login() throws IOException {
        String phone = AppUtils.getString("Nhap so dien thoai:");
        String pass = AppUtils.getString("Nhap mat khau:");
        User user = UserService.getByPhone(phone);
        if(user != null && user.getPass().equals(pass) && user.getRole().equals(Role.ADMIN)) {
            currentUser = UserService.getByPhone(phone);
            AdminView.selectAdminView();
        } else if (user != null && user.getPass().equals(pass) && user.getRole().equals(Role.CLIENT)) {
            currentUser = UserService.getByPhone(phone);
            ClientView.selectBuyerView();
        } else {
            System.out.println("Tai khoan hoac mat khau khong dung!");
            System.out.println("Vui long nhap lai tai khoan va mat khau!!!");
            LoginService.login();
        }
    }
}
