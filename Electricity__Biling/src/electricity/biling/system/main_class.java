package electricity.biling.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_class extends JFrame implements ActionListener {
    String acctype;
    String meter_pass;
    main_class(String acctype,String meter_pass) {
        this.acctype = acctype;
        this.meter_pass = meter_pass;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/splash/ele.png"));
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("serif", Font.PLAIN, 15));


        JMenuItem newcustomer = new JMenuItem("New Customer");
        newcustomer.setFont(new Font("monospace", Font.PLAIN, 14));
        newcustomer.addActionListener(this);
        menu.add(newcustomer);

        JMenuItem customerdetails = new JMenuItem("Customer Details");
        customerdetails.setFont(new Font("monospace", Font.PLAIN, 14));
        customerdetails.addActionListener(this);
        menu.add(customerdetails);

        JMenuItem depositdetails = new JMenuItem("Deposit Details");
        depositdetails.setFont(new Font("monospace", Font.PLAIN, 14));
        depositdetails.addActionListener(this);
        menu.add(depositdetails);

        JMenuItem calculatebill = new JMenuItem("Calculate Bill");
        calculatebill.setFont(new Font("monospace", Font.PLAIN, 14));
        calculatebill.addActionListener(this);
        menu.add(calculatebill);

        JMenu info = new JMenu("Information");
        info.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem Upinfo = new JMenu("Update Information");
        Upinfo.setFont(new Font("monospace",Font.PLAIN,14));
        Upinfo.addActionListener(this);
        info.add(Upinfo);

        JMenuItem viewInfo = new JMenuItem("View Information");
        viewInfo.setFont(new Font("monospace",Font.PLAIN,14));
        viewInfo.addActionListener(this);
        info.add(viewInfo);

        JMenu user= new JMenu("User");
        user.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem paybill = new JMenuItem("Pay Bill");
        paybill.setFont(new Font("monospace",Font.PLAIN,14));
        paybill.addActionListener(this);
        user.add(paybill);

        JMenuItem billdetails = new JMenuItem("Bill Details");
        billdetails.setFont(new Font("monospace",Font.PLAIN,14));
        billdetails.addActionListener(this);
        user.add(billdetails);

        JMenu bill = new JMenu("Bill");
        bill.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem genBill = new JMenuItem("Generate Bill");
        genBill.setFont(new Font("monospace",Font.PLAIN,14));
        genBill.addActionListener(this);
        bill.add(genBill);

        JMenu utility = new JMenu("Utility");
        utility.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setFont(new Font("monospace",Font.PLAIN,14));
        notepad.addActionListener(this);
        utility.add(notepad);

        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.setFont(new Font("monospace",Font.PLAIN,14));
        calculator.addActionListener(this);
        utility.add(calculator);

        JMenu exit = new JMenu("Exit");
        exit.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem eexit = new JMenuItem("Exit");
        eexit.setFont(new Font("monospace",Font.PLAIN,14));
        eexit.addActionListener(this);
        exit.add(eexit);

        if (acctype.equals("Admin")) {
            menuBar.add(menu);
        }else{
            menuBar.add(bill);
            menuBar.add(user);
            menuBar.add(info);
        }
        menuBar.add(utility);
        menuBar.add(exit);

        setLayout(new FlowLayout());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String msg = e.getActionCommand();
        if (msg.equals("New Customer")) {
            new newCustomer();
        } else if (msg.equals("Customer Details")) {
            new customer_details();
        } else if (msg.equals("Deposit Details")) {
            new deposit_details();
        } else if (msg.equals("Calculate Bill")) {
            new CalculateBill();
        } else if (msg.equals("View Information")) {
            new view_information(meter_pass);
        } else if (msg.equals("Update Information")) {
            new update_information(meter_pass);
        } else if (msg.equals("Bill Details")) {
            new bill_details(meter_pass);
        } else if (msg.equals("Calculator")) {
            try{
                Runtime.getRuntime().exec("calc.exe");
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (msg.equals("Notepad")) {
            try{
                Runtime.getRuntime().exec("notepad.exe");
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (msg.equals("Exit")) {
            setVisible(false);
            new Login();
        } else if (msg.equals("Pay Bill")) {
            new pay_bill(meter_pass);
        } else if (msg.equals("Generate Bill")) {
            new generate_bill(meter_pass);
        }
    }

    public static void main(String[] args) {
        new main_class("","");
    }
}
