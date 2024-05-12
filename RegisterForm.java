package hospital_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 *
 * @author Hirut Tarekegn
 */
public class RegisterForm extends Frame{
    private JPanel pan=new JPanel();
   private  JLabel lab=new JLabel("PATIENT REGISTER FORM");
    private JLabel lab1=new JLabel("FIRST NAME");
    private JTextField tf=new JTextField();
    private JLabel lab2=new JLabel("LAST NAME");
    private JTextField tf1=new JTextField();
    private JLabel lab3=new JLabel("ID");
    private JTextField tf2=new JTextField();
   private  JLabel lab4=new JLabel("SEX");
    private JTextField tf3=new JTextField();
   private  JLabel lab5=new JLabel("AGE");
    private JTextField tf4=new JTextField();
     
    public  RegisterForm(){
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
     add(pan);

        
}
  public static void main(String[] args) {
        System.out.println("selam");
        RegisterForm rg=new RegisterForm();
        rg.setTitle("HOSPITAL MANEGEMENT SYSTEM");
        rg.setSize(400, 500);
        rg.setVisible(true);
        rg.setLocationRelativeTo(null);
       
    }
    
    
}
