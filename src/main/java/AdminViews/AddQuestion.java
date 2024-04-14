package AdminViews;

import db.Myconnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddQuestion extends JFrame implements ActionListener {
    private JTextField questionField, option1Field, option2Field, option3Field, answerField;
    private JButton addButton, backButton;
    private String tableName;

    public AddQuestion(String tableName) {
        this.tableName = tableName;
        setTitle("Add Question");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        JLabel questionLabel = new JLabel("Enter the question:");
        questionField = new JTextField();
        JLabel option1Label = new JLabel("Enter option 1:");
        option1Field = new JTextField();
        JLabel option2Label = new JLabel("Enter option 2:");
        option2Field = new JTextField();
        JLabel option3Label = new JLabel("Enter option 3:");
        option3Field = new JTextField();
        JLabel answerLabel = new JLabel("Enter the answer:");
        answerField = new JTextField();

        addButton = new JButton("Add Question");
        backButton = new JButton("Back");

        add(questionLabel);
        add(questionField);
        add(option1Label);
        add(option1Field);
        add(option2Label);
        add(option2Field);
        add(option3Label);
        add(option3Field);
        add(answerLabel);
        add(answerField);
        add(addButton);
        add(backButton);

        addButton.addActionListener(this);
        backButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                Connection connection = Myconnection.getConnection();
                PreparedStatement ps = connection.prepareStatement("INSERT INTO " + tableName + " (Question, Option1, Option2, Option3, Answer) VALUES (?, ?, ?, ?, ?)");

                String question = questionField.getText();
                String option1 = option1Field.getText();
                String option2 = option2Field.getText();
                String option3 = option3Field.getText();
                String answer = answerField.getText();

                ps.setString(1, question);
                ps.setString(2, option1);
                ps.setString(3, option2);
                ps.setString(4, option3);
                ps.setString(5, answer);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Question added successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add question.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                ps.close();
                connection.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backButton) {
            dispose();
            try {
                new Back("user");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void main(String[] args) {
        // Example usage:
//         SwingUtilities.invokeLater(() -> new AddQuestion("your_table_name"));
    }
}
