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

public class SalesReport extends JFrame {
    private JButton close = new JButton("Close");
    private JButton logout = new JButton("LogOut");
    private JTable table = new JTable();
    private JPanel pan = new JPanel();

    SalesReport() {
        table1();

        JScrollPane scrollPane = new JScrollPane(table); // Wrap the JTable in a JScrollPane

        pan.setLayout(new BorderLayout());
        pan.add(scrollPane, BorderLayout.CENTER); // Add the JScrollPane to the JPanel

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(close);
        buttonPanel.add(logout);
        pan.add(buttonPanel, BorderLayout.PAGE_END);

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                   new Pharmacist();
            }
        });

        add(pan);
        setTitle("Sales Report");
        setSize(300, 200);
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
            model.addColumn("presid");
            model.addColumn("channel_id");
            model.addColumn("doctor_name");
            model.addColumn("des_type");
            model.addColumn("description");

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