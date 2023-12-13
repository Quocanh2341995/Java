package services.AuthService;

import models.User;
import services.UserService;

import java.util.Objects;

public class RegisterService {
    public static boolean register(User user) {
        if (UserService.listUsers.stream().anyMatch(e -> Objects.equals(e.getPhonenumber(), user.getPhonenumber()))) {
            return false;
        }
        UserService.listUsers.add(user);
        UserService.save();
        return true;
    }
}
