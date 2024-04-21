package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelManagementSystem extends JFrame implements ActionListener {
    HotelManagementSystem() {

        setSize(1366, 565); // setting the size of the login frame
        setLocation(42, 152);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0,0,1366,565);//location x, location y, length, breadth
        add(image);

        JLabel text = new JLabel("HOTEL MANAGEMENT SYSTEM");
        text.setBounds(20,430,1000,90);
        text.setFont(new Font("serif",Font.PLAIN,50));
        text.setForeground(Color.WHITE); // Color class needs to import awt pacakge
        image.add(text);

        JButton next = new JButton("Next");
        next.setBounds(1150,450,150,50);
        next.setBackground(Color.BLACK);
        next.addActionListener(this);
        image.add(next);

        setVisible(true);

        }

    @Override
    public void actionPerformed(ActionEvent e) {

            setVisible(false);
            new Login();

    }

    public static void main(String[] args) {
        new HotelManagementSystem();
    }


}
