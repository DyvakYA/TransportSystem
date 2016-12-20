package model.entities.transport;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dyvak on 16.12.2016.
 */
public class Bus implements Transport {

    private int id;
    private String number;
    private Set drivers = new HashSet();
    private Long route_id;

    public Bus() {
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setDrivers(Set drivers) {
        this.drivers = drivers;
    }
    public void setRoute_id(Long route_id) {
        this.route_id = route_id;
    }
    public int getId() {
        return id;
    }
    public String getNumber() {
        return number;
    }
    public Set getDrivers() {
        return drivers;
    }
    public Long getRoute_id() {
        return route_id;
    }

    @Override
    public void go() {

    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bus bus = (Bus) o;

        if (id != bus.id) return false;
        if (drivers != null ? !drivers.equals(bus.drivers) : bus.drivers != null) return false;
        if (number != null ? !number.equals(bus.number) : bus.number != null) return false;
        if (route_id != null ? !route_id.equals(bus.route_id) : bus.route_id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (drivers != null ? drivers.hashCode() : 0);
        result = 31 * result + (route_id != null ? route_id.hashCode() : 0);
        return result;
    }

    public static class Builder{
        Bus instance = new Bus();

        public Builder setNumber(String number) {
            instance.number = number;
            return this;
        }

        public Builder setNumber(String number , boolean isNull) {
            if(!isNull) {
                instance.number = number;
            }else{
                instance.number = null;
            }
            return this;
        }

        public Builder setId(int id) {
            instance.id = id;
            return this;
        }

        public Bus build() {
            return instance;
        }
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", drivers=" + drivers +
                ", route_id=" + route_id +
                '}'+"\n";
    }
}

