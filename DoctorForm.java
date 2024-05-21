package hospital_management;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class DoctorForm extends JFrame {
    private JTextField nameField;
    private JTextField phoneno;
    private JTextArea addressArea;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> departmentComboBox;
    private JButton saveButton;
    private JButton updateButton = new JButton("UPDATE");
    private JButton clearButton = new JButton("CLEAR");
    private JButton exitButton = new JButton("EXIT");
    private JButton deleteButton = new JButton("DELETE");
    private JButton searchButton = new JButton("SEARCH");
    private JTable table = new JTable();
    private JPanel pan3 = new JPanel();

    public DoctorForm() {
        table1();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.PINK);
        pan3.add(scrollPane);
        pan3.setBackground(Color.ORANGE);

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));

        JLabel nameLabel = new JLabel("Name");
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(150, 25)); // Increase the size of the text field
        
        JLabel phonenoLabel = new JLabel("docNo");
        phoneno = new JTextField();
        phoneno.setPreferredSize(new Dimension(150, 25)); // Increase the size of the text field
        JLabel genderLabel = new JLabel("Gender");
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female"});
        JLabel addressLabel = new JLabel("Address");
        addressArea = new JTextArea();
        addressArea.setPreferredSize(new Dimension(150, 100)); // Increase the size of the text area
        
        JLabel departmentLabel = new JLabel("Department");
        departmentComboBox = new JComboBox<>(new String[]{"Cardiology", "Neurology", "Orthopedics", "Pediatrics"});
formPanel.setBackground(Color.MAGENTA);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(phonenoLabel);
        formPanel.add(phoneno);
        formPanel.add(genderLabel);
        formPanel.add(genderComboBox);
        formPanel.add(addressLabel);
        formPanel.add(new JScrollPane(addressArea));
        formPanel.add(departmentLabel);
        formPanel.add(departmentComboBox);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Admin();
                setVisible(false);
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                 nameField.setText("");
             phoneno.setText("");
            genderComboBox.setSelectedItem(null);
             addressArea.setText("");
             departmentComboBox.setSelectedItem(null);
            }
        });
     updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {   
              try{
          String value1= nameField.getText();
            String value2=phoneno.getText();
            String value3= genderComboBox.getSelectedItem().toString();
            String value4=addressArea.getText();
             String value5=departmentComboBox.getSelectedItem().toString();       
             //String value6=jTextField6.getText();               
         Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            String sql="update doctor set Name='"+value1+"', gender='"+value3+"',  address='"+value4+"', specialization='"+value5+"' where phone_no='"+value2+"'";
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
            }
        });
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDoctor();
                table1();
            }
        });
        
         table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) { // If a row is selected
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    nameField.setText(model.getValueAt(selectedRow, 0).toString());
                    phoneno.setText(model.getValueAt(selectedRow, 1).toString());
                    genderComboBox.setSelectedItem(model.getValueAt(selectedRow, 2));
                    addressArea.setText(model.getValueAt(selectedRow, 3).toString());
                    departmentComboBox.setSelectedItem(model.getValueAt(selectedRow, 4));
                }
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
        
                int result = JOptionPane.showConfirmDialog(null,"Sure? You want to delete?", "Swing Tester",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
//               label.setText("You selected: Yes");
//            }else if (result == JOptionPane.NO_OPTION){
//               label.setText("You selected: No");
//            }else {
//               label.setText("None selected");
//            }
//         }    
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                
Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
   String sql="delete from doctor where phone_no=?";
    // preparedStatement   Statement  = con.createStatement();  
       PreparedStatement Statement=con.prepareStatement(sql);
       Statement.setString(1,phoneno.getText());
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
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        
         
                try {
                    // connection string
                    Class.forName("com.mysql.cj.jdbc.Driver");
                
Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
       PreparedStatement pst=con.prepareStatement("select * from doctor where phone_no='"+phoneno.getText()+"'");
        Statement st = con.createStatement();
       ResultSet rs=pst.executeQuery();
        if(rs.next()){
        String str="select * from doctor where phone_no='"+phoneno.getText()+"'";
        ResultSet result=st.executeQuery(str);
        while(result.next()){
            String fName=result.getString(1);
            String lName=result.getString(2);
            String department=result.getString(3);
            String position=result.getString(4);
           String salary=result.getString(5);
            nameField.setText(fName);
             phoneno.setText(lName);
            genderComboBox.setSelectedItem(department);
             addressArea.setText(position);
             departmentComboBox.setSelectedItem(salary);
                 }
       }
        else {
         JOptionPane.showMessageDialog(null, "this id is not found in the database");   
        }
 } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DoctorForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(DoctorForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(searchButton);

        setTitle("Doctor Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 350);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        add(formPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
        add(pan3, BorderLayout.EAST);
    }

    private void saveDoctor() {
        // Placeholder for saving doctor logic
        String name = nameField.getText();
        String phone = phoneno.getText();
        String gender = (String) genderComboBox.getSelectedItem();
        String address = addressArea.getText();
        String department = (String) departmentComboBox.getSelectedItem();
                      
               Connection con = null;
                Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
       
                    con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
                    stmt = con.createStatement();

                    String insert = "INSERT INTO doctor VALUES('" + name + "','" + phone + "','"
                            + gender + "','" + address + "','" + department + "')";
                    stmt.executeUpdate(insert);
                    JOptionPane.showMessageDialog(null, "ADDED SUCCESSFULLY!");

                    System.out.println("added successfully!");
                     } catch (ClassNotFoundException ex) {
            Logger.getLogger(DoctorForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DoctorForm.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    private void clearFields() {
        // Placeholder for clearing form fields logic
        nameField.setText("");
        phoneno.setText("");
        addressArea.setText("");
        genderComboBox.setSelectedIndex(0);
        departmentComboBox.setSelectedIndex(0);
    }

    private void searchDoctor() {
        // Placeholder for searching doctor logic
        // Implement your search logic here
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DoctorForm doctorForm = new DoctorForm();
                doctorForm.setVisible(true);
            }
        });
    }

    public void table1() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM doctor");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Name");
            model.addColumn("Phone No");
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
}
