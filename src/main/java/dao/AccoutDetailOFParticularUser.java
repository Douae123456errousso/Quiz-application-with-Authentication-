package dao;
import Detail.accountDetail;
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

public class AccoutDetailOFParticularUser extends JFrame {
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public AccoutDetailOFParticularUser(String email) {
        setTitle("Your Score");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton BackButton = new JButton("Back"); // New button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(BackButton); // Add the new button

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        try {
            populateTable(email);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while fetching data.");
        }

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the Back method here
                dispose();
                new accountDetail(email);
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


}
