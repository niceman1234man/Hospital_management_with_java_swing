package hospital_management;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateUser extends JFrame implements ActionListener {
    private JTextField uname;
    private JComboBox<String> userTypeComboBox;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton btnCancel;
    private JButton close;
    private JButton loginButton;

    public CreateUser() {
        setTitle("Login");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel nameLabel = new JLabel("Name");
        uname = new JTextField(8);

        JLabel userLabel = new JLabel("Username");
        usernameField = new JTextField(8);

        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(10);

        JLabel userTypeLabel = new JLabel("User Type");
        String[] userTypes = {"Doctor", "Admin", "Pharmacist", "Receptionist",""};
        userTypeComboBox = new JComboBox<>(userTypes);

        loginButton = new JButton("Create User");
        loginButton.addActionListener(this);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);

        close = new JButton("Close");

        panel.setBackground(Color.red);
        panel.add(nameLabel);
        panel.add(uname);
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(userTypeLabel);
        panel.add(userTypeComboBox);
        panel.add(loginButton);
        panel.add(btnCancel);
        panel.add(close);

        add(panel);

        setVisible(true);

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Admin();
                setVisible(false);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            uname.setText("");
            usernameField.setText("");
            passwordField.setText("");
            userTypeComboBox.setSelectedIndex(0);
        } else if (e.getSource() == loginButton) {
            // Handle create user button action
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project", "root", "1234");
                Statement stmt = con.createStatement();

                String insert = "INSERT INTO user VALUES('" + uname.getText() + "','" + usernameField.getText() + "','" + passwordField.getText() +
                        "','" + (String) userTypeComboBox.getSelectedItem() + "')";
                stmt.executeUpdate(insert);
                JOptionPane.showMessageDialog(null, "ADDED SUCCESSFULLY!");

                System.out.println("Added successfully!");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}