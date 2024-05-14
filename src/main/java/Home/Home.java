package  Home;
import views.Welcome;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class Home extends JFrame {

    public Home() {
        setTitle("Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        // Load the image from the URL
        ImageIcon backgroundImage = loadImageFromURL("https://wallpaperaccess.com/full/2384069.jpg");

        // Check if the image was loaded successfully
        if (backgroundImage != null) {
            // Create a JLabel to hold the background image
            JLabel backgroundLabel = new JLabel(backgroundImage);
            backgroundLabel.setLayout(new BorderLayout());

            JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
            panel.setOpaque(false); // Make the panel transparent
            panel.setBorder(BorderFactory.createEmptyBorder(250, 250, 250, 250));

            JPanel headingPanel = new JPanel();
            headingPanel.setOpaque(false); // Make it transparent
            JLabel headingLabel = new JLabel("Welcome to Quiz");
            headingLabel.setFont(new Font("Arial", Font.BOLD, 58));
            headingLabel.setForeground(Color.DARK_GRAY);
            headingPanel.add(headingLabel);

            backgroundLabel.add(headingPanel, BorderLayout.NORTH);

            JButton adminButton = createRoundedButton("Admin");
            adminButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Close the current window
                    Welcome welcome = new Welcome();
                    welcome.login("Admin");
                }
            });

            JButton userButton = createRoundedButton("User");
            userButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Close the current window
                    Welcome welcome = new Welcome();
                    try {
                        welcome.welcomeScreen();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            JButton helpButton = createRoundedButton("Help");
            helpButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Close the current window
                }
            });

            JButton exitButton = createRoundedButton("Exit");
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Close the current window
                    System.exit(0);
                }
            });

            panel.add(adminButton);
            panel.add(userButton);
            panel.add(helpButton);
            panel.add(exitButton);

            // Add the panel to the background label
            backgroundLabel.add(panel, BorderLayout.CENTER);

            // Set the content pane of the JFrame to the background label
            setContentPane(backgroundLabel);
        } else {
            // If the image couldn't be loaded, display an error message
            JOptionPane.showMessageDialog(this, "Failed to load background image.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        setVisible(true);
    }

    private ImageIcon loadImageFromURL(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            return new ImageIcon(ImageIO.read(url));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    super.paintComponent(g);
                    return;
                }
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int width = getWidth();
                int height = getHeight();
                g2d.setColor(getBackground());
                g2d.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, 20, 20));
                g2d.setColor(getForeground());
                g2d.draw(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, 20, 20));
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Home();
            }
        });
    }
}