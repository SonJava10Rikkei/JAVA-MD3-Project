package rikkei.academy.service;

import java.util.List;

public interface IGenericService<T> {
    List<T> getAll();

    void save(T t);

    T findById(int id);

    void deleteById(int id);


}
