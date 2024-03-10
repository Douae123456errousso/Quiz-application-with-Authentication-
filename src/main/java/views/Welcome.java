package views;
import AdminViews.AView1;
import dao.UserDAO;
import model.User;
import servies.GenerateOTP;
import servies.SendOTPService;
import servies.UserServices;
import views.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class Welcome extends JFrame {
    private JTextField emailField;
    private JTextField nameField;
    private JPasswordField passwordField;
    private String name;

    private JPanel mainPanel;

    public void welcomeScreen() throws SQLException {
        setTitle("Welcome to the App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Signup");
        JButton exitButton = new JButton("Exit");

        loginButton.setPreferredSize(new Dimension(200, 40));
        signupButton.setPreferredSize(new Dimension(200, 40));
        exitButton.setPreferredSize(new Dimension(200, 40));

        loginButton.setBackground(Color.BLACK);
        signupButton.setBackground(Color.BLACK);
        exitButton.setBackground(Color.RED);

        loginButton.setForeground(Color.WHITE);
        signupButton.setForeground(Color.WHITE);
        exitButton.setForeground(Color.WHITE);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();
                login("Users");
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();
                try {
                    signUp();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mainPanel.add(loginButton);
        mainPanel.add(signupButton);
        mainPanel.add(exitButton);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void login(String name) {
        this.name = name;
        setTitle("Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel emailLabel = new JLabel("Email:");
        panel.add(emailLabel, gbc);

        gbc.gridy++;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);

        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel, gbc);

        gbc.gridy++;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    if (UserDAO.isExist(email, name)) {
                        if (UserDAO.isPassword(email, password, name)) {
                            if (name.equals("Users")) {
                                new LoginView(email);
                            } else {
                                new AView1();
                            }
                            dispose(); // Close the current window upon successful login
                        } else {
                            JOptionPane.showMessageDialog(Welcome.this, "Wrong Password", "Error", JOptionPane.ERROR_MESSAGE);
                            dispose(); // Close the current window upon successful login
                            login(name);
                        }
                    } else {
                        JOptionPane.showMessageDialog(Welcome.this, "User not Found", "Error", JOptionPane.ERROR_MESSAGE);
                        dispose(); // Close the current window upon successful login
                        login(name);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(loginButton, gbc);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void signUp() throws SQLException {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Name:");
        panel.add(nameLabel, gbc);

        gbc.gridy++;
        nameField = new JTextField(20);
        panel.add(nameField, gbc);

        gbc.gridy++;
        JLabel emailLabel = new JLabel("Email:");
        panel.add(emailLabel, gbc);

        gbc.gridy++;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);

        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel, gbc);

        gbc.gridy++;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(200, 40));
        signUpButton.setBackground(Color.BLACK);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String message = "Hey " + name + ", Your OTP to SignUp in the Quiz Game ";
                String genOTP = GenerateOTP.getOTP();
                SendOTPService.sentOTP(email, genOTP, message);

                String otp = JOptionPane.showInputDialog(Welcome.this, "Enter the OTP:");
                if (otp != null && otp.equals(genOTP)) {
                    User user = new User(email, name, password);
                    try {
                        int response = UserServices.saveUser(user);
                        if (response == 1) {
                            JOptionPane.showMessageDialog(Welcome.this, "User registered.\nLogin to Get Started.");
                            dispose();
                            new LoginView("Users");
                        } else {
                            JOptionPane.showMessageDialog(Welcome.this, "Failed to register user.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(Welcome.this, "Wrong OTP", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(signUpButton, gbc);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Welcome().welcomeScreen();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
