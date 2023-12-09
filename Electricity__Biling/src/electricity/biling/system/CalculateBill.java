package electricity.biling.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CalculateBill extends JFrame implements ActionListener {
    JLabel nameText, addressText;
    TextField unitText;
    Choice meterNumCho,monthCho;
    JButton submit,cancel;

    public CalculateBill() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214, 195, 247));
        add(panel);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(70, 10, 300, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        JLabel meterNumLabel = new JLabel("Meter Number");
        meterNumLabel.setBounds(50, 80, 100, 20);
        panel.add(meterNumLabel);

        meterNumCho = new Choice();
        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from newcustomer");
            while (resultSet.next()) {
                meterNumCho.add(resultSet.getString("meter_no"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        meterNumCho.setBounds(180, 80, 100, 20);
        panel.add(meterNumCho);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 120, 100, 20);
        panel.add(nameLabel);

        nameText = new JLabel("");
        nameText.setBounds(180, 120, 150, 20);
        panel.add(nameText);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(50, 160, 100, 20);
        panel.add(addressLabel);

        addressText = new JLabel("");
        addressText.setBounds(180, 160, 150, 20);
        panel.add(addressText);

        try {
            database c = new database();
            String query = "SELECT * FROM newcustomer WHERE meter_no = ?";
            PreparedStatement preparedStatement = c.connection.prepareStatement(query);

            String selectedMeterNum = meterNumCho.getSelectedItem();
            if (selectedMeterNum != null) {
                preparedStatement.setString(1, selectedMeterNum);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    nameText.setText(resultSet.getString("name"));
                    addressText.setText(resultSet.getString("address"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        meterNumCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    database c = new database();
                    String query = "SELECT * FROM newcustomer WHERE meter_no = ?";
                    PreparedStatement preparedStatement = c.connection.prepareStatement(query);

                    String selectedMeterNum = meterNumCho.getSelectedItem();
                    if (selectedMeterNum != null) {
                        preparedStatement.setString(1, selectedMeterNum);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        while (resultSet.next()) {
                            nameText.setText(resultSet.getString("name"));
                            addressText.setText(resultSet.getString("address"));
                        }
                    }

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JLabel unitconsumed = new JLabel("Unit Consumed");
        unitconsumed.setBounds(50,200,100,20);
        panel.add(unitconsumed);

        unitText = new TextField();
        unitText.setBounds(180,200,150,20);
        panel.add(unitText);

        JLabel month = new JLabel("Month");
        month.setBounds(50,240,100,20);
        panel.add(month);

        monthCho = new Choice();
        monthCho.add("January");
        monthCho.add("February");
        monthCho.add("March");
        monthCho.add("April");
        monthCho.add("May");
        monthCho.add("June");
        monthCho.add("July");
        monthCho.add("August");
        monthCho.add("September");
        monthCho.add("October");
        monthCho.add("November");
        monthCho.add("December");
        monthCho.setBounds(180,240,150,20);
        panel.add(monthCho);

        submit = new JButton("Submit");
        submit.setBounds(80,300,100,20);
        submit.setBackground(Color.black);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(220,300,100,20);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.WHITE);
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
        add(imagePanel, BorderLayout.EAST);

        setSize(650, 400);
        setLocation(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CalculateBill();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String smeterNo = meterNumCho.getSelectedItem();
            String sunit = unitText.getText();
            String smonth = monthCho.getSelectedItem();

            int totalBill = 0;
            int unit = Integer.parseInt(sunit);

            // Retrieve customer-specific tax rates
            String query_tax = "SELECT * FROM tax WHERE meter_no = ?";
            try {
                database c = new database();
                PreparedStatement taxStatement = c.connection.prepareStatement(query_tax);
                taxStatement.setString(1, smeterNo);
                ResultSet taxResultSet = taxStatement.executeQuery();

                if (taxResultSet.next()) {
                    totalBill += unit * Integer.parseInt(taxResultSet.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(taxResultSet.getString("meter_rent"));
                    totalBill += Integer.parseInt(taxResultSet.getString("service_charge"));
                    totalBill += Integer.parseInt(taxResultSet.getString("swach_bharat"));
                    totalBill += Integer.parseInt(taxResultSet.getString("fixed_tax"));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            String query_total_bill = "INSERT INTO bill VALUES ('" + smeterNo + "','" + smonth + "','" + sunit + "','" + totalBill + "','Not Paid')";
            try {
                database c = new database();
                c.statement.executeUpdate(query_total_bill);

                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            setVisible(false);
        }
    }
}
