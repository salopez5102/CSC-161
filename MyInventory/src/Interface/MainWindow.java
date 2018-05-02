package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {

private JFrame Main;                     //name of text field
private JButton ExitButton;
private JButton View;
private JButton Add;
private JButton Delete;
private JButton Edit;
private JLabel user;

public MainWindow(){

    setTitle("Main Window");                                //name of jpanel
    setSize(100,260);
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    JPanel panel = new JPanel();
    add(panel);
    panel.setLayout(null);
    panel.setBackground(Color.DARK_GRAY);                   //set the background color

    user = new JLabel("User:");
    user.setBounds(10,0,225,20);
    user.setForeground(Color.WHITE);
    panel.add(user);


    View = new JButton("view");
    View.setBounds(10,30,225,20);
    View.addActionListener(this);
    View.setBackground(Color.WHITE);
    panel.add(View);

    Add = new JButton("add");
    Add.setBounds(10,70,225,20);
    Add.addActionListener(this);
    Add.setBackground(Color.WHITE);
    panel.add(Add);

    Delete = new JButton("delete");
    Delete.setBounds(10,110,225,20);
    Delete.addActionListener(this);
    Delete.setBackground(Color.WHITE);
    panel.add(Delete);

    Edit = new JButton("edit");
    Edit.setBounds(10,150,225,20);
    Edit.addActionListener(this);
    Edit.setBackground(Color.WHITE);
    panel.add(Edit);

    ExitButton = new JButton("exit");
    ExitButton.setBounds(10,190,225,20);
    ExitButton.addActionListener(this);
    ExitButton.setBackground(Color.WHITE);
    panel.add(ExitButton);

    setLocationRelativeTo(null);
    setVisible(true);
}

public void actionPerformed(ActionEvent e){
    System.out.println("action performed");
    if(e.getSource() instanceof JButton && e.getSource().equals(ExitButton)){
        System.out.println("Exit button hit");
        System.exit(0);                                             //closes Mainwindow and ends proggram
    }
    if(e.getSource() instanceof JButton && e.getSource().equals(Add)){
        new Addbutton();
        this.dispose();                                                     //closes Mainwindow
    }
    if(e.getSource() instanceof JButton && e.getSource().equals(View)){
        new ViewButton();
        this.dispose();
    }
    if(e.getSource() instanceof JButton && e.getSource().equals(Delete)){
        new DeleteButton();
        this.dispose();
    }
    if(e.getSource() instanceof JButton && e.getSource().equals(Edit)){
        new EditButton();
        this.dispose();
    }
    }
}
