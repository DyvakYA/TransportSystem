package model.entities.driver;

/**
 * Created by Dyvak on 16.12.2016.
 */
public class People {

    String name;
    String lastName;
    int age;

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
