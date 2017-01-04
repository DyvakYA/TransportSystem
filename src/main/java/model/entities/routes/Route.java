package model.entities.routes;

import model.entities.Driver;
import model.entities.SetOfStops;
import model.entities.Transport;

/**
 * Created by Dyvak on 22.12.2016.
 */
public class Route implements RoutePlan{

    Driver driver;
    Transport transport;
    SetOfStops stops;

    public Driver getDriver() {
        return driver;
    }

    @Override
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Transport getTransport() {
        return transport;
    }

    @Override
    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public SetOfStops getStops() {
        return stops;
    }

    public void setStops(SetOfStops stops) {
        this.stops = stops;
    }

    @Override
    public String toString() {
        return "Route{" +
                "driver = " + driver +
                ", transport = " + transport +
                ", stops = " + stops +
                '}';
    }
}
