package model.entities.drivers;

import model.entities.enums.DriveCategory;
import model.entities.transports.Transport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dyvak on 16.12.2016.
 */
public class UrbanDriver implements Driver{

    private int id;
    private String name;
    private String lastName;
    int age;
    private List<DriveCategory> category = new ArrayList<>();
    private List<Transport> transports = new ArrayList<>();

    public UrbanDriver() {
    }

    public UrbanDriver(int id, String name, String lastName, int age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public void addCategory(DriveCategory cat) {
        category.add(cat);
    }

    @Override
    public void addTransports(Transport trans) {
        transports.add(trans);
    }

    public static class Builder{
        UrbanDriver instance = new UrbanDriver();

        public Builder setName(String name) {
            instance.name = name;
            return this;
        }

        public Builder setName(String name , boolean isNull) {
            if(!isNull) {
                instance.name = name;
            }else{
                instance.name = null;
            }
            return this;
        }

        public Builder setId(int id) {
            instance.id = id;
            return this;
        }

        public Driver build() {
            return instance;
        }
    }

    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<DriveCategory> getCategory() {
        return category;
    }

    public void setCategory(List<DriveCategory> category) {
        this.category = category;
    }

    public List getTransports() {
        return transports;
    }

    public void setTransports(List transports) {
        this.transports = transports;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", category=" + category +
                '}';
    }
}
