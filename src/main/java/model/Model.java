package model;

import model.authorization.AdminRole;
import model.authorization.Authorization;
import model.authorization.Role;
import model.entities.drivers.UrbanDriver;
import model.entities.enums.DriveCategory;
import model.entities.enums.Stops;
import model.entities.enums.TransportType;
import model.entities.routes.RouteBuilder;
import model.entities.routes.RoutePlan;
import model.entities.setOfStops.SetOfStops;
import model.entities.transports.Transport;
import model.entities.transports.UrbanTransport;

/**
 * Created by User on 17.03.2016.
 */
public class Model {

    // The Program logic
    public void userProcces() {

        Role admin = new AdminRole();
        Authorization authorization = new Authorization();
        authorization.setRole(admin);
        admin.access();


        //System.out.println( "Hello World!"   );
//        BusService service = new BusService();
//
//        System.out.println(service.getAllBuses() );
//
//        DaoFactory factory = DaoFactory.getInstance();
//        BusDao dao = factory.createBusDao();
//
//        //System.out.println(dao.findAll());
//
//        Bus bus = new Bus.Builder().setNumber("1520").build();
//        dao.create(bus);
//        System.out.println(bus);

        UrbanDriver driver = new UrbanDriver(1, "nick", "back", 23);
        driver.addCategory(DriveCategory.B);
        driver.addCategory(DriveCategory.C);

        Transport transport = new UrbanTransport(5, TransportType.BUS, "est", "1234") {
        };

        SetOfStops stops = new SetOfStops();
        stops.addStopInSetOfStops(Stops.Kiyv);
        stops.addStopInSetOfStops(Stops.Mogilev);

        RoutePlan route = new RouteBuilder()
                .buildDriver(driver)
                .buildTransport(transport)
                .buildSetOfStops(stops)
                .build();
        System.out.println(route);
    }
}





