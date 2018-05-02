package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButton extends JFrame implements ActionListener {
    private JLabel uidLabel;
    private JTextField uid;
    private JLabel nameLabel;
    private JTextField name;
    private JLabel valueLabel;
    private JTextField value;
    private JTextField username;
    private JLabel dropdownLabel;
    private JComboBox kinds;
    private JLabel serialLabel;
    private JTextField serialNumber;
    private JButton back;
    private JButton Confirm;

    public DeleteButton(){
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
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(_leftside, _top, _labelwidth, _height);
        userLabel.setForeground(Color.WHITE);
        panel.add(userLabel);

        username = new JTextField(20);
        username.setEnabled(false);
        username.setBounds(_rightside, _top, _labelwidth * 2, _height);
        panel.add(username);

        dropdownLabel = new JLabel("Kinds:");
        dropdownLabel.setBounds(_leftside, _top += 30, _labelwidth, _height);
        dropdownLabel.setForeground(Color.WHITE);
        panel.add(dropdownLabel);

        kinds = new JComboBox<String>();
        kinds.setBounds(_rightside, _top, _labelwidth * 2, _height);
        kinds.addActionListener(this);
        panel.add(kinds);

        JLabel whichLabel = new JLabel("Which:");
        whichLabel.setBounds(_leftside, _top += 30, _labelwidth, _height);
        whichLabel.setForeground(Color.WHITE);
        panel.add(whichLabel);

        JComboBox which = new JComboBox<String>();
        which.setBounds(_rightside, _top, _labelwidth * 2, _height);
        which.addActionListener(this);
        panel.add(which);

        uidLabel = new JLabel("UID:");
        uidLabel.setBounds(_leftside, _top += 30, _labelwidth, _height);
        uidLabel.setForeground(Color.WHITE);
        panel.add(uidLabel);

        uid = new JTextField();
        uid.setEnabled(false);
        uid.setBounds(_rightside, _top, _labelwidth * 2, _height);
        panel.add(uid);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(_leftside, _top += 30, _labelwidth, _height);
        nameLabel.setForeground(Color.WHITE);
        panel.add(nameLabel);

        name = new JTextField(20);
        name.setBounds(_rightside, _top, _labelwidth * 2, _height);
        panel.add(name);

        valueLabel = new JLabel("Value:");
        valueLabel.setBounds(_leftside, _top += 30, _labelwidth, _height);
        valueLabel.setForeground(Color.WHITE);
        panel.add(valueLabel);

        value = new JTextField(20);
        value.setBounds(_rightside, _top, _labelwidth * 2, _height);
        panel.add(value);

        serialLabel = new JLabel("Serial:");
        serialLabel.setBounds(_leftside, _top += 30, _labelwidth, _height);
        serialLabel.setForeground(Color.WHITE);
        panel.add(serialLabel);

        serialNumber = new JTextField(20);
        serialNumber.setBounds(_rightside, _top, _labelwidth * 2, _height);
        panel.add(serialNumber);

        back = new JButton("back");
        back.setBounds(100,230,100,20);
        back.addActionListener(this);
        back.setBackground(Color.WHITE);
        panel.add(back);

        Confirm = new JButton("confirm");
        Confirm.setBounds(220,230,100,20);
        Confirm.addActionListener(this);
        Confirm.setBackground(Color.WHITE);
        panel.add(Confirm);

        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton && e.getSource().equals(back));{  //goes back to the mainwindow when back is clicked
            new MainWindow();
            this.dispose();
        }
    }
}
