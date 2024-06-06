package hospital_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hospital_Management extends JFrame {
    private JPanel pan = new JPanel();
    private JComboBox<String> cb = new JComboBox<>();
    private JLabel lab1 = new JLabel("LOGIN FORM");
    private JLabel lab2 = new JLabel("USER NAME");
    private JTextField tf = new JTextField();
    private JLabel lab3 = new JLabel("PASSWORD");
    private JPasswordField pw = new JPasswordField(3);
    private JLabel lab4 = new JLabel("USER TYPE");

    private JButton bt2 = new JButton("EXIT");
    private JButton bt3 = new JButton("login");
    private JPanel pan2 = new JPanel();
    private JPanel pan1 = new JPanel();

    public Hospital_Management() {
          JLabel labLL = new JLabel("WELCOME TO\n HOSPITAL MANAGEMENT SYSTEM ");
        pan1.setLayout(new BorderLayout());
        labLL.setBackground(Color.darkGray);
        labLL.setForeground(Color.red);
        labLL.setFont(labLL.getFont().deriveFont(Font.BOLD));

        // Create a JLabel for the image
        JLabel imageLabel = new JLabel();
        // Load the image from a file
        ImageIcon imageIcon = new ImageIcon("C://Users/Hirut Tarekegn/Desktop/javaproject1/images.jfif");
        // Set the image icon to the JLabel
        imageLabel.setIcon(imageIcon);

        pan2.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan2.add(labLL);
        pan2.add(imageLabel);
        Color c=new Color(150,150,255);
        pan2.setBackground(c);
        pan.setLayout(new GridLayout(6, 2, 10, 10));
        pan.setBackground(Color.CYAN);
        pan.add(lab1);
        pan.add(new JLabel());

        pan.add(lab2);
        pan.add(tf);

        pan.add(lab3);
        pan.add(pw);

        pan.add(lab4);
        pan.add(cb);

        pan.add(new JLabel());
        pan.add(bt2);
        pan.add(bt3);

        cb.addItem("Admin");
        cb.addItem("Receptionist");
        cb.addItem("Doctor");
        cb.addItem("Pharmacist");
         cb.addItem("Lboratoriest");

        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               System.exit(0);
            }
        });

        bt3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = tf.getText();
                String password = pw.getText();
                String utype = cb.getSelectedItem().toString();
//                    Class.forName("com.mysql.cj.jdbc.Driver");
//                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project", "root", "1234");
//                    Statement st = con.createStatement();
////                    ResultSet rs = st.executeQuery("SELECT * FROM user");
                   Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project", "root", "1234");
            
            PreparedStatement st=con.prepareStatement("select * from user where username= ? and passworrd= ? and usertype = ? ");
            st.setString(1,username);
            st.setString(2,password);
            st.setString(3,utype);
            ResultSet rs=st.executeQuery();
                      //String usertype = rs.getString(4); 
                       if (rs.next()) {
//                        String selectedUserType = (String) cb.getSelectedItem();
                        if (utype.equals("Receptionist")) {
                            
                            new Recieption();
                             setVisible(false);

                        } else if ( utype.equals("Pharmacist")) {
                           
                            new Pharmacist();
                            
                                  setVisible(false);

                        } else if ( utype.equals("Doctor")) {
                            
                            new Doctor();
                            
                            
                            setVisible(false);
                        } else if (utype.equals("Admin")) {
                            
                            new Admin();
                            
                            
                            setVisible(false);
                        } else if (utype.equals("Lboratoriest")) {
                            
                            new Labratory();
                            
                            
                            setVisible(false);
                        }
                        JOptionPane.showMessageDialog(null, "Login successful!");
                   } else {
                        JOptionPane.showMessageDialog(null, "Invalid ID or Password!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Hospital_Management.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Hospital_Management.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        pan1.add(pan, BorderLayout.CENTER);
        pan1.add(pan2, BorderLayout.PAGE_START);

        add(pan1);
        setTitle("HOSPITAL MANAGEMENT SYSTEM");
        setSize(450, 450);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Hospital_Management hm = new Hospital_Management();
    }
    
    
   
}
