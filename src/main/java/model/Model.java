package model;

import model.dao.BusDao;
import model.dao.DaoFactory;
import model.entities.transport.Bus;
import model.services.BusService;

/**
 * Created by User on 17.03.2016.
 */
public class Model {

    // The Program logic
    public void run() {
        //System.out.println( "Hello World!"   );
        BusService service = new BusService();

        System.out.println(service.getAllBuses() );

        DaoFactory factory = DaoFactory.getInstance();
        BusDao dao = factory.createBusDao();

        //System.out.println(dao.findAll());

        Bus bus = new Bus.Builder().setNumber("1520").build();
        dao.create(bus);
        System.out.println(bus);
    }
}





