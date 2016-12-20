package model.services;

import model.dao.BusDao;
import model.dao.DaoFactory;
import model.entities.transport.Bus;

import java.util.List;

/**
 * Created by Dyvak on 17.12.2016.
 */
public class BusService {
    private BusDao busDao = DaoFactory.getInstance().createBusDao();

    void setCityDao(BusDao cityDao) {
        this.busDao = cityDao;
    }

    public List<Bus> getAllBuses(){
        return busDao.findAll();
    }
}
