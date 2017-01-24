package model.services.service;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.TransportDao;
import model.entities.Transport;
import model.services.TransportServiceable;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class TransportService implements TransportServiceable{

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final TransportService INSTANCE = new TransportService();
    }

    public static TransportService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Optional<Transport>> getAll() throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            TransportDao transportDao = daoFactory.createTransportDao(connection);
            return transportDao.findAll();
        }
    }

    public void create(Transport transport) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            TransportDao transportDao = daoFactory.createTransportDao(connection);
            transportDao.create(transport);
            connection.commit();
        }
    }

    public void update(Transport transport,int id) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            TransportDao transportDao = daoFactory.createTransportDao(connection);
            transportDao.update(transport, id);
            connection.commit();
        }
    }

    public void delete(int id) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            TransportDao transportDao = daoFactory.createTransportDao(connection);
            transportDao.delete(id);
            connection.commit();
        }
    }

}
