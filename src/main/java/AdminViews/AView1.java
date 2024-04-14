package AdminViews;

import dao.UserDetailsGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class AView1 extends  JFrame{
    public AView1() throws SQLException {
        setTitle("Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton AboutAdminButton = new JButton("About Admin");
        AboutAdminButton.setBackground(Color.BLACK);
        AboutAdminButton.setForeground(Color.WHITE);
        AboutAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                try {
                    new AboutAdmin();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        // Add new Admin button


        JButton AboutUserButton = new JButton("About User ");
        AboutUserButton.setBackground(Color.BLACK);
        AboutUserButton.setForeground(Color.WHITE);
        AboutUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                try {
                    new AboutUser().setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        JButton Exit = new JButton(" Exit");
        Exit.setBackground(Color.RED);
        Exit.setForeground(Color.WHITE);
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                System.exit(0);
            }
        });



        panel.add(AboutAdminButton);
        panel.add(AboutUserButton);
        panel.add(Exit);

        add(panel);
        setVisible(true);

    }
  public static void main(String[ ] args) throws SQLException {
        new AView1();
  }
}

