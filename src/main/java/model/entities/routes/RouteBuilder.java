package model.entities.routes;

import model.entities.drivers.UrbanDriver;
import model.entities.setOfStops.SetOfStops;
import model.entities.transports.Transport;

/**
 * Created by Dyvak on 22.12.2016.
 */
public class RouteBuilder {
    UrbanDriver driver;
    Transport transport;
    SetOfStops stops;

    public RouteBuilder buildDriver(UrbanDriver driver){
            this.driver=driver;
            return this;
        }

    public RouteBuilder buildTransport(Transport transport){
            this.transport = transport;
            return this;
        }

    public RouteBuilder buildSetOfStops(SetOfStops stops){
            this.stops = stops;
            return this;
        }

    public Route build(){
            Route route = new Route();
                route.setDriver(driver);
                route.setTransport(transport);
                route.setStops(stops);
        return route;
        }
}

