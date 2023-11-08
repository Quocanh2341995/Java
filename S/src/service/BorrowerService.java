package service;

import model.*;
import utils.Serializable;
import view.CustomerView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BorrowerService implements BasicCRUD<Borrower> {
    public static List<Borrower> listBorrowers;
    List<Book> listBooks = new ArrayList<>();
    BookService bookService = new BookService();
    CustomerView customerView = new CustomerView();

    static {
        listBorrowers = (List<Borrower>) Serializable.deserialize(EPath.BORROWERS.getFilePath());
    }


    public List<Borrower> getBorrowers() {
        return listBorrowers;
    }

    public static void save() {
        Serializable.serialize(listBorrowers, EPath.BORROWERS.getFilePath());
    }


    public Borrower getBorrowerBookDetail(int borrowerId) {
        for (Borrower borrower : listBorrowers) {
            if (borrower.getId() == borrowerId) {
                return borrower;
            }
        }
        return null;
    }
    public Borrower findById(int id){
        for (Borrower b: listBorrowers) {
            if(b.getId() == id){
                return b;
            }
        }
        return null;
    }

    public void confirmReturnBook(int borrowerID) {
        Borrower borrower = findById(borrowerID);
        Book book = bookService.getByID(borrower.getBookId());
        book.setQuantity(book.getQuantity() + borrower.getQuantity());
        bookService.update(book);
        BookService.save();
        listBorrowers.remove(borrower);
        save();
    }

    public List<Borrower> displayBorrowerBook(User user) {
        List<Borrower> listBorrowBookByUser = new ArrayList<>();
        listBorrowers = getBorrowers();
        int userId = user.getId();
        for (Borrower borrower : listBorrowers) {
            if (userId == borrower.getUserId()) {
                listBorrowBookByUser.add(borrower);
            }
        }
        return listBorrowBookByUser;
    }
//    public void muonSach(int userId, int bookId, int quantity ) {
//        Borrower borrower = new Borrower();
//        borrower.setUserId(LoginService.currentUser.getId());
//        borrower.setQuantity(quantity);
//        borrower.setBorrowerStatus(BorrowerStatus.BORRWED);
//        borrower.setBorrowDate(LocalDate.now());
//
//        save();
//    }


    public void traSach( int userId, int bookId, int quantity) throws IOException {
        Borrower borrower = listBorrowers.stream().filter(bor -> bor.getUserId() == userId && bor.getBookId()==bookId).findFirst().orElse(null);
        if (borrower != null) {
            if(borrower.getQuantity() - quantity == 0) {

                borrower.setBorrowerStatus(BorrowerStatus.PAY);
            } else if (borrower.getQuantity() - quantity < 0) {
                CustomerView.selectCustomerView();
            } else {
                borrower.setQuantity(borrower.getQuantity() - quantity);
                update(borrower);
            }
        }
        save();
    }

    public void borrowBook( int bookId, User user, int quantity) {
        listBorrowers = getBorrowers();
        listBooks = bookService.getAll();
        LocalDate date = LocalDate.now();
        if (bookService.getByID(bookId).getQuantity() < quantity){
            System.out.println("Sach het roi!!!");
        } else {
            for (Book book : listBooks) {
                if (bookId == book.getId()) {
                    book.setQuantity(book.getQuantity() - quantity);
                }
            }
            System.out.println("Borrow books successfully!!");
        }
        Borrower borrower = new Borrower();
        int id = 0;
        if (listBorrowers.size() == 0) {
            id = 1;
        } else id = listBorrowers.get(listBorrowers.size() - 1).getId() + 1;
        borrower.setId(id);
        borrower.setBookId(bookId);
        borrower.setUserId(user.getId());
        borrower.setBorrowDate(date);
        borrower.setQuantity(quantity);
        borrower.setExpDate(date.plusDays(7));
        borrower.setUserId(user.getId());
        borrower.setBorrowerStatus(BorrowerStatus.BORRWED);
        bookService.changeBookStatus(bookId);
        for (Borrower borrowed: listBorrowers) {
            if (borrowed.getUserId() == borrower.getUserId() && borrowed.getBorrowDate().equals(borrower.getBorrowDate())){
                borrowed.setQuantity(borrowed.getQuantity() + borrower.getQuantity());
                save();
                return;
            }
        }
        listBorrowers.add(borrower);
        save();
    }

    @Override
    public Borrower getByID(int id) {
        return listBorrowers
                .stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Borrower> getAll() {
        return listBorrowers;
    }

    @Override
    public void create(Borrower borrower) {
        listBorrowers.add(borrower);

    }

    @Override
    public void update(Borrower obj) {
        for (Borrower borrower: listBorrowers
        ) {
            if(borrower.getId() == obj.getId()){
                borrower = obj;
                break;
            }
        }

    }

    @Override
    public void delete(int id) {
        listBorrowers.removeAll(listBorrowers.stream()
                .filter(b -> b.getId() == id).toList());

    }

    @Override
    public boolean isExist(int id) {
        Borrower borrower = listBorrowers.stream()
                .filter(b -> Objects.equals(b.getId(),id))
                .findFirst()
                .orElse(null);
        return borrower != null;
    }
    public void print(){
        for (Borrower borrower : listBorrowers){
            System.out.println(borrower.toString());
        }
    }

    public List<Borrower> showBorrowerNearTheExpired() {
        List<Borrower> borrowerNearList = new ArrayList<>();
        for (Borrower borrower: listBorrowers) {
            if (borrower.getExpDate().minusDays(3).isBefore(LocalDate.now())) {
                borrower.setBorrowerStatus(BorrowerStatus.BORRWED);
                borrowerNearList.add(borrower);
            }
        }

        return borrowerNearList;
    }


    public List<Borrower> showBorrowerTheExpired() {
        List<Borrower> borrowerExpiredList = new ArrayList<>();
        for (Borrower borrower: listBorrowers) {
            if (borrower.getExpDate().isBefore(LocalDate.now())) {
                borrower.setBorrowerStatus(BorrowerStatus.OUTOFDATE);
                borrowerExpiredList.add(borrower);
            }
        }
        return borrowerExpiredList;
    }
}
