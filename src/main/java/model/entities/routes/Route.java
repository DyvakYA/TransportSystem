package model.entities.routes;

import model.entities.drivers.UrbanDriver;
import model.entities.setOfStops.SetOfStops;
import model.entities.transports.Transport;

/**
 * Created by Dyvak on 22.12.2016.
 */
public class Route implements RoutePlan{

    UrbanDriver driver;
    Transport transport;
    SetOfStops stops;

    public UrbanDriver getDriver() {
        return driver;
    }

    @Override
    public void setDriver(UrbanDriver driver) {
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
