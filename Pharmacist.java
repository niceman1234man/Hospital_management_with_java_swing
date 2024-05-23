package hospital_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Pharmacist extends JFrame {
    private JButton viewPresc = new JButton("View Prescription");
    private JButton createItem = new JButton("Create Item");
    private JButton viewDoctor = new JButton("View Doctor");
    private JButton logout = new JButton("Log Out");
//    private JButton report = new JButton("Report");
    private JPanel pan = new JPanel();
    private JPanel pan1 = new JPanel(new GridLayout(2, 2, 5, 5));
    private JPanel pan3 = new JPanel();
     private JPanel p = new JPanel();
//  private JLabel l =new JLabel("User Type :-");
// JLabel l2= new JLabel("User Name :-");
// JLabel label = new JLabel();
//        JLabel label2 = new JLabel();
//     Pharmacist(){ 
//     label.setEnabled(false);
//     label2.setEnabled(false);
//     }
    Pharmacist() {
         JLabel imageLabel = new JLabel();
        // Load the image from a file
        ImageIcon imageIcon = new ImageIcon("C://Users/Hirut Tarekegn/Desktop/javaproject1/images (2).jfif");
        // Set the image icon to the JLabel
        imageLabel.setIcon(imageIcon);
        p.add(imageLabel);
        pan.add(viewPresc);
        pan.add(createItem);
//        label.setText(u);
//        label2.setText(t);
//        pan1.add(l);
//        pan1.add(label);
//        
//        pan1.add(l2);
//        pan1.add(label2);

        
//        pan.add(report);
        pan.add(logout);

        viewPresc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new View_Prescription();
                  setVisible(false);
            }
        });

        createItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateItem();
                setVisible(false);
            }
        });

        viewDoctor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to view doctors
            }
        });

        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Hospital_Management();
                dispose(); // Close the current Pharmacist frame
            }
        });

//        report.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                new SalesReport();
//            }
//        });
        setLayout(new BorderLayout());
        pan.setLayout(new GridLayout(4, 1, 5, 6));
        pan3.setLayout(new BoxLayout(pan3, BoxLayout.LINE_AXIS));
        pan3.add(pan);
        pan3.add(Box.createHorizontalGlue());
        pan3.add(pan1);
         add(p,BorderLayout.PAGE_START);
        add(pan3,BorderLayout.CENTER);
        

        setTitle("Pharmacist");
        setSize(500, 450);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
