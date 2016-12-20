package model.dao;

import java.util.List;

/**
 * Created by Dyvak on 17.12.2016.
 */
public interface GenericDao<E> {
    E find(int id);
    List<E> findAll();
    void create(E e);
    void update(E e);
    void delete(int id);
}
