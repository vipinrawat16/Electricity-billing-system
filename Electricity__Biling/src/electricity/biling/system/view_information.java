package electricity.biling.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;

public class view_information extends JFrame implements ActionListener {
    String view;
    JButton cancel;
    view_information(String view){
        this.view = view;
        setBounds(350,150,850,650);
        getContentPane().setBackground(new Color(209, 224, 254));
        setLayout(null);

        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(250,0,500,40);
        heading.setFont(new Font("serif",Font.BOLD,20));
        add(heading);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(70,80,100,20);
        add(nameLabel);

        JLabel nameLabelText = new JLabel("");
        nameLabelText.setBounds(200,80,150,20);
        add(nameLabelText);

        JLabel meterno = new JLabel("Meter No");
        meterno.setBounds(70,140,100,20);
        add(meterno);

        JLabel meternoText = new JLabel("");
        meternoText.setBounds(200,140,150,20);
        add(meternoText);

        JLabel address = new JLabel("Address");
        address.setBounds(70,200,100,20);
        add(address);

        JLabel addressText = new JLabel("");
        addressText.setBounds(200,200,150,20);
        add(addressText);

        JLabel city = new JLabel("City");
        city.setBounds(70,260,100,20);
        add(city);

        JLabel cityText = new JLabel("");
        cityText.setBounds(200,260,150,20);
        add(cityText);

        JLabel state = new JLabel("State");
        state.setBounds(500,80,100,20);
        add(state);

        JLabel stateText = new JLabel("");
        stateText.setBounds(600,80,150,20);
        add(stateText);

        JLabel email = new JLabel("Email");
        email.setBounds(500,140,100,20);
        add(email);

        JLabel emailText = new JLabel("");
        emailText.setBounds(600,140,150,20);
        add(emailText);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(500,200,100,20);
        add(phone);

        JLabel phoneText = new JLabel("");
        phoneText.setBounds(600,200,150,20);
        add(phoneText);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from newcustomer where meter_no = '"+view+"'");
            if (resultSet.next()) {
                nameLabelText.setText(resultSet.getString("name"));
                meternoText.setText(resultSet.getString("meter_no"));
                addressText.setText(resultSet.getString("address"));
                cityText.setText(resultSet.getString("city"));
                stateText.setText(resultSet.getString("state"));
                emailText.setText(resultSet.getString("email"));
                phoneText.setText(resultSet.getString("phone_no"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        cancel = new JButton("Cancel");
        cancel.setBackground(new Color(203, 173, 122));
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(330,300,120,25);
        cancel.addActionListener(this);
        add(cancel);

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setBackground(new Color(0xFFFFFF));

        URL imageURL = getClass().getClassLoader().getResource("icon/splash/ph.png");
        ImageIcon i1 = new ImageIcon(imageURL);
        Image i2 = i1.getImage().getScaledInstance(630,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLable = new JLabel(i3);
        imgLable.setBounds(100,320,600,300);
        add(imgLable);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new view_information("");
    }
}
