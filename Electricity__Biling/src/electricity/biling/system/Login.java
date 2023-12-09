package electricity.biling.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField TextField,passwordText;
    Choice LoginChoice;
    JButton loginbutton , cancelbutton , signupbutton;
    Login(){
        super("Login");
        getContentPane().setBackground(new Color(0xFF85C0CE, true));

        JLabel username = new JLabel("Username");
        username.setBounds(300,60,100,20);
        add(username);

        TextField = new JTextField();
        TextField.setBounds(400,60,150,20);
        add(TextField);

        JLabel password = new JLabel("Password");
        password.setBounds(300,100,100,20);
        add(password);

        passwordText = new JTextField();
        passwordText.setBounds(400,100,150,20);
        add(passwordText);

        JLabel loggin = new JLabel("Login In As");
        loggin.setBounds(300,140,100,20);
        add(loggin);

        LoginChoice = new Choice();
        LoginChoice.add("Admin");
        LoginChoice.add("Customer");
        LoginChoice.setBounds(400,140,150,20);
        add(LoginChoice);

        loginbutton = new JButton("Login");
        loginbutton.setBounds(330,180,100,20);
        loginbutton.addActionListener(this);
        add(loginbutton);

        cancelbutton = new JButton("Cancel");
        cancelbutton.setBounds(450,180,100,20);
        cancelbutton.addActionListener(this);
        add(cancelbutton);
        signupbutton = new JButton("Signup");
        signupbutton.setBounds(400,215,100,20);
        signupbutton.addActionListener(this);
        add(signupbutton);

        ImageIcon profileOne = new ImageIcon(getClass().getResource("/icon/splash/b.jpg"));
        Image profileTow = profileOne.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon fprofileOne = new ImageIcon(profileTow);
        JLabel profileLabel = new JLabel(fprofileOne);
        profileLabel.setBounds(35,30,250,250);
        add(profileLabel);

        setSize(700,400);
        setLocation(400,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginbutton) {
            String username = TextField.getText();
            String password = passwordText.getText();
            String user = LoginChoice.getSelectedItem();
            try {
                database E = new database();
                String query = "SELECT * FROM Signup WHERE username = ? AND password = ? AND usertype = ?";
                PreparedStatement preparedStatement = E.connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, user);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String meter = resultSet.getString("meter_no");
                    setVisible(false);
                    new main_class(user,meter);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == cancelbutton) {
            setVisible(false);
        } else if (e.getSource() == signupbutton) {
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
