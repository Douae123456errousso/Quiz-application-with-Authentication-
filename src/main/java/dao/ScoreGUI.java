package dao;
import db.Myconnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreGUI extends JFrame {
    private final JTextField emailField;
    private final DefaultTableModel tableModel;

    public ScoreGUI() {
        setTitle("Total Score GUI");
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel emailLabel = new JLabel("Enter Email:");
        emailField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton BackButton = new JButton("Back"); // New button
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);
        inputPanel.add(searchButton);
        inputPanel.add(BackButton); // Add the new button

        tableModel = new DefaultTableModel();
        JTable resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                if (!email.isEmpty()) {
                    try {
                        populateTable(email);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(ScoreGUI.this, "An error occurred while fetching data.");
                    }
                } else {
                    JOptionPane.showMessageDialog(ScoreGUI.this, "Please enter an email.");
                }
            }
        });


    }

    private void populateTable(String email) throws SQLException {
        Connection connection = Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM score WHERE Email = ?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        // Clear previous data
        tableModel.setRowCount(0);

        // Add columns to table
        tableModel.setColumnIdentifiers(new String[]{"Name", "Score", "Language"});

        // Add rows to table
        while (rs.next()) {
            String name = rs.getString("Name");
            int score = rs.getInt("Score");
            String lang = rs.getString("Lang");
            tableModel.addRow(new Object[]{name, score, lang});
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ScoreGUI().setVisible(true);
            }
        });
    }
}
