package model.services;

import java.util.List;

/**
 * Created by Dyvak on 17.12.2016.
 */
public interface GenericService<E> {
    List<E> getAll() ;
    void create(E e);
    void update(E e, int id);
    void delete(int id) ;
}
