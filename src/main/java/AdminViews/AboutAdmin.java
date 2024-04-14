package AdminViews;


import dao.UserDetailsGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;



public class AboutAdmin extends JFrame {
    public AboutAdmin() throws SQLException  {
        // See Admin button
        setTitle("About Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 650);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton SeeadminButton = new JButton("See all Admin");
        SeeadminButton.setBackground(Color.BLACK);
        SeeadminButton.setForeground(Color.WHITE);
        SeeadminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                try {
                    SeeAllAdmin();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
       // Add new Admin button


        JButton NewAdminButton = new JButton("New Admin ");
        NewAdminButton.setBackground(Color.BLACK);
        NewAdminButton.setForeground(Color.WHITE);
        NewAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                new AddNewAdmin();
            }
        });
         // Delete Admin
        JButton DeleteAdmin = new JButton("Delete Admin");
        DeleteAdmin.setBackground(Color.BLACK);
        DeleteAdmin.setForeground(Color.WHITE);
        DeleteAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                new DeleteAdmin();
            }
        });
        // Button for Back
        JButton Back = new JButton(" Back");
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        Back.addActionListener(new ActionListener() {
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

        // For exit

        JButton Exit = new JButton(" Exit");
        Exit.setBackground(Color.BLACK);
        Exit.setForeground(Color.WHITE);
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                System.exit(0);
            }
        });



        panel.add(SeeadminButton);
        panel.add(NewAdminButton);
        panel.add(DeleteAdmin);
        panel.add(Back);
        panel.add(Exit);

        add(panel);
        setVisible(true);
    }
    public static void SeeAllAdmin() throws SQLException {
        UserDetailsGUI userDetailsGUI = new UserDetailsGUI("admin","admin");
        userDetailsGUI.setVisible(true);
    }
}
