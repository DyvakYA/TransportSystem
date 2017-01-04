package model.entities.routes;

import model.entities.Driver;
import model.entities.SetOfStops;
import model.entities.Transport;

/**
 * Created by Dyvak on 24.12.2016.
 */
public interface RoutePlan {

    public void setDriver(Driver driver);
    public void setTransport(Transport transport);
    public void setStops(SetOfStops stops);
}
