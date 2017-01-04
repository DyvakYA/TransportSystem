package model.a_authorization;

/**
 * Created by Dyvak on 24.12.2016.
 */
public class AdminRole implements Role {

    @Override
    public void access() {
        System.out.println("Admin");
    }
}
