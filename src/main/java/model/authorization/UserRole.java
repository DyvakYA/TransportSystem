package model.authorization;

/**
 * Created by Dyvak on 24.12.2016.
 */
public class UserRole implements Role {

    @Override
    public void access() {
        System.out.println("User");
    }
}
