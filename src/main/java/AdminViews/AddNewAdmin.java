package AdminViews;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import static dao.AdminDAO.saveAdmin;
import static dao.UserDAO.isExist;

import servies.GenerateOTP;
import servies.SendOTPService;

public class AddNewAdmin extends JFrame implements ActionListener {
    private JTextField nameField, emailField, passwordField, otpField;
    private JButton sendOTPButton, addAdminButton, backButton, exitButton;
    private String generatedOTP;

    public AddNewAdmin() {
        setTitle("Add New Admin");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        JLabel nameLabel = new JLabel("Enter name:");
        nameField = new JTextField();
        JLabel emailLabel = new JLabel("Enter email:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Enter password:");
        passwordField = new JPasswordField();

        sendOTPButton = new JButton("Send OTP");
        backButton = new JButton("Back");
        exitButton = new JButton("Exit");
        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(sendOTPButton);
        add(backButton);
        add(exitButton);


        sendOTPButton.addActionListener(this);
         backButton.addActionListener(this);
         exitButton.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendOTPButton) {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();

            try {
                if (isExist(email, "admin")) {
                    JOptionPane.showMessageDialog(this, "Admin with this email already exists. Please use a different email.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                generatedOTP = GenerateOTP.getOTP();
                String message = "Hey " + name + ",\nHere is your OTP to add as an admin in the Quiz Game: " + generatedOTP;
                SendOTPService.sentOTP(email, generatedOTP, message);

                JPanel otpPanel = new JPanel(new GridLayout(2, 1));
                otpField = new JTextField();
                JButton addAdminButton = new JButton("Add Admin");

                otpPanel.add(new JLabel("Enter OTP:"));
                otpPanel.add(otpField);

                otpPanel.add(addAdminButton);
                add(otpPanel);

                addAdminButton.addActionListener(this);

                validate();
                repaint();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error checking admin existence or sending OTP: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                dispose();
                new AddNewAdmin();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error sending OTP. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
                dispose();
                new AddNewAdmin();
            }
        } else if (e.getActionCommand().equals("Add Admin")) {

            String otp = otpField.getText();
            if (otp.equals(generatedOTP)) {
                try {
                    String name = nameField.getText();
                    String email = emailField.getText();
                    String password = passwordField.getText();

                    saveAdmin(name, email, password);
                    JOptionPane.showMessageDialog(this, "Admin saved successfully");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving admin: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Wrong OTP", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if(e.getSource() == backButton){
            dispose();
            try {
                new AboutAdmin();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }else{
            dispose();
            try {
                new AView1();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddNewAdmin());
    }
}
