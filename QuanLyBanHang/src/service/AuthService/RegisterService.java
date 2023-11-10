package service.AuthService;

import model.User;
import service.UserService;

import java.util.Objects;

public class RegisterService {
    public static boolean register(User user) {
        if (UserService.listUsers.stream().anyMatch(e -> Objects.equals(e.getPhone(), user.getPhone()))) {
            return false;
        }
        UserService.listUsers.add(user);
        UserService.save();
        return true;
    }
}
