package electricity.biling.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener {

    Choice SIgn;

    TextField metercusto,employee,usernameTEXT,NameTEXT,passwordTEXT;

    JButton create,back;
    Signup(){
        super("Signup Page");

        getContentPane().setBackground(new Color(0xF4F4F4));
        JLabel createAs = new JLabel("Create Account As");
        createAs.setBounds(30,50,125,20);
        add(createAs);

        SIgn = new Choice();
        SIgn.add("Admin");
        SIgn.add("Customer");
        SIgn.setBounds(170,50,125,20);
        add(SIgn);

        JLabel meterno = new JLabel("Meter Number");
        meterno.setBounds(30,100,125,20);
        meterno.setVisible(false);
        add(meterno);

        metercusto = new TextField();
        metercusto.setBounds(170,100,125,20);
        metercusto.setVisible(false);
        add(metercusto);

        JLabel employeeid = new JLabel("Employee ID");
        employeeid.setBounds(30,100,125,20);
        employeeid.setVisible(true);
        add(employeeid);

        employee = new TextField();
        employee.setBounds(170,100,125,20);
        employee.setVisible(true);
        add(employee);

        JLabel UserName = new JLabel("Username");
        UserName.setBounds(30,140,125,20);
        add(UserName);

        usernameTEXT =new TextField();
        usernameTEXT.setBounds(170,140,125,20);
        add(usernameTEXT);

        JLabel Name = new JLabel("Name");
        Name.setBounds(30,180,125,20);
        add(Name);

        NameTEXT =new TextField("");
        NameTEXT.setBounds(170,180,125,20);
        add(NameTEXT);

        metercusto.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    database c = new database();
                    ResultSet resultSet = c.statement.executeQuery("select * from signup where meter_no= '"+metercusto.getText()+"'");
                    if (resultSet.next()) {
                        NameTEXT.setText(resultSet.getString("name"));
                    }

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JLabel password = new JLabel("Password");
        password.setBounds(30,220,125,20);
        add(password);

        passwordTEXT =new TextField();
        passwordTEXT.setBounds(170,220,125,20);
        add(passwordTEXT);

        SIgn.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = SIgn.getSelectedItem();
                if (user.equals("Customer")) {
                    employee.setVisible(false);
                    employeeid.setVisible(false);
                    meterno.setVisible(true);
                    metercusto.setVisible(true);
                }else{
                    employee.setVisible(true);
                    employeeid.setVisible(true);
                    meterno.setVisible(false);
                    metercusto.setVisible(false);
                }
            }
        });

        create = new JButton("Create");
        create.setForeground(Color.WHITE);
        create.setBackground(new Color(51, 90, 140));
        create.setBounds(50,270,80,20);
        create.addActionListener(this);
        add(create);

        back = new JButton("Back");
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(51, 90, 140));
        back.setBounds(180,270,70,20);
        back.addActionListener(this);
        add(back);


        setSize(600,380);
        setLocation(500,200);
        setLayout(null);

        ImageIcon signicon = new ImageIcon(Signup.class.getResource("/icon/splash/d.png"));
        Image signimg = signicon.getImage().getScaledInstance(250,250,Image.SCALE_FAST);
        ImageIcon signIcon2 = new ImageIcon(signimg);
        JLabel signLabel = new JLabel(signIcon2);
        signLabel.setBounds(320,30,250,250);
        add(signLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            String signdata = SIgn.getSelectedItem();
            String username = usernameTEXT.getText();
            String name = NameTEXT.getText();
            String password = passwordTEXT.getText();
            String meter = metercusto.getText();

            try{
                database a = new database();
                String query = null;
                if (SIgn.equals("Admin")) {
                    query = "insert into Signup value('"+meter+"','"+username+"','"+name+"','"+password+"','"+signdata+"')";
                }else {
                    query = "update signup set username = '"+username+"', password = '"+password+"', usertype = '"+signdata+"' where meter_no = '"+meter+"'";
                }
                a.statement.executeUpdate(query);

                a.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Account Created");
                setVisible(true);
                a.close();
                new Login();

            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==back) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
