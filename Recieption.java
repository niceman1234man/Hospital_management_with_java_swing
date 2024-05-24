package hospital_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.*;
public class Recieption extends JFrame {
    private JPanel pan = new JPanel();
    private JButton patient = new JButton("PATIENT");
    private JButton createChanel = new JButton("Create Chanel");
    private JButton viewDoctor = new JButton("View Doctor");
    private JButton apoint = new JButton("view appintment");
     private JButton viewInventory = new JButton("viewInventory");
    private JButton logOut = new JButton("LOGOUT");
    private JPanel ta = new JPanel();
    private JPanel main = new JPanel();   
    Recieption(){   
        JLabel imageLabel = new JLabel();
        
        ImageIcon imageIcon = new ImageIcon("C://Users/Hirut Tarekegn/Desktop/javaproject1/images (3).jfif");
        
        // Set the image icon to the JLabel
        imageLabel.setIcon(imageIcon);
       
        main.setLayout(new GridLayout(1,2,5,6));
      ta.add(imageLabel);
        pan.setLayout(null);
        patient.setBounds(10, 45, 120, 25);
        createChanel.setBounds(10, 75, 120, 25);

        viewDoctor.setBounds(10, 105, 120, 25);
       apoint.setBounds(10, 135, 120, 25);
       viewInventory.setBounds(10, 165, 120, 25);
        logOut.setBounds(10, 195, 120, 25);
        pan.setBackground(Color.pink);
        pan.add(patient);
        pan.add(createChanel);
        pan.add(viewDoctor);
        pan.add(apoint);
        pan.add(viewInventory);
        pan.add(logOut);
        
       main.add(pan);
       main.add(ta);
      
patient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            PatientRegistrationForm rf=   new PatientRegistrationForm();
            rf.setVisible(true);
            setVisible(false);
            }
        });
viewInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
               new ViewInventory();
               
                setVisible(false);
          }
        });
        
apoint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             new View_appointment();   
             setVisible(false);
            }
        });
createChanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                     new Create_NewChannel();
               
                     setVisible(false);
            }
        });
viewDoctor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            new DoctorList();
            setVisible(false);
            }
        });

logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
         Hospital_Management hm=  new Hospital_Management();
              setVisible(false);
            }
        });
          add(main);
        setSize(400, 350);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void a(){}
}
