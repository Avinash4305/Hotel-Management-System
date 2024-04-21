package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {

    Dashboard(){
        setBounds(0,0,1550, 1000);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550,1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1550,1000);
        add(image);

        JLabel text = new JLabel("WELCOME TO THE TAJ HOTELS");
        text.setBounds(400,80,1000,50);
        text.setFont(new Font("Tahoma", Font.PLAIN,45));
        text.setForeground(Color.WHITE);
        image.add(text);

        JMenuBar mb = new JMenuBar();
        mb.setBounds(0,0,1550,30);
        image.add(mb);

        JMenu hotel = new JMenu("HOTEL MANAGEMENT");
        hotel.setForeground(Color.BLACK);
        mb.add(hotel);

        JMenuItem reception = new JMenuItem("Reception");
        reception.addActionListener(this);
        hotel.add(reception);

        JMenu admin = new JMenu("ADMIN");
        admin.setForeground(Color.BLACK);
        mb.add(admin);

        JMenuItem addEmploye = new JMenuItem("Add Employee");
        addEmploye.addActionListener(this);
        admin.add(addEmploye);

        JMenuItem addRooms = new JMenuItem("Add Rooms");
        addRooms.addActionListener(this);
        admin.add(addRooms);

        JMenuItem addDriver = new JMenuItem("Add Driver");
        addDriver.addActionListener(this);
        admin.add(addDriver);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Add Employee")){
            new addEmployee();
        }else if(ae.getActionCommand().equals("Add Rooms")){
            new addRooms();
        }else if(ae.getActionCommand().equals("Add Driver")){
            new addDriver();
        }
    }

    public static void main(String[] args) {
        new Dashboard();
    }


}
