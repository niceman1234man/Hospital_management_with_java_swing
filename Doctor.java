package hospital_management;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Doctor extends JFrame {

    JLabel labLL = new JLabel("WELCOME TO HOSPITAL MANAGEMENT SYSTEM ");

//    private JButton doctor = new JButton("Doctor");
    private JButton viewchanel = new JButton("View Chanel");
//    private JButton viewdoctor = new JButton("View Doctor");
    private JButton logout = new JButton("LogOut");
    private JPanel ta = new JPanel();
    private JScrollPane scrollPane; // New scroll pane
    private JPanel pan = new JPanel();
    private JPanel buttonPanel = new JPanel();
//    private JLabel l = new JLabel("User name:-");
    private JLabel l2 = new JLabel();
    private JLabel l3 = new JLabel("WELCOME !");
    private JLabel l4 = new JLabel();

    Doctor() {
        JLabel imageLabel = new JLabel();
        // Load the image from a file
        ImageIcon imageIcon = new ImageIcon("C://Users/Hirut Tarekegn/Desktop/javaproject1/images (1).jfif");
        // Set the image icon to the JLabel
        imageLabel.setIcon(imageIcon);
        buttonPanel.setLayout(new GridLayout(4, 1, 5, 6));
        ta.setLayout(new GridLayout(2, 2, 5, 6));
//        l2.setText(a);
//        l4.setText(b);
//        ta.add(l);
        ta.add(l2);
        ta.add(l3);
        ta.add(l4);
//        buttonPanel.add(doctor);
        buttonPanel.add(viewchanel);
//        buttonPanel.add(viewdoctor);
        buttonPanel.add(logout);
        buttonPanel.setBackground(Color.blue);
        // Make the text area read-only
        ta.setBackground(Color.WHITE);
        // Create a border for the text area
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        ta.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        // Create a scroll pane and add the text area to it
        scrollPane = new JScrollPane(ta);

        pan.setLayout(new BorderLayout());
        pan.add(labLL, BorderLayout.PAGE_START);
        pan.add(scrollPane, BorderLayout.CENTER); // Add the scroll pane instead of the text area

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(imageLabel, BorderLayout.NORTH);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        pan.add(leftPanel, BorderLayout.WEST);

//        doctor.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                DoctorForm doctorForm = new DoctorForm();
//                doctorForm.setVisible(true);
//                setVisible(false);
//            }
//        });

        viewchanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new View_Channel();
                setVisible(false);
            }
        });

//        viewdoctor.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                new Doctor();
//            }
//        });

        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Hospital_Management();
                setVisible(false);
            }
        });

        add(pan);
        setTitle("HOSPITAL");
        setSize(450, 450);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}