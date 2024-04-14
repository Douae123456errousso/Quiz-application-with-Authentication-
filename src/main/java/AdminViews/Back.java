package AdminViews;

import AdminViews.AboutUser;
import AdminViews.AddQuestionGUI;
import dao.UserDetailsGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Back {
    private JButton addQuestionButton, goBackButton, exitButton;

    public Back(String a) throws SQLException {
//        setTitle("Back");
//        setSize(400, 200);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        if(a.equals("admin")){
            new AboutAdmin();
        }else if(a.equals("user")) {
            new AboutUser().setVisible(true);
        }
//        else if (a.equals("a")) {
//            setLayout(new GridLayout(1, 3));
//            addQuestionButton = new JButton("Add Question");
//            addQuestionButton.addActionListener(this);
//            add(addQuestionButton);
//        } else {
//            setLayout(new GridLayout(1, 2));
//            goBackButton = new JButton("Go Back");
//            exitButton = new JButton("Exit");
//            goBackButton.addActionListener(this);
//            exitButton.addActionListener(this);
//            add(goBackButton);
//            add(exitButton);
//        }
//        setVisible(true);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        dispose();
//        if (e.getSource() == goBackButton) {
//            try {
//                new AboutUser();
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//        } else if (e.getSource() == addQuestionButton) {
//            new AddQuestionGUI();
//        } else if (e.getSource() == exitButton) {
//            System.exit(0);
//        }
//    }
}
