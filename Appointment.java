
package hospital_management;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;





public class Appointment extends JFrame {
     private JTable table = new JTable();
    private JButton add = new JButton("ADD");
    private JButton update = new JButton("Update");
    private JButton close = new JButton("close");
    private JLabel lab1 = new JLabel("CHANNEL ID");
    private JTextField tf = new JTextField();
    private JLabel lab = new JLabel("patientName");
    private JTextField tf3 = new JTextField();
    private JLabel labb = new JLabel("DoctorName");
    private JTextField t4 = new JTextField();
    private JLabel lab2 = new JLabel("Date ");
    private JTextField tf1 = new JTextField();
     private JPanel pan = new JPanel();
      private JPanel pan1 = new JPanel();
    
    public Appointment(String a,String b,String c){
        
            
        
        
        
        tf.setText(a);
        tf3.setText(b);
        t4.setText(c);
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Connection con = null;
        Statement stmt = null;
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            stmt = con.createStatement();

            String insert = "INSERT INTO appointment VALUES('" + tf.getText() + "','" + tf3.getText() + "','"
                    + t4.getText() + "','" + tf1.getText() + "')";
            stmt.executeUpdate(insert);
            JOptionPane.showMessageDialog(null, "SAVED SUCCESSFULLY!");
            
            tf.setText("");
            tf3.setText("");
            t4.setText("");
            tf1.setText("");          
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DoctorForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DoctorForm.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DoctorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                 
            }
        });
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                     
         
          String value1= tf.getText();;
            String value2=tf3.getText();
            String value3=  t4.getText();
            String value4= tf1.getText();
             
             //String value6=jTextField6.getText();               
         Class.forName("com.mysql.cj.jdbc.Driver");
  



        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            String sql="update appointment set  patientName='"+value2+"',  doctorName='"+value3+"', date='"+value4+"' where channelNo='"+value1+"'";
            PreparedStatement statement=con.prepareStatement(sql);
           //Pst=conn.prepareStatement();
          int rowsupdated= statement.executeUpdate(sql);
          if(rowsupdated>0){
          JOptionPane.showMessageDialog(null, "updated");  
           
          }else{
                JOptionPane.showMessageDialog(null, "you didn't update any thing");}
                     } catch (SQLException ex) {
                    Logger.getLogger(Prescription.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Prescription.class.getName()).log(Level.SEVERE, null, ex);
                }
   
            tf.setText("");
            tf3.setText("");
            t4.setText("");
            tf1.setText(""); 
                 
            }
        });
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new View_Channel();
                setVisible(false);
                 
            }
        });
        
       
        pan.setLayout(new GridLayout(4,2,5,6));
        pan.add(lab1);
         pan.add(tf);
        pan.add(lab);
        pan.add(tf3);
        pan.add(labb);
        pan.add(t4);
        pan.add(lab2);
        pan.add(tf1);
        pan1.add(add);
        pan1.add(update);
        pan1.add(close);
        add(pan,BorderLayout.CENTER);
        add(pan1,BorderLayout.SOUTH);
        setSize(400, 350);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        
    
    }
}
