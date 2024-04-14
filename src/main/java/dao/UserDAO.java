package dao;

import db.Myconnection;
import model.User;
import model.UserScore;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserDAO {
    public static boolean isExist(String email,String User)throws SQLException {
        Connection connection =Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from "+User);
        ResultSet rs =  ps.executeQuery();
       while (rs.next()){
           String e = rs.getString("email");
           if(e.equals(email)) {
               return true;
           }
       }
       return false;

    }

    public static boolean isPassword(String email,String Password,String User)throws SQLException {
        Connection connection =Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from "+User);
        ResultSet rs =  ps.executeQuery();
        while (rs.next()){
           String e = rs.getString("Password");
           String f = rs.getString("email");
           if(e.equals(Password)&&f.equals(email)) {
               return true;
           }
        }
      return false;

    }

    public static boolean isName(String email,String Lang,String Name)throws SQLException {
        Connection connection =Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from score");
        ResultSet rs =  ps.executeQuery();
        while (rs.next()) {
            String f = rs.getString("Email");
            String j = rs.getString("Lang");
            String k = rs.getString("Name");
            if (f.equals(email) && j.equals(Lang) && k.equals(Name)) {

                return false;
            }
        }

        return true;
    }


    public static int saveUser(User user) throws SQLException{
        Connection connection = Myconnection.getConnection();
        PreparedStatement ps;
        ps = connection.prepareStatement("insert into users(name,email,password) values(?,?,?) ");
        ps.setString(1,user.getName());
        ps.setString(2,user.getEmail());
        ps.setString(3,user.getPassword());
        return ps.executeUpdate();

    }
    public static int saveScore(UserScore userScore) throws SQLException{
        Connection connection = Myconnection.getConnection();
        PreparedStatement ps;
        ps = connection.prepareStatement("insert into score(Name,Email,Lang,Score) values(?,?,?,?) ");
        ps.setString(1, userScore.getName());
        ps.setString(2, userScore.getEmail());
        ps.setString(3, userScore.getLang());
        ps.setInt(4,userScore.getScore());
        return ps.executeUpdate();
    }


    public static String TotalScore(String Email) throws SQLException {
        Connection connection = Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from score");
        ResultSet rs = ps.executeQuery();

        StringBuilder table = new StringBuilder();
        table.append("__________________________________\n");
        table.append(String.format("|%-12s | %-8s|%-7s%n", "Name", "Score", "Lang    |"));
        table.append("|--------------------------------|\n");

        while (rs.next()) {
            String f = rs.getString("Email");
            String g = rs.getString("Lang");
            String i = rs.getString("Name");
            int h = rs.getInt("Score");

            if (f.equals(Email)) {
                table.append(String.format("|%-12s | %-8d| %-7s|\n", i, h, g));
            }
        }
        table.append("----------------------------------\n");

        return table.toString();
    }


}

