package Detail;
import dao.AccoutDetailOFParticularUser;
import dao.LanguageScoreGUI;
import views.Welcome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class accountDetail extends JFrame implements ActionListener {
    private final JButton detailButton;
    private final JButton fullDetailButton;
    private final JButton exitButton;
    private JButton backButton;
    private final String email;

    public accountDetail(String email) {
        this.email = email;

        setTitle("Account Detail");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));
        setLocationRelativeTo(null);
        detailButton = new JButton("Detail of Particular Language");
        fullDetailButton = new JButton("Get Whole Detail");
        backButton = new JButton("Back");
        exitButton = new JButton("Exit");

        add(detailButton);
        add(fullDetailButton);
        add(backButton);
        add(exitButton);

        detailButton.addActionListener(this);
        fullDetailButton.addActionListener(this);
        exitButton.addActionListener(this);
        backButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        if (e.getSource() == detailButton) {
            dispose();
            showParticularFieldDetail();
        } else if (e.getSource() == fullDetailButton) {
            try {
                dispose();
                showFullDetail();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }else {
            dispose();
           new Welcome();
        }
    }

    private void showParticularFieldDetail() {
        Object[] options = {"Java", "JavaScript", "Web Development", "Back","Exit"};
        int choice = JOptionPane.showOptionDialog(this,
                "Select Language:",
                "Language Selection",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if (choice != JOptionPane.CLOSED_OPTION) {
            String language;
            switch (choice) {
                case 0:
                    language = "Java";
                    break;
                case 1:
                    language = "JS";
                    break;
                case 2:
                    language = "WebDev";
                    break;
                case 3:
                    new accountDetail(email);
                default:
                    return; // Exit if "Exit" is selected
            }
            new LanguageScoreGUI(email, language);
//                showGoBackOption();
        }
    }

    private void showFullDetail() throws SQLException {

        AccoutDetailOFParticularUser AU = new AccoutDetailOFParticularUser("kiranjeetkour144");
        AU.setVisible(true);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new accountDetail("kiranjeetkour144"));
    }
}
