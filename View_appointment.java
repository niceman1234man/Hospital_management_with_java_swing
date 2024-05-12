
package hospital_management;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class View_appointment extends JFrame{
  private JButton close = new JButton("close");
  private JPanel pan = new JPanel();
  private JTable table = new JTable();
   public View_appointment(){
       table1();
       JScrollPane scrollPane = new JScrollPane(table);
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           new Recieption();
           setVisible(false);
            }
        });
        pan.setLayout(new BorderLayout());
    pan.add(close,BorderLayout.SOUTH);
    pan.add(scrollPane ,BorderLayout.CENTER);
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
            PreparedStatement pst = con.prepareStatement("SELECT * FROM appointment");
            ResultSet rs = pst.executeQuery();
            




            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("channelNo");
            model.addColumn("patientName");
            model.addColumn("doctorName");
            model.addColumn("date");
            

            while (rs.next()) {
                Object[] rowData = new Object[4];
                rowData[0] = rs.getString("channelNo");
                rowData[1] = rs.getString("patientName");
                rowData[2] = rs.getString("doctorName");
                rowData[3] = rs.getString("date");
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
