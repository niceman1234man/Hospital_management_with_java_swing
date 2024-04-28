package hospital_management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class DoctorList extends JFrame {
    private JButton close = new JButton("Close");
    private JPanel pan = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JTable table = new JTable();

    DoctorList() {
       table1();
        // Wrap the table in a JScrollPane
        table.setBackground(Color.ORANGE);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the panel
        pan.setLayout(new BorderLayout());
        pan.add(scrollPane, BorderLayout.CENTER);

        // Add the close button to the button panel
        buttonPanel.add(close);

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new Recieption();
                setVisible(false);
            }
        });

        // Add the button panel to the panel
        pan.add(buttonPanel, BorderLayout.SOUTH);

        // Add the panel to the frame
        add(pan);

        setTitle("Doctor List");
        setSize(500, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
     public void table1() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM doctor");
            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Name");
            model.addColumn("Phone");
            model.addColumn("Gender");
            model.addColumn("Address");
            model.addColumn("Specialization");

            while (rs.next()) {
                Object[] rowData = new Object[5];
                rowData[0] = rs.getString("Name");
                rowData[1] = rs.getString("phone_no");
                rowData[2] = rs.getString("gender");
                rowData[3] = rs.getString("address");
                rowData[4] = rs.getString("specialization");
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
            new DoctorList();
        });
    }
}