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

public class Inventory extends JFrame {
    
    private JLabel lab = new JLabel("Prescription ID ");
    private JTextField tf = new JTextField();

    private JLabel lab1 = new JLabel("Drug Code ");
    private JTextField tf1 = new JTextField();

    private JLabel lab2 = new JLabel("Drug  Name ");
    private JTextField tf2 = new JTextField();

    private JLabel lab3 = new JLabel("Quantity  ");
    private JTextField tf3 = new JTextField();

    private JButton add = new JButton("ADD");
   
    private JLabel lab4 = new JLabel("Total Cost ");
    private JTextField tf4 = new JTextField();

    private JLabel lab5 = new JLabel("Pay ");
    private JTextField tf5 = new JTextField();

    private JTable table = new JTable();
    private JButton create = new JButton("Create");
    private JButton cancel = new JButton("Cancel");
    
    private JButton close = new JButton("Close");


    private JPanel pan = new JPanel();
    private JPanel pan2 = new JPanel();
    private JLabel labLL = new JLabel("HOSPITAL MANAGEMENT SYSTEM ");

    private JPanel pan1 = new JPanel(new BorderLayout());

    Inventory(String pid) {
        table1();
        tf5.setEditable(false);
        tf.setText(pid);
        pan2.add(labLL);
        
        pan.add(lab);
        pan.add(tf);
        pan.add(lab1);
        pan.add(tf1);

        pan.add(lab2);
        pan.add(tf2);

        pan.add(lab3);
        pan.add(tf3);

        pan.add(lab4);
        pan.add(tf4);

        pan.add(lab5);
        pan.add(tf5);
        pan.add(create);
        pan.add(cancel);
        pan.add(close);
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) { // If a row is selected
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    tf.setText(model.getValueAt(selectedRow, 0).toString());
                    tf1.setText(model.getValueAt(selectedRow, 1).toString());
                    tf2.setText(model.getValueAt(selectedRow, 2).toString());
                    tf3.setText(model.getValueAt(selectedRow, 3).toString());
                    tf4.setText(model.getValueAt(selectedRow, 4).toString());
                    tf5.setText(model.getValueAt(selectedRow, 5).toString());
                }
            }
        });
        
        pan.setLayout(new GridLayout(9, 2, 5, 6));
        
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tf.setText("");
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                tf5.setText("");
            }
        });
        
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new View_Prescription();
                setVisible(false);
            }
        });
        
        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double d;
                int n;
                
                Connection con = null;
                Statement stmt = null;
                table1();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
                    stmt = con.createStatement();
                    PreparedStatement pst = con.prepareStatement("SELECT * FROM item WHERE itemid = ?");
                    pst.setString(1, tf1.getText());
                    ResultSet rs = pst.executeQuery();
                    rs.next();
            
                    n = Integer.parseInt(tf3.getText());
                    d = Double.parseDouble(rs.getString(4)) * n;
           
                    String insert = "INSERT INTO inventory VALUES('" + tf.getText() + "','" + tf1.getText() + "','"
                            + tf2.getText() + "','" + tf3.getText() + "','" + d + "','" + tf5.getText() + "')";
                    stmt.executeUpdate(insert);
                    JOptionPane.showMessageDialog(null, "CREATED SUCCESSFULLY!");
                    tf.setText("");
                    tf1.setText("");
                    tf2.setText("");
                    tf3.setText("");
                    tf4.setText("");
                    tf5.setText("");
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
                table1();
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        pan1.add(pan, BorderLayout.WEST);
        pan1.add(scrollPane, BorderLayout.CENTER);
        pan1.add(pan2,BorderLayout.PAGE_START);
        add(pan1);
        setTitle("Inventory");
        setSize(650, 350);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void table1() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM inventory");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("pres_id");
            model.addColumn("drug_code");
            model.addColumn("drug_name");
            model.addColumn("quantity");
            model.addColumn("total_cost");
            model.addColumn("pay status");
            

            while (rs.next()) {
                Object[] rowData = new Object[6];
                rowData[0] = rs.getString("pres_id");
                rowData[1] = rs.getString("drug_code");
                rowData[2] = rs.getString("drug_name");
                rowData[3] = rs.getString("quantity");
                rowData[4] = rs.getString("total_cost");
                rowData[5] = rs.getString("pay");
                
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