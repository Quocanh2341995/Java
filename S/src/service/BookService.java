package service;

import model.Book;
import model.BookStatus;
import model.EPath;
import utils.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookService implements BasicCRUD<Book> {
    List<Book> list = new ArrayList<>();
    public static List<Book> listBooks;
    static {
        listBooks = (List<Book>) Serializable.deserialize(EPath.BOOKS.getFilePath());
    }

    public BookService(){
    }
    public static void save() {
        Serializable.serialize(listBooks, EPath.BOOKS.getFilePath());
    }
    @Override
    public Book getByID(int id) {
        return listBooks
                .stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Book getBookDetail(long bookId) {
        list = getAll();
        for (Book book: list){
            if (book.getId() == bookId){
                return book;
            }
        }
        return null;
    }
    public List<Book> getByBookName(String fullName) {
        return listBooks
                .stream()
                .filter(b -> b.getFullName().contains(fullName))
                .collect(Collectors.toList());
    }
    public void add(Book newbook){
        listBooks = getAll();
        listBooks.add(newbook);
        save();
    }
    @Override
    public List<Book> getAll() {
        return listBooks;
    }

    @Override
    public void create(Book book) {
        listBooks.add(book);
    }

    @Override
    public void update(Book obj) {
        for (Book book: listBooks
        ) {
            if(book.getId() == obj.getId()){
                book = obj;
                break;
            }
        }

    }

    @Override
    public void delete(int id) {
        listBooks.removeAll(listBooks.stream()
                .filter(b -> b.getId() == id).toList());
        save();
    }

    @Override
    public boolean isExist(int id) {
        Book book = listBooks.stream()
                .filter(b -> Objects.equals(b.getId(),id))
                .findFirst()
                .orElse(null);
        return book != null;
    }
    public boolean checkBookStatus(long bookId) {
        list = getAll();
        Book book = getBookDetail(bookId);

        return book.getStatus() == BookStatus.INSTOCK;
    }

    public void changeBookStatus(long bookId) {
        list = getAll();
        Book book = getBookDetail(bookId);
        if(book.getQuantity() == 0){
            if (book.getStatus() == BookStatus.INSTOCK){
                book.setStatus(BookStatus.OUTOFSTOCK);
            }
        }
        save();
    }
}
