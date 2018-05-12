package GUI;

import DataBase.InventoryDatabase;
import DataBase.InventorySystem;
import DataBase.RolesDatabase;
import DataBase.SerialNumber;
import Items.kindsList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewButton extends JFrame implements ActionListener {
    public String user ="";
    public static ViewButton singleton;

    private JLabel uidLabel;
    private JTextField uid;
    private JLabel nameLabel;
    private JTextField name;
    private JLabel valueLabel;
    private JTextField value;
    private JLabel username;
    private JLabel dropdownLabel;
    private JComboBox<String> kinds;
    private JComboBox<String> which;
    private JLabel serialLabel;
    private JTextField serialNumber;
    private JButton goback;
    private JButton Confirm;

    public InventoryDatabase IDB = new InventoryDatabase();
    public RolesDatabase RDB = new RolesDatabase();

    private ArrayList<InventorySystem> whichContext = new ArrayList<InventorySystem>();
    private ArrayList<kindsList> kindListOptions = new ArrayList<kindsList>();


     public ViewButton(){
         //loadKinds();
         int _leftside = 10;
         int _rightside = 100;
         int _top = 10;
         int _labelwidth = 110;
         int _height = 25;

         setTitle("View");
         setSize(400,300);
         setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

         JPanel panel = new JPanel();
         add(panel);
         //panel.setBackground(Color.white);
         panel.setLayout(null);

         JLabel userLabel = new JLabel("User:");
         userLabel.setBounds(_leftside, _top, _labelwidth, _height);
         userLabel.setForeground(Color.BLACK);
         panel.add(userLabel);

         username = new JLabel();
         username.setBounds(_rightside, _top, _labelwidth * 2, _height);
         panel.add(username);

         dropdownLabel = new JLabel("Kinds:");
         dropdownLabel.setBounds(_leftside, _top += 30, _labelwidth, _height);
         dropdownLabel.setForeground(Color.black);
         panel.add(dropdownLabel);

         kinds = new JComboBox<String>();
         kinds.setBounds(_rightside, _top, _labelwidth * 2, _height);
         kinds.addActionListener(this);
         panel.add(kinds);

         JLabel whichLabel = new JLabel("Which:");
         whichLabel.setBounds(_leftside, _top += 30, _labelwidth, _height);
         whichLabel.setForeground(Color.BLACK);
         panel.add(whichLabel);

         which = new JComboBox<String>();
         which.setBounds(_rightside, _top, _labelwidth * 2, _height);
         which.addActionListener(this);
         panel.add(which);

         uidLabel = new JLabel("UID:");
         uidLabel.setBounds(_leftside, _top += 30, _labelwidth, _height);
         uidLabel.setForeground(Color.BLACK);
         panel.add(uidLabel);

         uid = new JTextField();
         uid.setEnabled(false);
         uid.setBounds(_rightside, _top, _labelwidth * 2, _height);
         panel.add(uid);

         nameLabel = new JLabel("Name:");
         nameLabel.setBounds(_leftside, _top += 30, _labelwidth, _height);
         nameLabel.setForeground(Color.BLACK);
         panel.add(nameLabel);

         name = new JTextField(20);
         name.setBounds(_rightside, _top, _labelwidth * 2, _height);
         panel.add(name);

         valueLabel = new JLabel("Value:");
         valueLabel.setBounds(_leftside, _top += 30, _labelwidth, _height);
         valueLabel.setForeground(Color.BLACK);
         panel.add(valueLabel);

         value = new JTextField(20);
         value.setBounds(_rightside, _top, _labelwidth * 2, _height);
         panel.add(value);

         serialLabel = new JLabel("Serial:");
         serialLabel.setBounds(_leftside, _top += 30, _labelwidth, _height);
         serialLabel.setForeground(Color.BLACK);
         panel.add(serialLabel);

         serialNumber = new JTextField(20);
         serialNumber.setBounds(_rightside, _top, _labelwidth * 2, _height);
         panel.add(serialNumber);

         goback = new JButton("return");
         goback.setBounds(220,230,100,20);
         goback.addActionListener(this);
         goback.setBackground(Color.WHITE);
         panel.add(goback);

         /*Confirm = new JButton("confirm");
         Confirm.setBounds(220,230,100,20);
         Confirm.addActionListener(this);
         Confirm.setBackground(Color.WHITE);
         panel.add(Confirm);*/

         setLocationRelativeTo(null);
         setVisible(true);
     }
    public static ViewButton singleton(){
        if(singleton == null){
            singleton = new ViewButton();
        }
        return singleton;
    }

public void setUser(String name){user = name; username.setText(user);}
public String getUser(){return user;}
public InventoryDatabase getIDB(){return IDB;}

    public void loadKinds(String name) {
        try {
            for (String akind : IDB.getInventoryKinds()) {
                kindsList item = new kindsList();
                item.description = akind.substring(akind.lastIndexOf(".") + 1).trim();
                item.kind = akind;
                kindListOptions.add(item);

                kinds.addItem(item.description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUser(name);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton && e.getSource().equals(goback)){
            new MainWindow().setUsername(user);
            MainWindow.singleton().setApplicationSecurityContext(user,user);
            System.out.println(IDB.getmaxuid());
            this.dispose();
        }
        if (e.getSource() instanceof JComboBox && e.getSource().equals(kinds)) {
            try {
                which.removeAllItems();
                whichContext.removeAll(whichContext);
                for (InventorySystem row : ViewButton.singleton().getIDB().getAllInventoryOfKind(kindListOptions.get(kinds.getSelectedIndex()).kind)) {
                    whichContext.add(row);
                    which.addItem(row.getName());
                }
                for(int i=0;i<whichContext.size();i++){
                    which.addItem(whichContext.get(i).getName());
                }
            } catch (Exception catchall) {
                catchall.printStackTrace();
            }
        }
        if (e.getSource() instanceof JComboBox && e.getSource().equals(which)) {
            try {
                if (((JComboBox) e.getSource()).getSelectedIndex() >= 0) {
                    InventorySystem row = whichContext.get(which.getSelectedIndex());
                    uid.setText(String.valueOf(row.getInventoryNumber()));
                    name.setText(row.getName());
                    value.setText(String.valueOf(row.getWorth()));
                    if (row instanceof SerialNumber) {
                        serialLabel.setVisible(true);
                        serialNumber.setVisible(true);
                        serialNumber.setText(((SerialNumber) row).getSerialNumber());
                    } else {
                        serialLabel.setVisible(false);
                        serialNumber.setVisible(false);
                        serialNumber.setText("");
                    }
                }
            } catch (Exception catchAll2) {
                catchAll2.printStackTrace();
            }
        }


    }
}
