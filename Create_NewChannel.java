package hospital_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Create_NewChannel extends JFrame {
    private JLabel lab1 = new JLabel("CHANNEL NO ");
    private JTextField tf = new JTextField();
    private JLabel lab2 = new JLabel("Doctor Name");
    private JComboBox<String> cb = new JComboBox<>();
    private JLabel lab3 = new JLabel("Patient Name ");
    private JComboBox<String> cb1 = new JComboBox<>();
    private JLabel lab4 = new JLabel("Room NO ");
    private JTextField tf3 = new JTextField();
    private JLabel lab5 = new JLabel("CHANNEL Date ");
    private JTextField cb2 = new JTextField();
    private JPanel pan = new JPanel();
    private JButton create = new JButton("Create");
    private JButton cancel = new JButton("Cancel");
    private JButton close = new JButton("Close");
   
//    private JTextArea ta = new JTextArea();

    public Create_NewChannel() {
         autoID();
         dloader();
        ploader();
       
        pan.setBackground(Color.cyan);
        pan.add(lab1);
        pan.add(tf);
        pan.add(lab2);
        pan.add(cb);
        pan.add(lab3);
        pan.add(cb1);
        pan.add(lab4);
        pan.add(tf3);
        pan.add(lab5);
        pan.add(cb2);
//        pan.add(ta);

        pan.setLayout(new GridLayout(8, 2, 5, 6));
        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
                    Statement stmt=(Statement)con.createStatement();
                    String insert=("insert into channel VALUES('" + tf.getText() + "','" + cb.getSelectedItem() + "','" +  cb1.getSelectedItem() +
                            "','" + tf3.getText() + "','"+ cb2.getText() +"')");
                    stmt.executeUpdate(insert);
                    JOptionPane.showMessageDialog(null, "ADDED SUCCESSFULLY!");
                    
                    
                    
                    // Code to save the channel
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Create_NewChannel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Create_NewChannel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                tf.setText("");
                tf3.setText("");
                cb2.setText("");
                cb.setSelectedItem(null);
                 cb1.setSelectedItem(null);
                
                
                // Code to clear form fields
            }
        });

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
               new Recieption();
                setVisible(false);
                // Code to close the current window and go back to the reception window
            }
        });

      

        pan.add(create);
        pan.add(cancel);
        pan.add(close);
        

        add(pan);
        setTitle("HOSPITAL");
        setSize(500, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load doctor and patient data
       
    }

   

    public void dloader() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM doctor");
            ResultSet rs = pst.executeQuery();
            cb.removeAllItems();
            while (rs.next()) {
                cb.addItem(rs.getString(1));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Create_NewChannel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Create_NewChannel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ploader() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM patient");
            ResultSet rs = pst.executeQuery();
            cb1.removeAllItems();
            while (rs.next()) {
                cb1.addItem(rs.getString(2));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Create_NewChannel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Create_NewChannel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public void autoID() {
    try {
        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Establish a connection to the database
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
        
        // Prepare a statement to retrieve the maximum channelNo from the channel table
        PreparedStatement pst = con.prepareStatement("SELECT MAX(channelNo) FROM channel");
        
        // Execute the query and retrieve the result set
        ResultSet rs = pst.executeQuery();
        rs.next();
        
        // Get the maximum channelNo value from the result set
        String maxchannelNo = rs.getString("MAX(channelNo)");
        
        // Check if the maximum channelNo is null
        if (maxchannelNo == null) {
            // If it's null, set the ID to "CH001"
            tf.setText("CH001");
        } else {
            // Extract the numeric portion of the maximum channelNo and increment it
            long n = Long.parseLong(maxchannelNo.substring(2));
            n++;
            
            // Format the incremented number as a three-digit string and set the ID
            tf.setText("CH" + String.format("%03d", n));
        }
        
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(PatientRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(PatientRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    
    
}