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

public class CreateItem extends JFrame {
    private JLabel lab = new JLabel("Item ID ");
    private JTextField tf = new JTextField();

    private JLabel lab1 = new JLabel("Item Name ");
    private JTextField tf1 = new JTextField();

    private JLabel lab2 = new JLabel("Description ");
    private JTextArea ta = new JTextArea();

    private JLabel lab3 = new JLabel("Sell Price  ");
    private JTextField tf3 = new JTextField();

    private JLabel lab4 = new JLabel("Buy Price ");
    private JTextField tf4 = new JTextField();

    private JLabel lab5 = new JLabel("Quantity ");
    private JTextField tf5 = new JTextField();

    private JButton save = new JButton("Save");
        private JButton close = new JButton("CLOSE");

    private JButton update = new JButton("UPDATE");
    private JButton delete = new JButton("DELETE");
    private JButton clear = new JButton("CLEAR");
    private JPanel pan = new JPanel();
    private JTable table = new JTable();
    private JPanel pan2 = new JPanel();
     private JPanel pan3 = new JPanel();
    private JLabel labLL = new JLabel("HOSPITAL MANAGEMENT SYSTEM ");


    CreateItem() {
        table1();

        pan2.setSize(250, 200);
        JScrollPane scrollPane = new JScrollPane(table);
        pan2.add(scrollPane);
        scrollPane.setBackground(Color.cyan);
        pan3.add(labLL);
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(lab);
        inputPanel.add(tf);
        inputPanel.add(lab1);
        inputPanel.add(tf1);
        inputPanel.add(lab2);
        inputPanel.add(ta);
        inputPanel.add(lab3);
        inputPanel.add(tf3);
        inputPanel.add(lab4);
        inputPanel.add(tf4);
        inputPanel.add(lab5);
        inputPanel.add(tf5);
        inputPanel.setBackground(Color.ORANGE);
        
        scrollPane.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel d1 = (DefaultTableModel)table.getModel();
                int row = table.getSelectedRow();
                String id = d1.getValueAt(row, 0).toString();
                String name = d1.getValueAt(row, 1).toString();
                String desc = d1.getValueAt(row, 2).toString();
                String sell = d1.getValueAt(row, 3).toString();
                String buy = d1.getValueAt(row, 4).toString();
                String qut = d1.getValueAt(row, 5).toString();
                tf.setText(id);
                tf1.setText(name);
                ta.setText(desc);
                tf3.setText(sell);
                tf4.setText(buy);
                tf5.setText(qut);
            }
        });
        
       
        // Set preferred size for text fields
        tf.setPreferredSize(new Dimension(150, 25));
        tf1.setPreferredSize(new Dimension(150, 25));
        tf3.setPreferredSize(new Dimension(150, 25));
        tf4.setPreferredSize(new Dimension(150, 25));
        tf5.setPreferredSize(new Dimension(150, 25));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(save);
        buttonPanel.add(update);
        buttonPanel.add(delete);
        buttonPanel.add(clear);
         buttonPanel.add(close);

        pan.setLayout(new BorderLayout());
        pan.add(inputPanel, BorderLayout.WEST);
        pan.add(buttonPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(pan, BorderLayout.WEST);
        add(pan2, BorderLayout.CENTER);
        add(pan3,BorderLayout.PAGE_START);
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) { // If a row is selected
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    tf.setText(model.getValueAt(selectedRow, 0).toString());
                    tf1.setText(model.getValueAt(selectedRow, 1).toString());
                    ta.setText(model.getValueAt(selectedRow, 2).toString());
                    tf3.setText(model.getValueAt(selectedRow, 3).toString());
                    tf4.setText(model.getValueAt(selectedRow, 4).toString());
                     tf5.setText(model.getValueAt(selectedRow, 5).toString());
                    
                }
            }
        });
        
        
        
       save.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        Connection con = null;
        Statement stmt = null;
        table1();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            stmt = con.createStatement();

            String insert = "INSERT INTO item VALUES('" + tf.getText() + "','" + tf1.getText() + "','"
                    + ta.getText() + "','" + tf3.getText() + "','" + tf4.getText() + "','" + tf5.getText() + "')";
            stmt.executeUpdate(insert);
            JOptionPane.showMessageDialog(null, "SAVED SUCCESSFULLY!");
            table1();
            tf.setText("");
            tf1.setText("");
            ta.setText("");
            tf3.setText("");
            tf4.setText("");
            tf5.setText("");
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
        DefaultTableModel d1 = (DefaultTableModel) table.getModel();
        int selectedIndex = table.getSelectedRow();
         if (selectedIndex != -1) {
             
             try{
    
          String value1= tf.getText();
            String value2=tf1.getText();
            String value3= ta.getText();
            String value4=tf3.getText();
             String value5=tf4.getText();
             String value6=tf5.getText();
            
             //String value6=jTextField6.getText();               
         Class.forName("com.mysql.cj.jdbc.Driver");
  Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            String sql="update item set item_name='"+value2+"', descriptionn='"+value3+"',  sellprice='"+value4+"', buy_price='"+value5+"',quantity='"+value6+"' where itemid='"+value1+"'";
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
 
                
             
             
             
            
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                int result = JOptionPane.showConfirmDialog(null,"Sure? You want to delete?", "Swing Tester",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                
Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
   String sql="delete from item where itemid=?";
    // preparedStatement   Statement  = con.createStatement();  
       PreparedStatement Statement=con.prepareStatement(sql);
       Statement.setString(1,tf.getText());
         //pst.execute();
            //JOptionPane.showMessageDialog(null,"Deleted");
           int rowsdeleted=  Statement.executeUpdate();
           
            if(rowsdeleted> 0){
             JOptionPane.showMessageDialog(null,"deleted successfully");  
              table1();
            }else{
                JOptionPane.showMessageDialog(null,"you deleted nothing");
                
            }
            } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DoctorForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(DoctorForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                }else {
             JOptionPane.showMessageDialog(null,"you deleted nothing");           }
        
                
                
            }
        });
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                new Pharmacist();
                setVisible(false);
                
            }
                  });
            
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               tf.setText("");
                tf1.setText("");
                ta.setText("");
                tf3.setText("");
                tf4.setText("");
                tf5.setText("");
            }
        });

        setSize(870, 350);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void table1() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM item");
            ResultSet rs = pst.executeQuery();
        
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("itemid");
            model.addColumn("item_name");
            model.addColumn("descriptionn");
            model.addColumn("sellprice");
            model.addColumn("buy_price ");
             model.addColumn("quantity ");

            while (rs.next()) {
                Object[] rowData = new Object[6];
                rowData[0] = rs.getString("itemid");
                rowData[1] = rs.getString("item_name");
                rowData[2] = rs.getString("descriptionn");
                rowData[3] = rs.getString("sellprice");
                rowData[4] = rs.getString("buy_price");
                rowData[5] = rs.getString("quantity");
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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CreateItem();
            }
        });
    }
}