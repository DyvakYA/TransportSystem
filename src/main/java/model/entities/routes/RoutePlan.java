package model.entities.routes;

import model.entities.drivers.UrbanDriver;
import model.entities.setOfStops.SetOfStops;
import model.entities.transports.Transport;

/**
 * Created by Dyvak on 24.12.2016.
 */
public interface RoutePlan {

    public void setDriver(UrbanDriver driver);
    public void setTransport(Transport transport);
    public void setStops(SetOfStops stops);
}
