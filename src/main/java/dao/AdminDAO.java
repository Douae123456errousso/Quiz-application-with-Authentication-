package dao;


import db.Myconnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDAO{

        public static int saveAdmin(String name,String email ,String password) throws SQLException {
            Connection connection = Myconnection.getConnection();
            PreparedStatement ps;
            ps = connection.prepareStatement("insert into admin(email,password,name) values(?,?,?) ");
            ps.setString(1,email);
            ps.setString(2,password);
            ps.setString(3,name);
            return ps.executeUpdate();
    }
    public static int deleteAdmin(String email) throws SQLException {
        Connection connection = Myconnection.getConnection();
        PreparedStatement ps = null;
        int rowsAffected = 0;
        try {
            String query = "DELETE FROM admin WHERE email=?";
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rowsAffected = ps.executeUpdate();
        } finally {
            // Close resources in a finally block
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return rowsAffected;
    }

}

// Create a class for save and delete admin
// Generate OTP class edit new function
