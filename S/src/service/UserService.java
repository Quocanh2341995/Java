package service;

import model.Borrower;
import model.EPath;
import model.User;
import model.UserStatus;
import utils.Serializable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService implements BasicCRUD<User> {
    public static List<User> listUsers;
    List<Borrower> borrowerList = new ArrayList<>();
    BorrowerService borrowerService = new BorrowerService();

    static {
        listUsers = (List<User>) Serializable.deserialize((EPath.USERS.getFilePath()));
    }

    public static void save() {
        Serializable.serialize(listUsers, EPath.USERS.getFilePath());
    }



    public List<User> getUsers() {
        return listUsers;
    }

    @Override
    public User getByID(int id) {
        return listUsers.stream().filter(us -> us.getId() == id).findFirst().orElse(null);
    }
    public static User getByPhoneNumber(String phoneNumber) {
        return listUsers.stream().filter(us -> Objects.equals(us.getPhonenumber(), phoneNumber)).findFirst().orElse(null);
    }

    //    public User findByID(int id) {
//        for (User b: listUsers) {
//            if(b.getId() == id){
//                return b;
//            }
//        }
//        return null;
//    }
    //tìm kiếm và trả về đối tượng PhoneNumber từ listUsers dựa trên PhoneNumber.
    public User findByPhoneNumber(String phoneNumber) {
        for (User b : listUsers) {
            if (b.getPhonenumber() == phoneNumber) {
                return b;
            }
        }
        return null;
    }

    //tìm kiếm và trả về đối tượng User từ listUsers dựa trên userId.
    public User getUserDetail(long userId){
        for (User b : listUsers){
            if (b.getId() == userId){
                return b;
            }
        }
        return null;
    }


    @Override
    public List<User> getAll() {
        return listUsers;
    }

    @Override
    public void create(User user) {
        listUsers.add(user);

    }

    @Override
    public void update(User obj) {
        for (User user : listUsers
        ) {
            if (user.getId() == obj.getId()) {
                user = obj;
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        listUsers.removeAll(listUsers.stream().filter(us -> us.getId() == id).toList());

    }
    //     xử lý các tài khoản đã hết hạn.
    public void handleExpiredAccount(User user) {
        borrowerList = borrowerService.getAll();
        for (Borrower borrower : borrowerList){
            if(borrower.getExpDate().isBefore(LocalDate.now())){
                user.setUserStatus(UserStatus.DENY);
                break;
            } else {
                user.setUserStatus(UserStatus.ALLOW);
            }
        }
        listUsers = getUsers();
        for(User user1: listUsers){
            if (user1.getId() == user.getId()){
                user1.setUserStatus(user.getUserStatus());
            }
        }
        save();

    }
    //kiểm tra ngày hết hạn trong danh sách borrowerList và in ra thoong bao nếu ngày hết hạn là ngày hiện tại hoặc ngày trước đó.
    public void getMessage(User user, String message){
        borrowerList = borrowerService.getAll();
        for (Borrower borrower : borrowerList){
            if (borrower.getExpDate().minusDays(1).equals(LocalDate.now()) || borrower.getExpDate().equals(LocalDate.now())){
                System.out.println(message);
                System.out.println();
                break;
            }
        }
    }



    @Override
    public boolean isExist(int id) {
        User user = listUsers.stream()
                .filter(b -> Objects.equals(b.getId(), id))
                .findFirst()
                .orElse(null);
        return user != null;
    }

    public void checkNumber(int number) {
        try {
            int number1 = number;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
