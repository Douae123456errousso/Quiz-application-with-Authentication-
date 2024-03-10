package servies;

import dao.UserDAO;
import model.User;

import java.sql.SQLException;


public class UserServices{
    public static int saveUser(User user) {
        System.out.println(user.getEmail());
        try {
            if(UserDAO.isExist(user.getEmail(),"Users")) {
                System.out.println("User with this email already exists.");
                return -1; // User with the same email already exists
            } else {
                int result = UserDAO.saveUser(user);
                if (result > 0) {
                    System.out.println("Successfull save");
                    return 1; // Successful save
                } else {
                    return -2; // Failed to save for some reason
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -3; // Error occurred
        }
    }

}
