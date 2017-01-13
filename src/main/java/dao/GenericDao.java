package dao;

import java.util.List;

/**
 * Created by Dyvak on 17.12.2016.
 */
public interface GenericDao<E> {
    E findById(int id);
    List<E> findAll();
    void create(E e);
    void update(E e, int id);
    void delete(int id);
}
