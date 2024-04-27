
package hospital_management;

import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Labratory extends JFrame {
   private JButton save = new JButton("save");
    private JButton logOut = new JButton("LOGOUT");
    private JPanel main = new JPanel();   
    private JPanel pan=new JPanel();
     private JPanel pan1=new JPanel();
   private JTable table = new JTable();
    private JLabel lab3=new JLabel("channel no");
    private JTextField tf2=new JTextField();
    private JLabel lab1=new JLabel("patient name");
    private JTextField tf=new JTextField();
    private JLabel lab2=new JLabel("type");
    private JTextField tf1=new JTextField();
    
   private  JLabel lab4=new JLabel("result");
    private JTextArea tf3=new JTextArea();
   private  JLabel lab5=new JLabel("AGE");
    private JTextField tf4=new JTextField();
     
    Labratory(){
        table1() ;
        JScrollPane scrollPane = new JScrollPane(table);
        
    pan.add(lab3);
     pan.add(tf2);
      pan.add(lab1);
       pan.add(tf);
        pan.add(lab2);
         pan.add(tf1);
         pan.add(lab4);
         pan.add(tf3);
        main.add(save); 
        main.add(logOut);
        pan1.add(scrollPane);
        
        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Hospital_Management();
                setVisible(false);
            }
        });
        
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
          try{
         String idd=tf2.getText();
        String name = tf.getText();
        String aggge = tf1.getText();
        String address = tf3.getText();
          Class.forName("com.mysql.cj.jdbc.Driver");
      
         
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            String sql="update labratory set patientName='"+name+"',  type='"+aggge+"', result='"+address+"' where channelNo='"+idd+"'";
            PreparedStatement statement=con.prepareStatement(sql);
           //Pst=conn.prepareStatement();
          int rowsupdated= statement.executeUpdate(sql);
          if(rowsupdated>0){
          JOptionPane.showMessageDialog(null, "Saved"); 
            table1() ;
          }else{
                JOptionPane.showMessageDialog(null, "you didn't update any thing");}
                     } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(DoctorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
               
                
                
                     }catch (SQLException ex) {
                    Logger.getLogger(PatientRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
        });
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) { // If a row is selected
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    tf2.setText(model.getValueAt(selectedRow, 0).toString());
                    tf.setText(model.getValueAt(selectedRow, 1).toString());
                    tf1.setText(model.getValueAt(selectedRow, 2).toString());
                     tf3.setText(model.getValueAt(selectedRow, 3).toString());
                  
                    
                }
            }
        });
        
        
        pan.setLayout(new GridLayout(5,2,5,6));
        setLayout(new BorderLayout());
    add(pan,BorderLayout.CENTER);
    add(main,BorderLayout.SOUTH);
    add(pan1,BorderLayout.EAST);
        setSize(750, 350);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    
    
    public void table1() {
        try {
            



            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM labratory");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("channelNo");
            model.addColumn("patientName");
            model.addColumn("type");
            model.addColumn("result");
            
            while (rs.next()) {
                Object[] rowData = new Object[4];
                rowData[0] = rs.getString("channelNo");
                rowData[1] = rs.getString("patientName");
                rowData[2] = rs.getString("type");
                rowData[3] = rs.getString("result");
               
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
    
}
