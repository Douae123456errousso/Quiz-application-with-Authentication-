package views;

import Detail.accountDetail;
import model.UserScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginView extends JFrame implements ActionListener {
     String email;

    public LoginView(String email) throws SQLException {
        this.email = email;
        setTitle("Login View");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Let's Do some Thing interesting:");
        add(label);

        JButton playButton = createButton("Play", Color.BLACK);
        JButton scoreButton = createButton("See Score", Color.BLACK);
        JButton exitButton = createButton("Exit", Color.RED);

        add(playButton);
        add(scoreButton);
        add(exitButton);

        playButton.addActionListener(this);
        scoreButton.addActionListener(this);
        exitButton.addActionListener(this);

        setVisible(true);
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setForeground(color);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Play":
             new LanguageScreen(email);
                dispose();
                break;
            case "See Score":
                new accountDetail(email);
                dispose();
                break;
            case "Exit":
                System.exit(0);
                dispose();
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new LoginView("example@example.com");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
}
