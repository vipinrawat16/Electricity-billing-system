package electricity.biling.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class update_information extends JFrame implements ActionListener {
    JLabel nametext,meterText;
    JTextField addresstext,cityText,stateText,emailText,phoneText;
    String meter;
    JButton update,cancel;
    update_information(String meter){
        this.meter = meter;
        setBounds(400,150,777,450);
        getContentPane().setBackground(new Color(253, 253, 254));
        setLayout(null);

        JLabel heading = new JLabel("Update Customer Information");
        heading.setBounds(50,10,400,40);
        heading.setFont(new Font("serif",Font.BOLD,20));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(30,70,100,20);
        add(name);

        nametext = new JLabel("");
        nametext.setBounds(150,70,200,20);
        add(nametext);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30,110,100,20);
        add(meterNo);

        meterText = new JLabel("");
        meterText.setBounds(150,110,100,20);
        add(meterText);

        JLabel address = new JLabel("Address");
        address.setBounds(30,150,100,20);
        add(address);

        addresstext = new JTextField();
        addresstext.setBounds(150,150,200,20);
        add(addresstext);

        JLabel city = new JLabel("City");
        city.setBounds(30,190,100,20);
        add(city);

        cityText = new JTextField();
        cityText.setBounds(150,190,200,20);
        add(cityText);

        JLabel state = new JLabel("State");
        state.setBounds(30,230,100,20);
        add(state);

        stateText = new JTextField();
        stateText.setBounds(150,230,200,20);
        add(stateText);

        JLabel email = new JLabel("Email");
        email.setBounds(30,270,100,20);
        add(email);

        emailText = new JTextField();
        emailText.setBounds(150,270,200,20);
        add(emailText);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(30,310,100,20);
        add(phone);

        phoneText = new JTextField();
        phoneText.setBounds(150,310,200,20);
        add(phoneText);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from newcustomer where meter_no = '"+meter+"'");
            if (resultSet.next()) {
                nametext.setText(resultSet.getString("name"));
                meterText.setText(resultSet.getString("meter_no"));
                addresstext.setText(resultSet.getString("address"));
                cityText.setText(resultSet.getString("city"));
                stateText.setText(resultSet.getString("state"));
                emailText.setText(resultSet.getString("email"));
                phoneText.setText(resultSet.getString("phone_no"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        update = new JButton("Update");
        update.setBackground(new Color(33,106,145));
        update.setForeground(Color.WHITE);
        update.setBounds(50,360,120,25);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBackground(new Color(33,106,145));
        cancel.setForeground(Color.WHITE);
        update.setBounds(200,360,120,25);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon signicon = new ImageIcon(getClass().getResource("update.png"));
        Image signimg = signicon.getImage().getScaledInstance(400,350,Image.SCALE_FAST);
        ImageIcon signIcon2 = new ImageIcon(signimg);
        JLabel signLabel = new JLabel(signIcon2);
        signLabel.setBounds(380,20,350,350);
        add(signLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==update) {
            String saddress = addresstext.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String semail = emailText.getText();
            String sphone = phoneText.getText();

            try {
                database c = new database();
                c.statement.executeUpdate("update newcustomer set address = '"+saddress+"', city = '"+scity+"', state = '"+sstate+"', email = '"+semail+"', phone_no = '"+sphone+"' where meter_no = '"+meter+"'");

                JOptionPane.showMessageDialog(null,"User Information Updated Successfully");
                setVisible(false);
            }catch (Exception E) {
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new update_information("");
    }
}
