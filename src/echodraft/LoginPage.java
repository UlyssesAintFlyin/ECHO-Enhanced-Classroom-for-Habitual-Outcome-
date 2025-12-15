package echodraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel implements ActionListener {

    JPanel backgroundPanel = new JPanel();
    JTextField userTxt = new JTextField();
    JPasswordField passTxt = new JPasswordField();
    JButton confirmBttn = new JButton("Confirm");
    JButton setupBttn = new JButton("Setup Account");
    private MainFrame mainFrame;

    public LoginPage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        Image backgroundImage = new ImageIcon(getClass().getResource("/echodraft/images/login.png")).getImage();
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        backgroundPanel.add(userTxt);
        userTxt.setFont(new Font("font", Font.BOLD, 20));
        userTxt.setForeground(Color.WHITE);
        userTxt.setBounds(853, 275, 400, 40);
        userTxt.setBackground(new Color(12, 23, 12, 32));
        userTxt.setOpaque(false);

        backgroundPanel.add(passTxt);
        passTxt.setFont(new Font("font", Font.BOLD, 20));
        passTxt.setForeground(Color.WHITE);
        passTxt.setBounds(853, 385, 400, 40);
        passTxt.setBackground(new Color(12, 23, 12, 32));
        passTxt.setOpaque(false);

        backgroundPanel.add(confirmBttn);
        confirmBttn.setBackground(Color.decode("#FFC343"));
        confirmBttn.setForeground(Color.decode("#F1FAEE"));
        confirmBttn.setFont(new Font("Impact", Font.PLAIN, 25));
        confirmBttn.setBounds(1180, 560, 120, 50);
        confirmBttn.addActionListener(this);
        
        backgroundPanel.add(setupBttn);
        setupBttn.setBackground(Color.decode("#BA66A4"));
        setupBttn.setForeground(Color.decode("#F1FAEE"));
        setupBttn.setFont(new Font("Impact", Font.PLAIN, 20));
        setupBttn.setBounds(100, 700, 180, 50);
        setupBttn.addActionListener(this);

        setLayout(new BorderLayout());
        add(backgroundPanel, BorderLayout.CENTER);
    }

    public void reset() {
        userTxt.setText("");
        passTxt.setText("");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==setupBttn) {
            mainFrame.showCard("setup");
            reset();
        }
        
        if (e.getSource() == confirmBttn) {
            String username = userTxt.getText().trim();
            String password = passTxt.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all the fields.");
                return;
            }

            if (!TeacherDatabase.usernameExists(username)) {
                JOptionPane.showMessageDialog(this, "Username not found. Please sign up first.");
                return;
            }

            TeacherData user = TeacherDatabase.getUser(username);
            if (!user.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(this, "Incorrect password. Please try again.");
                return;
            }

            JOptionPane.showMessageDialog(this, "Login Successful! \nWelcome, " + user.getFirstName() + " (" + user.getUsername() + ")" + "!");
            reset();
            mainFrame.setDashboard(user);

        }

    }
}

