package electricity.biling.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;
import java.awt.TextField;


public class newCustomer extends JFrame implements ActionListener {
    JLabel heading , meternumText, customerName, meterNum, address, city, state, email, phone;
    JButton next, cancel;
    TextField nameText, addressText, cityText, stateText, emailText, phoneText;

    newCustomer() {
        super("New Customer");
        setSize(700, 500);
        setLocation(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0xFCA000));
        add(panel);

        heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        customerName = new JLabel("New Customer");
        customerName.setBounds(50, 80, 100, 20);
        panel.add(customerName);

        nameText = new TextField("");
        nameText.setBounds(150, 80, 200, 20);
        panel.add(nameText);

        meterNum = new JLabel("Meter Number");
        meterNum.setBounds(50, 120, 100, 20);
        panel.add(meterNum);

        meternumText = new JLabel("");
        meternumText.setBounds(150, 120, 200, 20);
        panel.add(meternumText);

        Random ran = new Random();
        long number = ran.nextLong() % 100000;
        meternumText.setText("" + Math.abs(number));

        address = new JLabel("Address");
        address.setBounds(50, 160, 100, 20);
        panel.add(address);

        addressText = new TextField("");
        addressText.setBounds(150, 160, 200, 20);
        panel.add(addressText);

        city = new JLabel("City");
        city.setBounds(50, 200, 100, 20);
        panel.add(city);

        cityText = new TextField("");
        cityText.setBounds(150, 200, 200, 20);
        panel.add(cityText);

        state = new JLabel("State");
        state.setBounds(50, 240, 100, 20);
        panel.add(state);

        stateText = new TextField("");
        stateText.setBounds(150, 240, 200, 20);
        panel.add(stateText);

        email = new JLabel("Email");
        email.setBounds(50, 280, 100, 20);
        panel.add(email);

        emailText = new TextField("");
        emailText.setBounds(150, 280, 200, 20);
        panel.add(emailText);

        phone = new JLabel("Phone");
        phone.setBounds(50, 320, 100, 20);
        panel.add(phone);

        phoneText = new TextField("");
        phoneText.setBounds(150, 320, 200, 20);
        panel.add(phoneText);

        next = new JButton("Next");
        next.setBounds(130, 390, 100, 25);
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(250, 390, 100, 25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, "Center");

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setBackground(new Color(0xFFFFFF));

        URL imageURL = getClass().getClassLoader().getResource("icon/splash/l.png");
        ImageIcon i1 = new ImageIcon(imageURL);
        Image i2 = i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLable = new JLabel(i3);
        imagePanel.add(imgLable, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.WEST);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==next) {
            String sname = nameText.getText();
            String smeter = meternumText.getText();
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String eemail = emailText.getText();
            String sphone = phoneText.getText();

            String query_customer = "insert into newcustomer values('"+sname+"','"+smeter+"','"+saddress+"','"+scity+"','"+sstate+"','"+eemail+"','"+sphone+"')";
            String query_signup = "insert into signup values('"+smeter+"','','"+sname+"','','')";

            try {
                database c = new database();
                c.statement.executeUpdate(query_customer);
                c.statement.executeUpdate(query_signup);

                JOptionPane.showMessageDialog(null,"Customer details added successfully");
                setVisible(false);
                new meterInfo(smeter);
            }catch (Exception E){
                E.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new newCustomer();
    }
}
