package AdminViews;

import servies.GenerateOTP;
import servies.SendOTPService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import static dao.AdminDAO.deleteAdmin;

public class DeleteAdmin extends JFrame implements ActionListener {
    private JTextField nameField, emailField, otpField;
    private JButton sendOTPButton, deleteAdminButton, backButton, exitButton;
    private String generatedOTP;

    public DeleteAdmin() {
        setTitle("Delete Admin");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Enter name:");
        nameField = new JTextField();
        JLabel emailLabel = new JLabel("Enter email:");
        emailField = new JTextField();
        JLabel otpLabel = new JLabel("Enter OTP:");
        otpField = new JTextField();

        sendOTPButton = new JButton("Send OTP");
        deleteAdminButton = new JButton("Delete Admin");
        backButton = new JButton("Back");
        exitButton = new JButton("Exit");

        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(otpLabel);
        add(otpField);
        add(sendOTPButton);
        add(deleteAdminButton);
        add(backButton);
        add(exitButton);

        sendOTPButton.addActionListener(this);
        deleteAdminButton.addActionListener(this);
        backButton.addActionListener(this);
        exitButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendOTPButton) {
            String name = nameField.getText();
            String email = emailField.getText();
            generatedOTP = GenerateOTP.getOTP();
            String message = ("Hey " + name + ", Warning: Here is the OTP to delete you as Admin: " + generatedOTP);
            SendOTPService.sentOTP(email, generatedOTP, message);
            JOptionPane.showMessageDialog(this, "OTP has been sent to the provided email.");
        } else if (e.getSource() == deleteAdminButton) {
            String enteredOTP = otpField.getText();
            if (enteredOTP.equals(generatedOTP)) {
                String email = emailField.getText();
                try {
                    deleteAdmin(email);
                    JOptionPane.showMessageDialog(this, "Admin has been deleted successfully.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error deleting admin: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Wrong OTP. Admin deletion failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backButton) {
            dispose();
            try {
                new AboutAdmin();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DeleteAdmin());
    }
}
