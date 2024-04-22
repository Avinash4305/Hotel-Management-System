package hotel.management.system;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class reception extends JFrame implements ActionListener {

    JButton newCustomerForm,rooms,department,allEmployees,customerInfo,managerInfo,checkout,updateStatus;
    JButton updateRoomStatus,pickupService,searchRoom,logout;

    reception() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setBounds(310,155,800,570);

        newCustomerForm = new JButton("New Customer Form");
        newCustomerForm.setBounds(10,30,200,30);
        add(newCustomerForm);

        rooms = new JButton("Rooms");
        rooms.setBounds(10,70,200,30);
        add(rooms);

        department = new JButton("Department");
        department.setBounds(10,110,200,30);
        add(department);

        allEmployees = new JButton("All Employees");
        allEmployees.setBounds(10,150,200,30);
        add(allEmployees);

        customerInfo = new JButton("Customer Info");
        customerInfo.setBounds(10,190,200,30);
        add(customerInfo);

        managerInfo = new JButton("Manager Info");
        managerInfo.setBounds(10,230,200,30);
        add(managerInfo);

        checkout = new JButton("Checkout");
        checkout.setBounds(10,270,200,30);
        add(checkout);

        updateStatus = new JButton("Update Status");
        updateStatus.setBounds(10,310,200,30);
        add(updateStatus);

        updateRoomStatus = new JButton("Update Room Status");
        updateRoomStatus.setBounds(10,350,200,30);
        add(updateRoomStatus);

        pickupService = new JButton("Pickup Service");
        pickupService.setBounds(10,390,200,30);
        add(pickupService);

        searchRoom = new JButton("Search Rooms");
        searchRoom.setBounds(10,430,200,30);
        add(searchRoom);


        logout = new JButton("Logout");
        logout.setBounds(10,470,200,30);
        add(logout);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(250,30,500,470);
        add(image);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new reception();
    }
}
