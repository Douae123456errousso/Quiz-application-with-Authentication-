package AdminViews;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class AddQuestionGUI extends JFrame implements ActionListener {
    private JButton javaButton;
    private JButton jsButton;
    private final JButton webdevButton;
    private final JButton backButton;
    private JTextField tableField;

    public AddQuestionGUI() {
        setTitle("Add Question");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        javaButton = new JButton("Java");
        jsButton = new JButton("JS");
        webdevButton = new JButton("Webdev");
        backButton = new JButton("Back");



        add(javaButton);
        add(jsButton);
        add(webdevButton);
        add(backButton);

        javaButton.addActionListener(this);
        jsButton.addActionListener(this);
        webdevButton.addActionListener(this);
        backButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        if (e.getSource() == javaButton) {
            new AddQuestion("Java");
        } else if (e.getSource() == jsButton) {
            new AddQuestion("js");
        } else if (e.getSource() == webdevButton) {
            new AddQuestion("webdev");
        } else if (e.getSource() == backButton) {
            try {
                new Back("user");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public static void main(String[] args) {
        new AddQuestionGUI();
    }

}

