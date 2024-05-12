package hospital_management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class PatientRegistrationForm extends JFrame {
 JLabel ageLabel = new JLabel("Age:");
    JTextField nameField; 
    private JComboBox<String> gender=new JComboBox();
    private JTextField age;
    private JTextField id;
    private JTable table = new JTable();
    private JTextField addressField;
    private JTextField contactField;
    private JButton registerButton;
    private JButton update = new JButton("UPDATE");
    private JButton clear = new JButton("CLEAR");
    private JButton exit = new JButton("EXIT");
    private JButton delete = new JButton("DELETE");
    private JButton search = new JButton("SEARCH");
    private JPanel p=new JPanel();
    public PatientRegistrationForm() {
       
        JLabel imageLabel = new JLabel();
        // Load the image from a file
        ImageIcon imageIcon = new ImageIcon("C://Users/Hirut Tarekegn/Desktop/javaproject1/download.jfif");
        // Set the image icon to the JLabel
        imageLabel.setIcon(imageIcon);
        p.add(imageLabel);
        p.setBackground(Color.LIGHT_GRAY);
         table1();
        JScrollPane scrollPane = new JScrollPane(table);
         String[] sex={"Male","Female"};
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
         gender=new JComboBox<>(sex);
         JLabel patietnnum = new JLabel("patientNO:");
         id=new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel genderLabel = new JLabel("Gender:");
      
       
        age= new JTextField();
        
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField();
        JLabel contactLabel = new JLabel("Contact:");
        contactField = new JTextField();
        formPanel.add(patietnnum);
        formPanel.add(id);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(genderLabel);
        formPanel.add(gender);
        //formPanel.add(new JLabel()); // Empty label for spacing
       // formPanel.add(femaleRadioButton);
        formPanel.add(ageLabel);
        formPanel.add(age);
        Color c1=new Color(250,100,255);
        formPanel.setBackground(c1);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(contactLabel);
        formPanel.add(contactField);
         autoID();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        Color c=new Color(250,0,255);
        buttonPanel.add(update);
        buttonPanel.add(delete);
        buttonPanel.add(clear);
        buttonPanel.add(exit);
        buttonPanel.add(search);
        buttonPanel.setBackground(c);
        registerButton = new JButton("Register");
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) { // If a row is selected
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    id.setText(model.getValueAt(selectedRow, 0).toString());
                    nameField.setText(model.getValueAt(selectedRow, 1).toString());
                    gender.setSelectedItem(model.getValueAt(selectedRow, 2));
                    age.setText(model.getValueAt(selectedRow, 3).toString());
                    addressField.setText(model.getValueAt(selectedRow, 4).toString());
                    contactField.setText(model.getValueAt(selectedRow, 5).toString());

                    
                }
            }
        });
        
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              registerPatient();
              table1();
            }
        });
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
         try{
         String idd=id.getText();
        String name = nameField.getText();
        String gend = (String) gender.getSelectedItem();
        String aggge = age.getText();
        String address = addressField.getText();
        String contact = contactField.getText();
          Class.forName("com.mysql.cj.jdbc.Driver");
         
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            String sql="update patient set patient_Full_name='"+name+"',  gender='"+gend+"', age='"+aggge+"', adrress='"+address+"',contact='"+contact+"' where patientNo='"+idd+"'";
            PreparedStatement statement=con.prepareStatement(sql);
           //Pst=conn.prepareStatement();
          int rowsupdated= statement.executeUpdate(sql);
          if(rowsupdated>0){
          JOptionPane.showMessageDialog(null, "updated"); 
          table1();
          }else{
                JOptionPane.showMessageDialog(null, "you didn't update any thing");}
                     } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(DoctorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
               
                
                
                     }catch (SQLException ex) {
                    Logger.getLogger(PatientRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
          id.setText("");
        nameField.setText("");
        gender.setSelectedIndex(0);
        age.setText("");
        addressField.setText("");
        contactField.setText("");

            }
        });
        
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                try {
    
         Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
       PreparedStatement pst=con.prepareStatement("select * from patient where patientNo='"+id.getText()+"'");
        // String sql="select * from login where S_id="+btn3.getText()+"'"+" and enrollment=extention";
        Statement st = con.createStatement();
       ResultSet rs=pst.executeQuery();
        if(rs.next()){
        String str="select * from doctor where patientNo='"+id.getText()+"'";
        ResultSet result=st.executeQuery(str);
        while(result.next()){
            String Name=result.getString(2);
            String agender=result.getString(3);
            String agae=result.getString(4);
            String address=result.getString(5);
           String contact=result.getString(6);
       
        nameField.setText("");
        gender.setSelectedIndex(0);
        age.setText("");
        addressField.setText("");
        contactField.setText("");
           
                 }
      
       }
        else {
         JOptionPane.showMessageDialog(null, "this id is not found in the database");   
        }
}               catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(DoctorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DoctorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
   String sql="delete from patient where patientNo=?";
    // preparedStatement   Statement  = con.createStatement();  
       PreparedStatement Statement=con.prepareStatement(sql);
       Statement.setString(1,id.getText());
         //pst.execute();
            //JOptionPane.showMessageDialog(null,"Deleted");
           int rowsdeleted=  Statement.executeUpdate();
           
            if(rowsdeleted> 0){
             JOptionPane.showMessageDialog(null,"deleted successfully"); 
             table1();
            }else{
                JOptionPane.showMessageDialog(null,"you deleted nothing"); 
            }} catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(DoctorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DoctorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                  }else {
             JOptionPane.showMessageDialog(null,"you deleted nothing");           }
            
            }
        });
        
         exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Recieption recieption = new  Recieption();
                 setVisible(false);
                
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(p,BorderLayout.PAGE_START);
        add(mainPanel, BorderLayout.CENTER);
        add(registerButton, BorderLayout.SOUTH);
        add(scrollPane,BorderLayout.EAST);
        setTitle("Patient Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 550);
        setLocationRelativeTo(null);
        
    }

    public void registerPatient() {
        String idd=id.getText();
        String name = nameField.getText();
        String gend = (String) gender.getSelectedItem();
        String aggge = age.getText();
        String address = addressField.getText();
        String contact = contactField.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        
               
Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
Statement stmt=(Statement)con.createStatement();


       String insert=("insert into patient VALUES('" + idd + "','" + name + "','" + gend + "','" +aggge+"','" + address+
                "','" + contact + "')");
       stmt.executeUpdate(insert);

 }      catch (SQLException ex) {
     JOptionPane.showMessageDialog(null,"Patient number is duplicated give other");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DoctorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
     JOptionPane.showMessageDialog(this, "Patient registered successfully!");

        // Clear the form fields after registration
        nameField.setText("");
        //maleRadioButton.setSelected(true);
        age.setText("");
        addressField.setText("");
        contactField.setText("");
    }
     public void table1() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM patient");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("patientNo");
            model.addColumn("patient_Full_name");
            model.addColumn("gender");
            model.addColumn("age");
            model.addColumn("adrress");
            model.addColumn("contact");
            while (rs.next()) {
                Object[] rowData = new Object[6];
                rowData[0] = rs.getString("patientNo");
                rowData[1] = rs.getString("patient_Full_name");
                rowData[2] = rs.getString("gender");
                rowData[3] = rs.getString("age");
                rowData[4] = rs.getString("adrress");
                rowData[5] = rs.getString("contact");
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
    public void autoID() {
    try {
        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Establish a connection to the database
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
        
        // Prepare a statement to retrieve the maximum patientNo from the patient table
        PreparedStatement pst = con.prepareStatement("SELECT MAX(patientNo) FROM patient");
        
        // Execute the query and retrieve the result set
        ResultSet rs = pst.executeQuery();
        rs.next();
        
        // Get the maximum patientNo value from the result set
        String maxPatientNo = rs.getString("MAX(patientNo)");
        
        // Check if the maximum patientNo is null
        if (maxPatientNo == null) {
            // f it's null, set the ID to "ps001"
            id.setText("ps001");
        } else {
            // Extract the numeric portion of the maximum patientNo and increment it
            long n = Long.parseLong(maxPatientNo.substring(2));
            n++;
            
            // Format the incremented number as a three-digit string and set the ID
            id.setText("ps" + String.format("%03d", n));
        }
        
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(PatientRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(PatientRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}
