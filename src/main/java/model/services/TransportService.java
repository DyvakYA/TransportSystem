package model.services;

import model.dao.DaoFactory;
import model.dao.TransportDao;
import model.entities.transports.Transport;

import java.util.List;

/**
 * Created by Dyvak on 17.12.2016.
 */
public class TransportService {
    private TransportDao transportDao = DaoFactory.getInstance().createTransportDao();

    void setCityDao(TransportDao transportDao) {
        this.transportDao = transportDao;
    }

    public List<Transport> getAllTransports(){
        return transportDao.findAll();
    }
}
