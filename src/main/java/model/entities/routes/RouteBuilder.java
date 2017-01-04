package model.entities.routes;

import model.entities.Driver;
import model.entities.SetOfStops;
import model.entities.Transport;

/**
 * Created by Dyvak on 22.12.2016.
 */
public class RouteBuilder {
    Driver driver;
    Transport transport;
    SetOfStops stops;

    public RouteBuilder buildDriver(Driver driver){
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

