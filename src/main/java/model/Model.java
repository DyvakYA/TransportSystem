package model;

import model.dao.DaoFactory;
import model.dao.TransportDao;
import model.entities.Transport;
import model.entities.enums.TransportType;

/**
 * Created by User on 17.03.2016.
 */
public class Model {

    // The Program logic
    public void userProcces() {

//        Role admin = new AdminRole();
//        Authorization a_authorization = new Authorization();
//        a_authorization.setRole(admin);
//        admin.access();

        //System.out.println( "Hello World!"   );

        DaoFactory factory = DaoFactory.getInstance();
        TransportDao dao = factory.createTransportDao();

//        System.out.println(dao.findByLogin("admin"));
//        System.out.println("==================================");
        System.out.println(dao.findByNumber("СМ8335КА"));
        System.out.println("==================================");
        System.out.println(dao.findById(1));
        System.out.println("==================================");
        Transport transport = new Transport.Builder()
                .setType(TransportType.TRAM)
                .setModel("MAN")
                .setNumber("RG1212FD")
                .build();
        System.out.println(transport);
        dao.create(transport);
        dao.delete(2);

        System.out.println(dao.findAll());

//        User user2 = dao.authentication("admin", 92670323);
        //System.out.println(user2);
//        System.out.println(bus);

//        UrbanDriver driver = new UrbanDriver(1, "nick", "back", 23);
//        driver.addCategory(DriveCategory.B);
//        driver.addCategory(DriveCategory.C);
//
//        Transport transport = new UrbanTransport(5, TransportType.BUS, "est", "1234") {
//        };
//
//        SetOfStops stops = new SetOfStops();
//        stops.addStopInSetOfStops(Stops.Kiyv);
//        stops.addStopInSetOfStops(Stops.Mogilev);
//
//        RoutePlan route = new RouteBuilder()
//                .buildDriver(driver)
//                .buildTransport(transport)
//                .buildSetOfStops(stops)
//                .build();
//        System.out.println(route);
    }
}





