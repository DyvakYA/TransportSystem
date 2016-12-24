package model.entities.transports;

import model.entities.enums.TransportType;

/**
 * Created by Dyvak on 16.12.2016.
 */
public interface Transport {
    public Integer getId();
    public void setId(Integer id);
    public String getModel();
    public void setModel(String model);
    public String getNumber();
    public void setNumber(String number);
    public TransportType getType();
    public void setType(TransportType type);
}
