package  views;
import dao.UserDAO;
import db.Myconnection;
import model.UserScore;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageScreen extends JFrame implements ActionListener {
     String email;

   public  LanguageScreen(String email) {
        this.email = email;

        setTitle("Language Selection");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a panel to hold the rules and language selection options
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create a panel for the rules
        JTextArea rulesTextArea = new JTextArea();
        rulesTextArea.setText(
                "######################################################################################################################################\n" +
                        "Rules:\n" +
                        "Name of the quiz should be unique\n" +
                        "Name should have less than 20 characters\n" + // Corrected character limit
                        "Name should be valuable\n" +
                        "######################################################################################################################################");
        rulesTextArea.setEditable(false);
        mainPanel.add(rulesTextArea, BorderLayout.NORTH);

        // Create a panel for language selection
        JPanel langSelectionPanel = new JPanel();
        langSelectionPanel.setLayout(new GridLayout(4, 1)); // Corrected grid layout

        JButton javaButton = new JButton("Java");
        javaButton.addActionListener(this);
        langSelectionPanel.add(javaButton);

        JButton jsButton = new JButton("JavaScript");
        jsButton.addActionListener(this);
        langSelectionPanel.add(jsButton);

        JButton webdevButton = new JButton("Web Development");
        webdevButton.addActionListener(this);
        langSelectionPanel.add(webdevButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        langSelectionPanel.add(exitButton);

        mainPanel.add(langSelectionPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String lang = "";
        switch (e.getActionCommand()) {
            case "Java":
                lang = "Java";
                break;
            case "JavaScript":
                lang = "JS";
                break;
            case "Web Development":
                lang = "Webdev";
                break;
            case "Exit":
                System.exit(0);
        }
        if (!lang.isEmpty()) {
            dispose();
            new QuizNameScreen(lang, email);
        }
    }

    public static void main(String[] args) {
        new LanguageScreen("kiranjeetkour144");
    }
}

class Quiz extends JFrame {
    public JLabel questionLabel;
    public JRadioButton option1Button;
    public JRadioButton option2Button;
    public JRadioButton option3Button;
    public JButton nextButton;
    public ButtonGroup buttonGroup;

    public ResultSet resultSet;
    public int score;
    public Connection connection;
    public String correctAnswer;
    public String name; // Added name attribute

    public Quiz(String lang, String name,String email) {
        this.name = name; // Store the name
        setTitle("Quiz");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1)); // Corrected grid layout

        // Initialize components
        questionLabel = new JLabel();
        option1Button = new JRadioButton();
        option2Button = new JRadioButton();
        option3Button = new JRadioButton();
        nextButton = new JButton("Next");

        // Add radio buttons to button group
        buttonGroup = new ButtonGroup();
        buttonGroup.add(option1Button);
        buttonGroup.add(option2Button);
        buttonGroup.add(option3Button);

        // Add components to frame
        add(new JLabel("Quiz Name: " + name)); // Added quiz name label
        add(questionLabel);
        add(option1Button);
        add(option2Button);
        add(option3Button);
        add(nextButton);

        // Add action listener to next button
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processAnswer();
                try {
                    if (resultSet.next()) {
                        displayQuestion();
                    } else {
                        endQuiz(email,lang);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Load questions from database
        try {
            connection = Myconnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + lang);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                displayQuestion();}
            else if(!resultSet.next()){
                    dispose();
                }
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public void displayQuestion() {
        try {
            String question = resultSet.getString("Question");
            correctAnswer = resultSet.getString("Answer");
            String option1 = resultSet.getString("Option1");
            String option2 = resultSet.getString("Option2");
            String option3 = resultSet.getString("Option3");

            questionLabel.setText(question);
            option1Button.setText(option1);
            option2Button.setText(option2);
            option3Button.setText(option3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processAnswer() {
        if (option1Button.isSelected() && option1Button.getText().equals(correctAnswer)) {
            score++;
        } else if (option2Button.isSelected() && option2Button.getText().equals(correctAnswer)) {
            score++;
        } else if (option3Button.isSelected() && option3Button.getText().equals(correctAnswer)) {
            score++;
        }
        buttonGroup.clearSelection();
    }

    public void endQuiz(String email,String Lang) {
        JOptionPane.showMessageDialog(this, "Quiz completed. Your final score is: " + score);
        try {
            UserScore userScore = new UserScore(email, name, score, Lang);
            UserDAO.saveScore(userScore);
            new View2(userScore);


            resultSet.close();
            connection.close();
//            Quiz.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dispose();
    }
}

class QuizNameScreen extends JFrame implements ActionListener {
    public JTextField nameTextField;
    public JButton submitButton;
    public String lang;
    public String email;

    public QuizNameScreen(String lang, String email) {
        this.lang = lang;
        this.email = email;

        setTitle("Quiz Name");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));

        JLabel nameLabel = new JLabel("Enter Name of the Quiz:");
        nameTextField = new JTextField();
        mainPanel.add(nameLabel);
        mainPanel.add(nameTextField);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        add(mainPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameTextField.getText();
            try {
                if (isNameCorrect(name, lang, email)) {
                    // Name is correct, continue to the quiz
                    dispose(); // Close the current screen
                    new Quiz(lang, name,email); // Open the quiz screen
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static boolean isNameCorrect(String name, String lang, String email) throws SQLException {
        if (name.isEmpty() || name.length() >= 20 || !UserDAO.isName(email, lang, name)) {
            JOptionPane.showMessageDialog(null, "Invalid Name");
            return false;
        }
        JOptionPane.showMessageDialog(null, "New name is: " + name);
        return true;
    }

    public static void main(String[] args) {
        new QuizNameScreen("Java", "kiranjeetkour144");
    }
}
