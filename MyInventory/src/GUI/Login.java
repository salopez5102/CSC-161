package GUI;

import DataBase.RolesDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JDialog implements ActionListener {
    private JTextField UserName;
    private JPasswordField Password;
    private JButton cancel;
    private JButton login;
    private RolesDatabase info;

    public Login(RolesDatabase recievedInfo) {                                  //create login window
        info = recievedInfo;

        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel login = new JPanel();
        add(login);
        login.setBackground(Color.PINK);

        login.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        login.add(userLabel);

        UserName = new JTextField(20);
        UserName.setBounds(100, 10, 160, 25);
        login.add(UserName);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        login.add(passwordLabel);

        Password = new JPasswordField(20);
        Password.setBounds(100, 40, 160, 25);
        login.add(Password);

        this.login = new JButton("Login");
        this.login.setBounds(10, 80, 100, 25);
        this.login.addActionListener(this);
        this.login.setBackground(Color.WHITE);
        login.add(this.login);
        login.getRootPane().setDefaultButton(this.login);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 80, 100, 25);
        cancel.addActionListener(this);
        cancel.setBackground(Color.WHITE);
        login.add(cancel);

        setModal(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getUserNameField() { return UserName.getText().trim(); }         //makes it simplier to get info

    public String getPasswordField() {
        return Password.getPassword().toString().trim();
    }

    public void setUserNameField(String text) {
        UserName.setText(text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if (((JButton) e.getSource()).getText() == "Login") {
                System.out.println("i go in");
                if (info.check(getUserNameField())) {
                    MainWindow.singleton().setApplicationSecurityContext(getUserNameField(), getPasswordField());
                    System.out.println("i go out");
                    this.dispose();
                } else {
                    setUserNameField("INVALID USER");
                }
            } else {
                System.out.println("Cancel");
                System.exit(0);
            }
        }
    }
}
