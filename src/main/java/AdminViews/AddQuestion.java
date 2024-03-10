package AdminViews;

import db.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddQuestion {
    public AddQuestion(String tableName){
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = Myconnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO " + tableName + " (Question, Option1, Option2, Option3, Answer) VALUES (?, ?, ?, ?, ?)");

            System.out.println("Enter the question:");
            String question = scanner.nextLine();

            System.out.println("Enter option 1:");
            String option1 = scanner.nextLine();

            System.out.println("Enter option 2:");
            String option2 = scanner.nextLine();

            System.out.println("Enter option 3:");
            String option3 = scanner.nextLine();

            System.out.println("Enter the answer:");
            String answer = scanner.nextLine();

            ps.setString(1, question);
            ps.setString(2, option1);
            ps.setString(3, option2);
            ps.setString(4, option3);
            ps.setString(5, answer);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Question added successfully!");
            } else {
                System.out.println("Failed to add question.");
            }

            ps.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
