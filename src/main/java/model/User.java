package model;
import java.sql.Connection;
import java.sql.SQLException;
public class User {

    private String name;
    private String email;
    private String Password;


    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String email, String name,String Password) {
        this.email = email;
        this.Password = Password;
        this.name = name;
    }

    public User() {
    // Constructeur vide pour les tests ou frameworks comme JUnit, Jackson, etc.
    }

}
