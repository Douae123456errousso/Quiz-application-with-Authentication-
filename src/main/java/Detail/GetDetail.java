package Detail;

import servies.SentScoreSelf;
import views.LanguageScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class GetDetail extends JFrame implements ActionListener {
    private final JButton sendDetailButton;
    private final JButton playAgainButton;
    private final JButton exitButton,BackButton;
    private final String email;

    public GetDetail(String email) {
        this.email = email;

        setTitle("Get Detail");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        setLocationRelativeTo(null);
        sendDetailButton = new JButton("Sent Detail to Your Email");
        playAgainButton = new JButton("Play Again");
        BackButton = new JButton("Back");
        exitButton = new JButton("Exit");

        add(sendDetailButton);
        add(playAgainButton);
        add(exitButton);
        add(BackButton);

        sendDetailButton.addActionListener(this);
        playAgainButton.addActionListener(this);
        exitButton.addActionListener(this);
        BackButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        if (e.getSource() == sendDetailButton) {
            new SentScoreSelf(email);
        } else if (e.getSource() == playAgainButton) {
            new LanguageScreen(email);
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }else{
            new accountDetail(email);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GetDetail("kiranjeetkour144"));
    }
}
