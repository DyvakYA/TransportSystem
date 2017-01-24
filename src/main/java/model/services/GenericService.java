package model.services;

import java.sql.SQLException;

/**
 * Created by Dyvak on 17.12.2016.
 */
public interface GenericService<E> {
    //E findById(int id);
    void create(E e) throws SQLException;
    void update(E e, int id) throws SQLException;
    void delete(int id) throws SQLException;
}
