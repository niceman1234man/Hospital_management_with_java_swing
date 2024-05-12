package hospital_management;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewInventory extends JFrame {
    private JPanel pan = new JPanel();
    private JPanel pan1 = new JPanel();
    private JButton confirm = new JButton("Confirm");
    private JButton close = new JButton("Close");
    private JTable table = new JTable();

    ViewInventory() {
        setLayout(new BorderLayout());
        table1();
        JScrollPane scrollPane = new JScrollPane(table);
        pan.setLayout(new FlowLayout());
        pan1.add(scrollPane);
        pan.add(confirm);
        pan.add(close);

        setTitle("View Inventory");
        setLayout(new BorderLayout());
        add(pan1, BorderLayout.CENTER);
       add(pan, BorderLayout.SOUTH);
        setSize(650, 350);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedIndex = table.getSelectedRow();
                    if (selectedIndex != -1) {
                        DefaultTableModel d1 = (DefaultTableModel) table.getModel();
                        String chno = d1.getValueAt(selectedIndex, 0).toString();
                        String s = "Payed";
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
                        String sql = "UPDATE inventory SET pay=? WHERE pres_id=?";
                        PreparedStatement statement = con.prepareStatement(sql);
                        statement.setString(1, s);
                        statement.setString(2, chno);
                        int rowsUpdated = statement.executeUpdate();
                        if (rowsUpdated > 0) {
                            JOptionPane.showMessageDialog(null, "Confirmed");
                            table1();
                        } else {
                            JOptionPane.showMessageDialog(null, "You didn't update anything");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select a row.");
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ViewInventory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Recieption doctorForm = new Recieption();
                doctorForm.setVisible(true);
                setVisible(false);
            }
        });
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