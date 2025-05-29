package com.example.coursachTrzbd.services;

import java.util.List;

public interface CRUDService<T> {
    T getById(Integer id);
    List<T> getAll();
    T create(T item);
    T update(T item);
    void delete(Integer id);
}
