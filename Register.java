package hospital_management;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Register extends JFrame{
     JPanel pan=new JPanel();
     JLabel lab=new JLabel("PATIENT REGISTER FORM");
     JLabel lab1=new JLabel("FIRST NAME");
     JTextField tf=new JTextField();
     JLabel lab2=new JLabel("LAST NAME");
     JTextField tf1=new JTextField();
     JLabel lab3=new JLabel("ID");
     JTextField tf2=new JTextField();
     JLabel lab4=new JLabel("SEX");
     JTextField tf3=new JTextField();
     JLabel lab5=new JLabel("AGE");
     JTextField tf4=new JTextField();
    public  Register(){
     pan.add(lab);
     pan.add(tf);
     
     pan.add(lab1);
     pan.add(tf1);

     pan.add(lab2);
     pan.add(tf2);

     pan.add(lab3);
     pan.add(tf3);

     pan.add(lab4);
     pan.add(tf4);
     pan. setLayout(new GridLayout(5,2,5,6));
     add(pan);
    
        System.out.println("selam");
       
        setTitle("HOSPITAL MANEGEMENT SYSTEM");
        setSize(300, 200);
        setVisible(true);
        setLocationRelativeTo(null);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}


    
    
    
}

