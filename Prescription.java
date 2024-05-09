package hospital_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Prescription extends JFrame {
    private JLabel lab = new JLabel("PrescID ");
    private JTextField tf3 = new JTextField();
    private JLabel labb = new JLabel("DoctorName");
    private JTextField t4 = new JTextField();
    private JLabel lab1 = new JLabel("CHANNEL ID ");
    private JTextField tf = new JTextField();
    private JLabel lab2 = new JLabel("Disease Type ");
    private JTextField tf1 = new JTextField();
    private JLabel lab3 = new JLabel("Prescription");
    private JTextArea ta = new JTextArea();
    private JPanel pan = new JPanel();
    private JButton save = new JButton("Save");
    private JButton update = new JButton("Update");
    private JButton close = new JButton("Close");
    private JPanel pan2 = new JPanel();
    private JLabel labLL = new JLabel("HOSPITAL MANAGEMENT SYSTEM ");
    private JTable table = new JTable();

    Prescription(String chid, String docname) {
        autoID();
        table1();
        pan.setLayout(new BorderLayout());
        tf.setText(chid);
        t4.setText(docname);
       
        pan2.add(labLL);
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        inputPanel.add(lab);
        inputPanel.add(tf3);
        inputPanel.add(lab1);
        inputPanel.add(tf);
        inputPanel.add(labb);
        inputPanel.add(t4);
        inputPanel.add(lab2);
        inputPanel.add(tf1);
        inputPanel.add(lab3);
        inputPanel.add(ta);
        JScrollPane scrollPane = new JScrollPane(table);
        
          table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) { // If a row is selected
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    tf3.setText(model.getValueAt(selectedRow, 0).toString());
                    tf.setText(model.getValueAt(selectedRow, 1).toString());
                    t4.setText(model.getValueAt(selectedRow, 2).toString());
                    tf1.setText(model.getValueAt(selectedRow, 3).toString());
                    ta.setText(model.getValueAt(selectedRow, 4).toString());
                    
                }else{JOptionPane.showMessageDialog(null, "please select th row!!!");}
            }
        });
        
        
        pan.add(inputPanel, BorderLayout.CENTER);

        
        pan.add(scrollPane, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(save);
        buttonPanel.add(update);
        buttonPanel.add(close);
        pan.add(buttonPanel, BorderLayout.SOUTH);
        pan.add(pan2, BorderLayout.PAGE_START);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection con = null;
                Statement stmt = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
                    stmt = con.createStatement();

                    String insert = "INSERT INTO prescription VALUES('" + tf3.getText() + "','" + tf.getText() + "','"
                            + t4.getText() + "','" + tf1.getText() + "','" + ta.getText() + "')";
                    stmt.executeUpdate(insert);
                    JOptionPane.showMessageDialog(null, "ADDED SUCCESSFULLY!");

                    System.out.println("added successfully!");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                   
              try{
    
          String value1= tf3.getText();
            String value2=tf.getText();
            String value3= t4.getText();
            String value4=tf1.getText();
             String value5=ta.getText();
                        
         Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            String sql="update prescription set channel_id='"+value2+"', doctor_name='"+value3+"',  des_type='"+value4+"', description='"+value5+"' where presid='"+value1+"'";
            PreparedStatement statement=con.prepareStatement(sql);
           //Pst=conn.prepareStatement();
          int rowsupdated= statement.executeUpdate(sql);
          if(rowsupdated>0){
          JOptionPane.showMessageDialog(null, "updated");  
           table1();
          }else{
                JOptionPane.showMessageDialog(null, "you didn't update any thing");}
                     } catch (SQLException ex) {
                    Logger.getLogger(Prescription.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Prescription.class.getName()).log(Level.SEVERE, null, ex);
                }
         
            }
        });

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new View_Channel();
                setVisible(false);
            }
        });

        add(pan);
        setTitle("Prescription");
        setSize(750, 350);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void table1() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM prescription");
            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("presid");
            model.addColumn("channel_id");
            model.addColumn("doctor_name");
            model.addColumn("des_type");
            model.addColumn("description ");

            while (rs.next()) {
                Object[] rowData = new Object[5];
               rowData[0] = rs.getString("presid");
                rowData[1] = rs.getString("channel_id");
                rowData[2] = rs.getString("doctor_name");
                rowData[3] = rs.getString("des_type");
                rowData[4] = rs.getString("description");
                model.addRow(rowData);
            }

            table.setModel(model);

            rs.close();
            pst.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void autoID() {
    try {
        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Establish a connection to the database
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
        
        // Prepare a statement to retrieve the maximum patientNo from the patient table
        PreparedStatement pst = con.prepareStatement("SELECT MAX(presid) FROM prescription");
        
        // Execute the query and retrieve the result set
        ResultSet rs = pst.executeQuery();
        rs.next();
        
        // Get the maximum patientNo value from the result set
        String maxPatientNo = rs.getString("MAX(presid)");
        
        // Check if the maximum patientNo is null
        if (maxPatientNo == null) {
            // f it's null, set the ID to "ps001"
            tf3.setText("PR001");
        } else {
            // Extract the numeric portion of the maximum patientNo and increment it
            long n = Long.parseLong(maxPatientNo.substring(2));
            n++;
            
            // Format the incremented number as a three-digit string and set the ID
            tf3.setText("PR" + String.format("%03d", n));
        }
        
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(PatientRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(PatientRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
    
    
}