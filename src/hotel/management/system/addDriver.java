package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.sql.SQLException;

public class addDriver extends JFrame implements ActionListener {

    JTextField tfName,tfAge,tfCarCompany,tfModel,tfLocation;
    JButton addDriver, cancel;
    JComboBox cbAvailable,cbGender,cbavailability;

    addDriver() {
        getContentPane().setBackground(Color.WHITE);

        setLayout(null);
        setBounds(300,200,980, 470);

        JLabel heading = new JLabel("Add Driver");
        heading.setBounds(150,10,200,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,18));
        add(heading);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(60, 70, 120,30);
        lblName.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblName);

        tfName = new JTextField();
        tfName.setBounds(200,70,150,30);
        add(tfName);

        JLabel lblAge = new JLabel("Age");
        lblAge.setBounds(60, 105, 120, 30);
        lblAge.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblAge);

        tfAge = new JTextField();
        tfAge.setBounds(200,105,150,30);
        tfAge.setFont(new Font("Tehona",Font.PLAIN,16));
        add(tfAge);


        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(60,150,120,30);
        lblGender.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblGender);

        String[] gender = {"Male","Female"};
        cbGender = new JComboBox<>(gender);
        cbGender.setBounds(200,150,150,30);
        add(cbGender);

        JLabel lblCarCompany = new JLabel("Car Company");
        lblCarCompany.setBounds(60, 190,120,30);
        lblCarCompany.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblCarCompany);

        tfCarCompany = new JTextField();
        tfCarCompany.setBounds(200,190,150,30);
        add(tfCarCompany);

        JLabel lblModel = new JLabel("Car Model");
        lblModel.setBounds(60,230,120,30);
        lblModel.setFont(new Font("Tehoma",Font.PLAIN,16));
        add(lblModel);

        tfModel = new JTextField();
        tfModel.setBounds(200,230,150,30);
        add(tfModel);

        JLabel lblAvailabiliy = new JLabel("Availability");
        lblAvailabiliy.setBounds(60,270,120,30);
        lblAvailabiliy.setFont(new Font("Tehoma",Font.PLAIN,16));
        add(lblAvailabiliy);

        String[] driverOptions = {"Available","Not Available"};
        cbAvailable = new JComboBox(driverOptions) ;
        cbAvailable.setBounds(200,270,150,30);
        add(cbAvailable);

        JLabel lblLocation = new JLabel("Location");
        lblLocation.setBounds(60,310,120,30);
        lblLocation.setFont(new Font("Tehoma",Font.PLAIN,16));
        add(lblLocation);

        tfLocation = new JTextField();
        tfLocation.setBounds(200,310,150,30);
        add(tfLocation);

        addDriver = new JButton("Add Driver");
        addDriver.setBounds(60,370,130,30);
        addDriver.addActionListener(this);
        add(addDriver);

        cancel = new JButton("Cancle");
        cancel.setBounds(220,370,130,30);
        cancel.addActionListener(this);
        add(cancel);




        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,20,500,400 );
        add(image);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == addDriver){
            String name = tfName.getText();
            String age = tfAge.getText();
            String gender = (String) cbGender.getSelectedItem();
            String company = tfCarCompany.getText();
            String brand =  tfModel.getText();
            String available = (String) cbAvailable.getSelectedItem();
            String location = tfLocation.getText();

            try {
                Conn c = new Conn();
                String query = "insert into driver values('"+name+"', '"+age+"', '"+gender+"', '"+company+"', '"+brand+"', '"+available+"', '"+location+"');";
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null,"Driver Added Successfully");
                setVisible(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else if(ae.getSource() == cancel){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new addDriver();
    }
}
