package echodraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupPage extends JPanel implements ActionListener {

    JPanel backgroundPanel = new JPanel();
    JTextField firstNameTxt = new JTextField();
    JTextField middleNameTxt = new JTextField();
    JTextField lastNameTxt = new JTextField();
    JTextField usernameTxt = new JTextField();
    JPasswordField passTxt = new JPasswordField();
    JPasswordField conPassTxt = new JPasswordField();
    JButton confirmBttn = new JButton("Confirm");
    JButton cancelBttn = new JButton("Cancel");
    private MainFrame mainFrame;

    public SetupPage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        Image backgroundImage = new ImageIcon(getClass().getResource("/echodraft/images/setup.png")).getImage();
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        
        backgroundPanel.add(firstNameTxt);
        firstNameTxt.setFont(new Font("font", Font.BOLD, 20));
        firstNameTxt.setForeground(Color.WHITE);
        firstNameTxt.setBounds(702, 245, 320, 40);
        firstNameTxt.setBackground(new Color(12, 23, 12, 32));
        firstNameTxt.setOpaque(false);

        backgroundPanel.add(middleNameTxt);
        middleNameTxt.setFont(new Font("font", Font.BOLD, 20));
        middleNameTxt.setForeground(Color.WHITE);
        middleNameTxt.setBounds(702, 355, 320, 40);
        middleNameTxt.setBackground(new Color(12, 23, 12, 32));
        middleNameTxt.setOpaque(false);
        
        backgroundPanel.add(lastNameTxt);
        lastNameTxt.setFont(new Font("font", Font.BOLD, 20));
        lastNameTxt.setForeground(Color.WHITE);
        lastNameTxt.setBounds(702, 465, 320, 40);
        lastNameTxt.setBackground(new Color(12, 23, 12, 32));
        lastNameTxt.setOpaque(false);
        
        backgroundPanel.add(usernameTxt);
        usernameTxt.setFont(new Font("font", Font.BOLD, 20));
        usernameTxt.setForeground(Color.WHITE);
        usernameTxt.setBounds(1083, 245, 320, 40);
        usernameTxt.setBackground(new Color(12, 23, 12, 32));
        usernameTxt.setOpaque(false);

        backgroundPanel.add(passTxt);
        passTxt.setFont(new Font("font", Font.BOLD, 20));
        passTxt.setForeground(Color.WHITE);
        passTxt.setBounds(1083, 355, 320, 40);
        passTxt.setBackground(new Color(12, 23, 12, 32));
        passTxt.setOpaque(false);
        
        backgroundPanel.add(conPassTxt);
        conPassTxt.setFont(new Font("font", Font.BOLD, 20));
        conPassTxt.setForeground(Color.WHITE);
        conPassTxt.setBounds(1083, 465, 320, 40);
        conPassTxt.setBackground(new Color(12, 23, 12, 32));
        conPassTxt.setOpaque(false);

        backgroundPanel.add(confirmBttn);
        confirmBttn.setBackground(Color.decode("#FFC343"));
        confirmBttn.setForeground(Color.decode("#F1FAEE"));
        confirmBttn.setFont(new Font("Impact", Font.PLAIN, 20));
        confirmBttn.setBounds(1280, 640, 150, 55);
        confirmBttn.addActionListener(this);
        
        backgroundPanel.add(cancelBttn);
        cancelBttn.setBackground(Color.decode("#D52246"));
        cancelBttn.setForeground(Color.decode("#F1FAEE"));
        cancelBttn.setFont(new Font("Impact", Font.PLAIN, 20));
        cancelBttn.setBounds(1150, 645, 100, 50);
        cancelBttn.addActionListener(this);

        setLayout(new BorderLayout());
        add(backgroundPanel, BorderLayout.CENTER);
    }

    public void reset() {
        firstNameTxt.setText("");
        middleNameTxt.setText("");
        lastNameTxt.setText("");
        usernameTxt.setText("");
        passTxt.setText("");
        conPassTxt.setText("");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancelBttn) {
            int x = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to EXIT account creation?", "WARNING",
                    JOptionPane.YES_NO_OPTION);
            if (x == 0) {
                mainFrame.showCard("login");
                reset();
            }
        }
        
        
        if (e.getSource()==confirmBttn) {
            String firstName = firstNameTxt.getText().trim();
            String middleName = middleNameTxt.getText().trim();
            String lastName = lastNameTxt.getText().trim();
            String username = usernameTxt.getText().trim();
            String password = passTxt.getText().trim();
            String conPass = conPassTxt.getText().trim();
            
            if (firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty()
                    || username.isEmpty() || password.isEmpty()|| conPass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            if (!password.equals(conPass)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.");
                return;
            }
            
            if (TeacherDatabase.usernameExists(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists. Please choose another.");
                return;
            }

            TeacherData newUser = new TeacherData(firstName, middleName, lastName, username, password);
            TeacherDatabase.addUser(newUser);
            JOptionPane.showMessageDialog(this, "Account created for " + newUser.getFullName());

            
            backgroundPanel.setVisible(false);
            mainFrame.showCard("login");
            reset();
        }
    }

}
