package hospital_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class View_Prescription extends JFrame {
    private JButton inventory = new JButton("Inventory");
    private JButton cancel = new JButton("Close");

    private JPanel pan = new JPanel();
    private JTable table = new JTable();

    View_Prescription() {
        table1();

        JScrollPane scrollPane = new JScrollPane(table);

        pan.setLayout(new BorderLayout());
        pan.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(inventory);
        buttonPanel.add(cancel);
        pan.add(buttonPanel, BorderLayout.PAGE_END);

        inventory.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel d1 = (DefaultTableModel) table.getModel();
        int selectedIndex = table.getSelectedRow();

        if (selectedIndex != -1) {
            String pid = d1.getValueAt(selectedIndex, 0).toString();
            new Inventory(pid);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
    }
});

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            new Pharmacist();
            }
        });

        add(pan);
        setTitle("View_Prescription");
        setSize(400, 250);
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

}