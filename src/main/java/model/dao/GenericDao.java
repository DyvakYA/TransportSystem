package model.dao;

/**
 * Created by Dyvak on 17.12.2016.
 */
public interface GenericDao<E> {
    void create(E e);
    void update(E e, int id);
    void delete(int id);
}
