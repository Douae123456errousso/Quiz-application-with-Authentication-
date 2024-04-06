package dao;

import AdminViews.AView1;
import db.Myconnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsGUI extends JFrame {
    private JPanel mainPanel;
    private JTable tableComponent;

    public UserDetailsGUI(String tableName) {
        setTitle("User Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        String[] columnNames = {"Name", "Email"};
        Object[][] data = getUserDetails(tableName);
        tableComponent = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(tableComponent);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Create a panel for buttons

        JButton back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                try {
                    new AView1();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton exit = new JButton("Exit");
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.WHITE);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                System.exit(0);
            }
        });

        buttonPanel.add(back);
        buttonPanel.add(exit);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel below the table

        setVisible(true);
    }

    public Object[][] getUserDetails(String tableName) {
        Object[][] data = null;
        try {
            Connection connection = Myconnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from " + tableName, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = ps.executeQuery();

            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            data = new Object[rowCount][2];
            int row = 0;
            while (rs.next()) {
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                data[row][0] = name;
                data[row][1] = email;
                row++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;    }


}
