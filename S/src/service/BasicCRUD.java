package service;

import java.util.List;

public interface BasicCRUD<T> {
    T getByID (int id);
    List<T> getAll();
    void create(T obj);
    void update (T obj);
    void delete (int id);
    boolean isExist(int id);
}
