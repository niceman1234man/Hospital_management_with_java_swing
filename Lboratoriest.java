
package hospital_management;

import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.table.DefaultTableModel;

public class Lboratoriest extends JFrame {
   private JButton save = new JButton("Assign");
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
    Lboratoriest(String a,String b){
         table1();
        JScrollPane scrollPane = new JScrollPane(table);
        tf2.setText(a);
        tf.setText(b);
        tf3.setEditable(false);
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
                new Doctor();
                setVisible(false);
            }
        });
        
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
         try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
                Statement stmt = con.createStatement();

             String insert = "INSERT INTO labratory VALUES('" + tf2.getText() + "','" + tf.getText() + "','" + tf1.getText() + "','" + tf3.getText() +  "')";
                stmt.executeUpdate(insert);
                JOptionPane.showMessageDialog(null, "ADDED SUCCESSFULLY!");

               
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
            }
          table1();
             }
        });
        
        
        pan.setLayout(new GridLayout(5,2,5,6));
        setLayout(new BorderLayout());
        add(pan1,BorderLayout.EAST);
    add(pan,BorderLayout.CENTER);
    add(main,BorderLayout.SOUTH);
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
