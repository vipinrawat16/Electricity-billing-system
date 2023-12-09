package electricity.biling.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Splash extends JFrame {
    Splash(){
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\vipin\\ideaprojects\\Electricity__Biling\\src\\icon\\splash\\a.jpg");
        Image imageOne  = imageIcon.getImage().getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);
        ImageIcon imageIcon2 = new ImageIcon(imageOne);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel);

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login();
            }
        });
        timer.setRepeats(false);
        timer.start();
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            Splash splash = new Splash();
            splash.setVisible(true);
        });

    }
}