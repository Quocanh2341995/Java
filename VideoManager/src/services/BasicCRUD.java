package services;

import java.util.List;

public interface BasicCRUD <T>{
    T getByID (long id);
    List<T> getAll();
    void create(T obj);
    void update (T obj);

    void delete(long id);

    boolean isExist(long id);
}
