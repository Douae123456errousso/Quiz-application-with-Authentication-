package dao;

import Detail.GetDetail;
import db.Myconnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LanguageScoreGUI extends JFrame {
    private JTable table;

    public LanguageScoreGUI(String email, String lang) {
        setTitle("Language Score");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        String[] columnNames = {"Name", "Score"};
        Object[][] data = getLanguageScores(email, lang);
        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        // Create a panel to hold the button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            dispose(); // Close the current window
            // Open a new GetDetail window
            new GetDetail(email);
        });
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private Object[][] getLanguageScores(String email, String lang) {
        Object[][] data = null;
        try {
            Connection connection = Myconnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT Name, Score FROM score WHERE Email = ? AND Lang = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, email);
            ps.setString(2, lang);
            ResultSet rs = ps.executeQuery();

            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            data = new Object[rowCount][2];
            int row = 0;
            while (rs.next()) {
                String name = rs.getString("Name");
                int score = rs.getInt("Score");
                data[row][0] = name;
                data[row][1] = score;
                row++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LanguageScoreGUI("kiranjeetkour144", "Java");
        });
    }
}
