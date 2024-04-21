package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class addEmployee extends JFrame implements ActionListener {

    JTextField tfName, tfAge,tfSalary, tfPhone,tfEmail,tfAadhar;
    JRadioButton rbMale, rbFemale;
    JButton submit;
    JComboBox cbJob;

    addEmployee() {
        setLayout(null);
                  // Color class is available in awt package;

        JLabel name = new JLabel("Name");
        name.setBounds(60,30,120,30);
        name.setFont(new Font ("Tahoma",Font.PLAIN,17));
        add(name);

        tfName = new JTextField();
        tfName.setBounds(200,30,150,30);
        add(tfName);

        JLabel age = new JLabel("Age");
        age.setBounds(60,80,120,30);
        age.setFont(new Font ("Tahoma",Font.PLAIN,17));
        add(age);

        tfAge = new JTextField();
        tfAge.setBounds(200,80,150,30);
        add(tfAge);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60,130,120,30);
        lblgender.setFont(new Font ("Tahoma",Font.PLAIN,17));
        add(lblgender);


        rbMale = new JRadioButton("Male");
        rbMale.setBounds(200, 130, 70, 30);
        rbMale.setFont(new Font("Tahoma",Font.PLAIN,14));
        add(rbMale);

        rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(265, 130, 90, 30);
        rbFemale.setFont(new Font("Tahoma",Font.PLAIN,14));
        add(rbFemale);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbMale);
        bg.add(rbFemale);

        JLabel lblJob = new JLabel("Job");
        lblJob.setBounds(60,180,120,30);
        lblJob.setFont(new Font ("Tahoma",Font.PLAIN,17));
        add(lblJob);

        String[] str = {"Front Desk Clerks","Porters","House Keeping","Kitchen staff","Room Serviece","Chef","Waiter/Waitress","Manager","Accountant"};
        cbJob = new JComboBox(str);
        cbJob.setBounds(200, 180, 150, 30);
        add(cbJob);

        JLabel lblSalary = new JLabel("Salary");
        lblSalary.setBounds(60,230,120,30);
        lblSalary.setFont(new Font ("Tahoma",Font.PLAIN,17));
        add(lblSalary);

        tfSalary = new JTextField();
        tfSalary.setBounds(200,230,150,30);
        add(tfSalary);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(60,280,120,30);
        lblPhone.setFont(new Font ("Tahoma",Font.PLAIN,17));
        add(lblPhone);

        tfPhone = new JTextField();
        tfPhone.setBounds(200,280,150,30);
        add(tfPhone);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(60,330,120,30);
        lblEmail.setFont(new Font ("Tahoma",Font.PLAIN,17));
        add(lblEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(200,330,150,30);
        add(tfEmail);

        JLabel lblAadhar = new JLabel("Aadhar");
        lblAadhar.setBounds(60,380,120,30);
        lblAadhar.setFont(new Font ("Tahoma",Font.PLAIN,17));
        add(lblAadhar);

        tfAadhar = new JTextField();
        tfAadhar.setBounds(200,380,150,30);
        add(tfAadhar);

        submit = new JButton("Submit");
        submit.setBounds(200,430,150,30);
        submit.addActionListener(this);
        add(submit);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450,450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(380,60, 450,370);
        add(image);




        setBounds(350,200,850,540);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = tfName.getText();
        String age = tfAge.getText();
        String salary = tfSalary.getText();
        String phone = tfPhone.getText();
        String email = tfEmail.getText();
        String aadhar = tfAadhar.getText();

        String gender = null;

        if(rbMale.isSelected()){
            gender = "Male";
        }else if(rbFemale.isSelected()){
            gender = "Female";
        }

        String job = (String) cbJob.getSelectedItem();

        try {
            Conn c = new Conn();
            String str = "INSERT INTO employee values( '"+name+"', '"+age+"', '"+gender+"', '"+job+"', '"+salary+"', '"+phone+"', '"+email+"','"+aadhar+"')";

            c.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null,"Employee Added");
            setVisible(false);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new addEmployee();
    }


}
