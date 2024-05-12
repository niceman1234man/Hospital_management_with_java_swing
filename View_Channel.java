package hospital_management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class View_Channel extends JFrame {

    private JButton precreption = new JButton("Prescription");
     private JButton Lab = new JButton("Assign laboratory");
    private JButton logout = new JButton("LogOut");
     private JButton apoint = new JButton("Appointment");
    private JTable table = new JTable();

    private JPanel buttonPanel = new JPanel();
    private JPanel mainPanel = new JPanel();

    View_Channel() {
        table1();

        // Set BorderLayout for the main panel
        mainPanel.setLayout(new BorderLayout());

        // Wrap the table in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
      scrollPane.setBackground(Color.red);
        // Add the scroll pane to the main panel
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        table.setBackground(Color.cyan);
        // Add the buttons to the button panel
        buttonPanel.add(precreption);
        buttonPanel.add(apoint);
        buttonPanel.add(logout);
        buttonPanel.add(Lab);
        precreption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
               
                 int selectedIndex=table.getSelectedRow();
                  if (selectedIndex != -1) { 
                   DefaultTableModel d1=(DefaultTableModel)table.getModel();
                  
                 String chno=d1.getValueAt(selectedIndex, 0).toString();
                 String docName=d1.getValueAt(selectedIndex, 1).toString();
                 
                
                
                new Prescription(chno,docName);
               }else{JOptionPane.showMessageDialog(null, "please select th row!!!");}

                setVisible(false);
            }
        });
        
        
        Lab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
               
                 int selectedIndex=table.getSelectedRow();
                  if (selectedIndex != -1) { 
                   DefaultTableModel d1=(DefaultTableModel)table.getModel();
                  
                 String chno=d1.getValueAt(selectedIndex, 0).toString();
                 String docName=d1.getValueAt(selectedIndex, 1).toString();
                 
                
                
                new Lboratoriest(chno,docName);
               }else{JOptionPane.showMessageDialog(null, "please select th row!!!");}

                setVisible(false);
            }
        });
        

        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Doctor();
                setVisible(false);
            }
        });
        
    
         
         
         
        apoint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 int selectedIndex=table.getSelectedRow();
                  if (selectedIndex != -1) { 
                   DefaultTableModel d1=(DefaultTableModel)table.getModel();
                  
                 String chno=d1.getValueAt(selectedIndex, 0).toString();
                 String docName=d1.getValueAt(selectedIndex, 1).toString();
                   String paName=d1.getValueAt(selectedIndex, 2).toString();
                
                
                new Appointment(chno,docName,paName);
               }else{JOptionPane.showMessageDialog(null, "please select th row!!!");}

                setVisible(false);
            }
        });

        // Add the button panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the JFrame
        add(mainPanel);

        setTitle("View Channel");
        setSize(500, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void table1() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM channel");
            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("channelNo");
            model.addColumn("doctor_name");
            model.addColumn("patientName");
            model.addColumn("roomNo");
            model.addColumn("channelDate ");

            while (rs.next()) {
                Object[] rowData = new Object[5];
                rowData[0] = rs.getString("channelNo");
                rowData[1] = rs.getString("doctor_name");
                rowData[2] = rs.getString("patientName");
                rowData[3] = rs.getString("roomNo");
                rowData[4] = rs.getString("channelDate");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new View_Channel();
        });
    }
}