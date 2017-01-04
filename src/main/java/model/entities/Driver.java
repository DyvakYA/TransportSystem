package model.entities;

import model.entities.enums.DriveCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dyvak on 16.12.2016.
 */
public class Driver {

    private int id;
    private String name;
    private String surname;
    int age;
    private List<DriveCategory> category = new ArrayList<>();
    private List<Transport> transports = new ArrayList<>();

    public Driver() {
    }

    public Driver(int id, String name, String lastName, int age) {
        this.id = id;
        this.name = name;
        this.surname = lastName;
        this.age = age;
    }

    public void addCategory(DriveCategory cat) {
        category.add(cat);
    }

    public void addTransports(Transport trans) {
        transports.add(trans);
    }

    public static class Builder{
        Driver instance = new Driver();

        public Builder setName(String name) {
            instance.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            instance.surname = surname;
            return this;
        }

        public Builder setAge(int age) {
            instance.age = age;
            return this;
        }

        public Driver build() {
            return instance;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", category=" + category +
                '}';
    }
}
