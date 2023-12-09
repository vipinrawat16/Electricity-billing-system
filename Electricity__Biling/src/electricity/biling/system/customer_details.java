package electricity.biling.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class customer_details extends JFrame implements ActionListener {
    Choice searchMeterCho,SearchNameCho;
    JTable table;
    JButton search,print,close;
    customer_details(){
        super("Customer Details");
        getContentPane().setBackground(new Color(208, 154, 245));
        setSize(800,500);
        setLocation(400,200);
        setLayout(null);

        JLabel searchMeter = new JLabel("Search By meter Number");
        searchMeter.setBounds(20,20,150,20);
        add(searchMeter);

        searchMeterCho = new Choice();
        searchMeterCho.setBounds(180,20,130,20);
        add(searchMeterCho);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from newcustomer");
            while (resultSet.next()){
                searchMeterCho.add(resultSet.getString("meter_no"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel searchName = new JLabel("Search By Name");
        searchName.setBounds(450,20,100,20);
        add(searchName);

        SearchNameCho = new Choice();
        SearchNameCho.setBounds(570,20,150,20);
        add(SearchNameCho);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from newcustomer");
            while (resultSet.next()){
                SearchNameCho.add(resultSet.getString("name"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        table = new JTable();
        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from newcustomer");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,800,500);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);

        search = new JButton("Search");
        search.setBackground(Color.white);
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(Color.white);
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBackground(Color.white);
        close.setBounds(600,70,80,20);
        close.addActionListener(this);
        add(close);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==search) {
            String query_search = "select * from newcustomer where meter_no = '"+searchMeterCho.getSelectedItem()+"' and name = '"+SearchNameCho.getSelectedItem()+"'";
            try{
                database c = new database();
                ResultSet resultSet = c.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch (Exception E){
                E.printStackTrace();
            }

        } else if (e.getSource()==print) {
            try{
                table.print();
            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new customer_details();
    }
}
