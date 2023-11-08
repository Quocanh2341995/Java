package DAO;

import model.*;
import service.BookService;
import service.BorrowerService;
import service.UserService;
import utils.Serializable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Startup {
    public static void init(){
        if (BookService.listBooks.isEmpty()){
            initBooks();
        }
        if (UserService.listUsers.isEmpty()){
            intiUsers();
        }
        if (BorrowerService.listBorrowers.isEmpty()){
            initBorrower();
        }
    }
    private static void initBooks() {
        Book book1 = new Book(1,"Tham tu lung danh Conan",9, Category.NOVEL,"Series manga trinh thám" ,"Aoyama Gōshō", Publisher.NHAXUATBANKIMDONG,12000.0, BookStatus.INSTOCK,LocalDate.of(2023,11,7));
        Book book2 = new Book(2,"Trang Quynh",10, Category.COMICS,"Bộ truyện tranh thiếu nhi nhiều tập của Việt Nam" ," Kim Khánh", Publisher.NHAXUATBANKIMDONG,16000.0, BookStatus.INSTOCK,LocalDate.of(2023,11,7));
        Book book3 = new Book(3,"Doraemon",15, Category.COMICS,"Hành trình của cậu bé tên là Nobita và nhóm bạn của cậu ấy" ," Motoo Abiko", Publisher.NHAXUATBANKIMDONG,16000.0, BookStatus.INSTOCK,LocalDate.of(2023,11,7));
        Book book4 = new Book(4,"OnePiece",15, Category.COMICS,"Hành trình của cậu bé tên là Luffy trên con đường trở thành vua hải tặc" ,"Oda Eiichiro", Publisher.NHAXUATBANVANHOC,18000.0, BookStatus.INSTOCK,LocalDate.of(2023,11,7));
        Book book5 = new Book(5,"Thuy hu",15, Category.NOVEL,"Sự hình thành và những thành tích của 108 anh hùng Lương Sơn Bạc chống lại triều đình mục nát" ,"Thi Nại Am", Publisher.NHAXUATBANVANHOC,14000.0, BookStatus.INSTOCK,LocalDate.of(2023,11,7));
        List<Book> listBooks = new ArrayList<>();
        listBooks.add(book1);
        listBooks.add(book2);
        listBooks.add(book3);
        listBooks.add(book4);
        listBooks.add(book5);
        BookService.listBooks = listBooks;
        Serializable.serialize(listBooks, EPath.BOOKS.getFilePath());
    }
    private static void intiUsers(){
        User user = new User(1,"Thang","Hue","0905552415","123",Role.ADMIN,UserStatus.ALLOW);
        User user1 = new User(2,"Hung","Hue","0905552416","123",Role.CUSTOMER,UserStatus.ALLOW);
        User user2 = new User(3,"Khanh","Ha Noi","0979875945","123",Role.CUSTOMER,UserStatus.ALLOW);
        User user3 = new User(4,"Quan","Hue","0905552414","123",Role.CUSTOMER,UserStatus.ALLOW);

        List<User> listUsers = new ArrayList<>();
        listUsers.add(user);
        listUsers.add(user1);
        listUsers.add(user2);
        listUsers.add(user3);
        UserService.listUsers = listUsers;
        Serializable.serialize(listUsers,EPath.USERS.getFilePath());
    }
    private static void initBorrower() {
        Borrower borrower1 = new Borrower(2,1,1, LocalDate.of(2023, 7,1), LocalDate.of(2023,7,8),2,BorrowerStatus.BORRWED);
        Borrower borrower2 = new Borrower(3,2,3,LocalDate.of(2023, 7,9), LocalDate.of(2023,7,16),4,BorrowerStatus.BORRWED);
        Borrower borrower3 = new Borrower(4,3,4,LocalDate.of(2023, 7,10), LocalDate.now(),4,BorrowerStatus.BORRWED);

        List<Borrower> listBorrowers = new ArrayList<>();
        listBorrowers.add(borrower1);
        listBorrowers.add(borrower2);
        listBorrowers.add(borrower3);
        BorrowerService.listBorrowers = listBorrowers;
        Serializable.serialize(listBorrowers,EPath.BORROWERS.getFilePath());
    }

}
