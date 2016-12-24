package model.entities.drivers;

import model.entities.enums.DriveCategory;
import model.entities.transports.Transport;

/**
 * Created by Dyvak on 24.12.2016.
 */
public interface Driver {
    public void addCategory(DriveCategory cat);
    public void addTransports(Transport trans);
    public String getName();
    public void setId(int anInt);
}
