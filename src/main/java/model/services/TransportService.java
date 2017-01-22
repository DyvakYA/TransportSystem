package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.TransportDao;
import model.entities.Transport;

import java.util.List;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class TransportService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final TransportService INSTANCE = new TransportService();
    }

    public static TransportService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Transport> getAllTransports() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            TransportDao transportDao = daoFactory.createTransportDao(connection);
            return transportDao.findAll();
        }
    }

    public void createTransport(Transport transport) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            TransportDao transportDao = daoFactory.createTransportDao(connection);
            transportDao.create(transport);
        }
    }

    public void updateTransport(Transport transport,int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            TransportDao transportDao = daoFactory.createTransportDao(connection);
            transportDao.update(transport, id);
        }
    }

    public void deleteTransport(int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            TransportDao transportDao = daoFactory.createTransportDao(connection);
            transportDao.delete(id);
        }
    }

}
