package AdminViews;

import Excel_sheet.CreateExcel;
import Excel_sheet.ExcelDAO;
import dao.ScoreGUI;
import dao.UserDetailsGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;


import static dao.UserDAO.TotalScore;
public class AboutUser extends JFrame {
    public AboutUser()  throws SQLException  {
        setTitle("User Detail Application");
        setSize(400, 300);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));



        JButton userDetailButton = new JButton("User Details");
        JButton detailOfUserButton = new JButton("Detail of Any User");
        JButton addQuizButton = new JButton("Add Question Quiz");
        JButton addUserButton = new JButton("Add User detail in the Excel sheet");
        JButton backButton = new JButton("Back");
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);

        add(userDetailButton);
        add(detailOfUserButton);
        add(addQuizButton);
        add(addUserButton);
        add(backButton);
        add(exitButton);

        userDetailButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserDetailsGUI userDetailsGUI = new UserDetailsGUI("users","user");
                userDetailsGUI.setVisible(true);
            }
        });

        detailOfUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
//
                new ScoreGUI().setVisible(true);
            }
        });

        addQuizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AddQuestionGUI();
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dispose();
                    Excel();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dispose();
                     new AView1();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }


    public void Excel() throws SQLException {
        new CreateExcel();
    }

}

