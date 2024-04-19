package Home;
import views.Welcome;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Home extends JFrame {

    public Home() {
        setTitle("Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton adminButton = new JButton("Admin");
        adminButton.setBackground(Color.BLACK);
        adminButton.setForeground(Color.WHITE);
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                Welcome welcome = new Welcome();
                welcome.login("Admin");
            }
        });

        JButton userButton = new JButton("User");
        userButton.setBackground(Color.BLACK);
        userButton.setForeground(Color.WHITE);
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                Welcome welcome = new Welcome();
                try {
                    welcome.welcomeScreen();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton help = new JButton("Help");
        help.setBackground(Color.BLUE);
        help.setForeground(Color.WHITE);

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window

            }
        });


        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                System.exit(0);
            }
        });

        panel.add(adminButton);
        panel.add(userButton);
        panel.add(help);
        panel.add(exitButton);


        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Home();
            }
        });
    }
}
