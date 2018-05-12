package GUI;

import DataBase.InventoryDatabase;
import DataBase.RolesDatabase;
import Items.SecurityContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {

private JButton ExitButton;
private JButton View;
private JButton Add;
private JButton Delete;
private JButton Edit;
private JLabel user;
private JLabel username;

public InventoryDatabase IDB = new InventoryDatabase();
public RolesDatabase RDB = new RolesDatabase();

public static MainWindow singleton = null;
private SecurityContext applicationSecurityContext = new SecurityContext();

public MainWindow(){

    setTitle("Main Window");                                //name of jpanel
    setSize(100,300);
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    JPanel panel = new JPanel();
    add(panel);
    panel.setLayout(null);


    user = new JLabel("User:");
    user.setBounds(10,0,100,20);
    user.setForeground(Color.BLACK);                        //sets foreground color
    panel.add(user);

    username = new JLabel();
    username.setBounds(50,0,100,20);
    username.setForeground(Color.BLACK);
    panel.add(username);

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
    ExitButton.setBackground(Color.WHITE);                                                              //changes background color for button
    panel.add(ExitButton);

    setLocationRelativeTo(null);
    setVisible(true);
}
    public void setUsername(String wow){
        if(username.getText().equals("")){
            username.setText(wow);
        }
    }
    public void setApplicationSecurityContext(String name, String token) {
        applicationSecurityContext.setSecurityContext(name, token);
        username.setText(name);
    }
    public void applyOperationalPermissions() {
        Add.setEnabled(RDB.getUserRole() != null && RDB.getUserRole().canAdd());
        Edit.setEnabled(RDB.getUserRole() != null && RDB.getUserRole().canEdit());
        Delete.setEnabled(RDB.getUserRole() != null && RDB.getUserRole().canDelete());
    }
   /* public void applyOperationalPermissions(String name) {
        username.setText(name);
        Add.setEnabled(RDB.getUserRole() != null && RDB.getUserRole().canAdd());
        Edit.setEnabled(RDB.getUserRole() != null && RDB.getUserRole().canEdit());
        Delete.setEnabled(RDB.getUserRole() != null && RDB.getUserRole().canDelete());
    }*/


public static MainWindow singleton(){
    if(singleton == null){
        singleton = new MainWindow();
    }
      return singleton;
}
    public InventoryDatabase getIDB(){return IDB;}
    public RolesDatabase getRDB(){return RDB;}


public void actionPerformed(ActionEvent e){
    if(e.getSource() instanceof JButton && e.getSource().equals(ExitButton)){
        System.exit(0);                                                     //closes Mainwindow and ends proggram
    }
    if(e.getSource() instanceof JButton && e.getSource().equals(Add)){
        Addbutton.singleton().loadKinds(username.getText());                      // creates new window with items loaded in and with the username displayed so for the rest
        this.dispose();
    }
    if(e.getSource() instanceof JButton && e.getSource().equals(View)){
        ViewButton.singleton().loadKinds(username.getText());
        this.dispose();
    }
    if(e.getSource() instanceof JButton && e.getSource().equals(Delete)){
        DeleteButton.singleton().loadKinds(username.getText());
        this.dispose();
    }
    if(e.getSource() instanceof JButton && e.getSource().equals(Edit)){
        EditButton.singleton().loadKinds(username.getText());
        this.dispose();
    }
    }
}
