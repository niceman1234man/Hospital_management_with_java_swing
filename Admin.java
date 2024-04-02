
package hospital_management;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Admin extends JFrame{
     private JPanel pan1 = new JPanel();

    private JPanel pan = new JPanel();
    private JButton logOut = new JButton("logOut");
    private JButton createUser = new JButton("Create user");
     private JButton doctor = new JButton("Doctor");
    
    Admin(){
          JLabel labLL = new JLabel("WELCOME TO\n HOSPITAL MANAGEMENT SYSTEM ");
        setLayout(new BorderLayout());
        pan1.add(labLL);
        // Create a JLabel for the image
        JLabel imageLabel = new JLabel();
        // Load the image from a file
        ImageIcon imageIcon = new ImageIcon("C://Users/Hirut Tarekegn/Desktop/javaproject1/images (4).jfif");
        // Set the image icon to the JLabel
        imageLabel.setIcon(imageIcon);
        pan1.add(imageLabel);
        pan.setLayout(new FlowLayout(FlowLayout.LEFT));
        createUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new CreateUser();
               setVisible(false);

            }
        });
        
        doctor.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        DoctorForm doctorForm = new DoctorForm();
        doctorForm.setVisible(true);
        setVisible(false);
    }
});
        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
         Hospital_Management hm=  new Hospital_Management();
              setVisible(false);
            }
        });
        pan.add(createUser);
        pan.add(doctor);
        pan.add(logOut);
        add(pan1,BorderLayout.CENTER);
        add(pan,BorderLayout.SOUTH);
        setSize(400, 350);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        
    }
     
}
