package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class addRooms extends JFrame implements ActionListener {

    JTextField tfRoonNo,tfPrice;
    JButton addRoom, cancel;
    JComboBox cbAvailable,cbCleaningStatus,cbBedType;

    addRooms() {
        getContentPane().setBackground(Color.WHITE);

        setLayout(null);
        setBounds(330,200,940, 470);

        JLabel heading = new JLabel("Add Rooms");
        heading.setBounds(150,20,200,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,18));
        add(heading);

        JLabel roomNo = new JLabel("Room No");
        roomNo.setBounds(60, 80, 120,30);
        roomNo.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(roomNo);

        tfRoonNo = new JTextField();
        tfRoonNo.setBounds(200,80,150,30);
        add(tfRoonNo);

        JLabel lblAvailable = new JLabel("Available");
        lblAvailable.setBounds(60, 130, 120, 30);
        lblAvailable.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblAvailable);

        String[] available = {"Available","Occupied"};
        cbAvailable = new JComboBox(available);
        cbAvailable.setBounds(200, 130, 150, 30);
        add(cbAvailable);

        JLabel lblCleaningStatus = new JLabel("Cleaning Status");
        lblCleaningStatus.setBounds(60,180,120,30);
        lblCleaningStatus.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblCleaningStatus);

        String[] cleaningStatus = {"Cleaned","Dirty"};
        cbCleaningStatus = new JComboBox<>(cleaningStatus);
        cbCleaningStatus.setBounds(200,180,150,30);
        add(cbCleaningStatus);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(60, 230,120,30);
        lblPrice.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblPrice);

        tfPrice = new JTextField();
        tfPrice.setBounds(200,230,150,30);
        add(tfPrice);

        JLabel lblBedType = new JLabel("Bed Type");
        lblBedType.setBounds(60,280,120,30);
        lblBedType.setFont(new Font("Tehoma",Font.PLAIN,16));
        add(lblBedType);

        String[] bedType = {"Single Bed","Double Bed"};
        cbBedType = new JComboBox(bedType);
        cbBedType.setBounds(200,280,150,30);
        add(cbBedType);

        addRoom = new JButton("Add Room");
        addRoom.setBounds(60,340,120,30);
        addRoom.setFont(new Font("Tehoma",Font.PLAIN,16));
        addRoom.addActionListener(this);
        add(addRoom);

        cancel = new JButton("Cancel");
        cancel.setBounds(200,340,120,30);
        cancel.setFont(new Font("Tehoma",Font.PLAIN,16));
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400,20,500,400 );
        add(image);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == addRoom){
            String roonNo = tfRoonNo.getText();
            String available = (String) cbAvailable.getSelectedItem();
            String cleaningStatus = (String) cbCleaningStatus.getSelectedItem();
            String price = tfPrice.getText();
            String bedType = (String) cbBedType.getSelectedItem();

            try {
                Conn c = new Conn();
                String query = "insert into rooms values('"+roonNo+"', '"+available+"', '"+cleaningStatus+"', '"+price+"', '"+bedType+"');";
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null,"Room Added Successfullu");
                setVisible(false);
            } catch (SQLException e) {
               e.printStackTrace();
            }

        }else if(ae.getSource() == cancel){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new addRooms();
    }


}
