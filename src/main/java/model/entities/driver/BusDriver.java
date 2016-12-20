package model.entities.driver;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dyvak on 16.12.2016.
 */
public class BusDriver extends People implements Driver {

    private Long id;
    private People people;
    private String category;
    private int speed = 60;
    private Set busses = new HashSet();

    public BusDriver() {
    }

    public void setBusses(Set busses) {
        this.busses = busses;
    }
    public Set getBusses() {
        return busses;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String lastName) {
        this.lastName = lastName;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }
}
