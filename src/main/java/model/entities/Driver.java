package model.entities;

/**
 * Created by Dyvak on 16.12.2016.
 */
public class Driver {

    private Integer id;
    private String name;
    private String surname;
    private int age;
    private int routeId;

    public Driver() {
    }

    public Driver(Integer id, String name, String lastName, int age,int routeId) {
        this.id = id;
        this.name = name;
        this.surname = lastName;
        this.age = age;
        this.routeId = routeId;
    }

    public Driver( String name, String surname, int age,int routeId) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.routeId = routeId;
    }

    public static class Builder{
        Driver instance = new Driver();

        public Builder setId(int id) {
            instance.id = id;
            return this;
        }

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

        public Builder setRouteId(int routeId) {
            instance.routeId = routeId;
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

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", routeId=" + routeId +
                '}';
    }
}
