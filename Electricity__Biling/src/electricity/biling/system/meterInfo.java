package electricity.biling.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class meterInfo extends JFrame implements ActionListener {

    Choice meterLocCho, metertypeCho, PhaseCodeCho, billtypeCho;
    JButton submit;
    String meternumber;
    meterInfo(String meternumber){
        this.meternumber = meternumber;
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252,186,3));
        add(panel);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 200, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(50, 80, 100, 20);
        panel.add(meterNumber);

        JLabel meterNumberText = new JLabel(meternumber);
        meterNumberText.setBounds(180, 80, 150, 20);
        panel.add(meterNumberText);

        JLabel meterLoc = new JLabel("Meter Number");
        meterLoc.setBounds(50,120,100,20);
        panel.add(meterLoc);

        meterLocCho = new Choice();
        meterLocCho.add("Outside");
        meterLocCho.add("Inside");
        meterLocCho.setBounds(180,120,150,20);
        panel.add(meterLocCho);

        JLabel meterType = new JLabel("Meter Type");
        meterType.setBounds(50,160,100,20);
        panel.add(meterType);

        metertypeCho = new Choice();
        metertypeCho.add("Electric Meter");
        metertypeCho.add("Solar Meter");
        metertypeCho.add("Smart Meter");
        metertypeCho.setBounds(180,160,150,20);
        panel.add(metertypeCho);

        JLabel phaseCode = new JLabel("Phase Code");
        phaseCode.setBounds(50,200,100,20);
        panel.add(phaseCode);

        PhaseCodeCho = new Choice();
        PhaseCodeCho.add("011");
        PhaseCodeCho.add("022");
        PhaseCodeCho.add("033");
        PhaseCodeCho.add("044");
        PhaseCodeCho.add("055");
        PhaseCodeCho.add("066");
        PhaseCodeCho.add("077");
        PhaseCodeCho.add("088");
        PhaseCodeCho.add("099");
        PhaseCodeCho.setBounds(180,200,150,20);
        panel.add(PhaseCodeCho);

        JLabel billtype = new JLabel("Bill Type");
        billtype.setBounds(50,240,100,20);
        panel.add(billtype);

        billtypeCho = new Choice();
        billtypeCho.add("Normal");
        billtypeCho.add("Industrial");
        billtypeCho.setBounds(180,240,150,20);
        panel.add(billtypeCho);

        JLabel day = new JLabel("30 Days Billing Time...");
        day.setBounds(50,280,150,20);
        panel.add(day);

        JLabel note = new JLabel("Note:-");
        note.setBounds(50,320,100,20);
        panel.add(note);

        JLabel note1 = new JLabel("By default bill is Calculated for 30 days only");
        note1.setBounds(50,340,300,20);
        panel.add(note1);

        submit = new JButton("Submit");
        submit.setBounds(220,390,100,20);
        submit.setBackground(Color.black);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        panel.add(submit);

        setLayout(new BorderLayout());
        add(panel,"Center");

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setBackground(new Color(0xFFFFFF));

        URL imageURL = getClass().getClassLoader().getResource("icon/splash/z.png");
        ImageIcon i1 = new ImageIcon(imageURL);
        Image i2 = i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLable = new JLabel(i3);
        imagePanel.add(imgLable, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.EAST);

        setSize(700,500);
        setLocation(400,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submit) {
            String smeterNum = meternumber;
            String smeterLoc = meterLocCho.getSelectedItem();
            String smeterType = metertypeCho.getSelectedItem();
            String sphaseCode = PhaseCodeCho.getSelectedItem();
            String sbillType = billtypeCho.getSelectedItem();
            String sday = "30";

            String query_meterInfo = "insert into meter_info values('"+smeterNum+"','"+smeterLoc+"','"+smeterType+"','"+sphaseCode+"','"+sbillType+"','"+sday+"')";
            try{
                database c = new database();
                c.statement.executeUpdate(query_meterInfo);

                JOptionPane.showMessageDialog(null,"Meter Information Submited Successfully");
                setVisible(false);
            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new meterInfo("");
    }
}
