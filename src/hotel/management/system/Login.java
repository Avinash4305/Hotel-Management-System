package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JTextField userName;
    JPasswordField password;
    JButton login, cancel;
    Login() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel user= new JLabel("Username");
        user.setBounds(40,20,100,30);
        add(user);

        userName = new JTextField();
        userName.setBounds(150,20,200, 30);
        add(userName);

        JLabel pass= new JLabel("Password");
        pass.setBounds(40,70,100,30);
        add(pass);

        password = new JPasswordField();
        password.setBounds(150,70,200, 30);
        add(password);

        login = new JButton("Login");
        login.setBounds(40,150,120,30);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(180,150,120,30);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350,10,200,200);
        add(image);


        setBounds(464, 239, 600,300);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == login ){
            String user = userName.getText();
            String pass = password.getText();

            try{
                Conn c = new Conn();
                String query = "select * from login where userName = '"+ user+ "' and password = '" + pass + "'";

                ResultSet rs = c.s.executeQuery(query);

                if(rs.next()){
                    setVisible(false);
                    new Dashboard();
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid UserName or Password");
                    setVisible(false);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }else if (ae.getSource() == cancel){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }


}
