package views;

import Detail.accountDetail;
import model.UserScore;
import servies.SentScoreSelf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class View2 extends JFrame {
    public View2(UserScore userScore) {
        setTitle("Game Options");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JLabel titleLabel = new JLabel("Well Played");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));

        JButton accountDetailButton = new JButton("See details of your Account");
        accountDetailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new accountDetail(userScore.getEmail());
            }
        });
        buttonPanel.add(accountDetailButton);

        JButton languageButton = new JButton("Play again with another Language");
        languageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LanguageScreen(userScore.getEmail());
            }
        });
        buttonPanel.add(languageButton);

        JButton playAgainButton = new JButton("Play again with the same Language");
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new QuizNameScreen(userScore.getLang(), userScore.getEmail());
            }
        });
        buttonPanel.add(playAgainButton);

        JButton sentDetailButton = new JButton("Send Whole Detail to Self");
        sentDetailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SentScoreSelf(userScore.getEmail());
            }
        });
        buttonPanel.add(sentDetailButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

}
