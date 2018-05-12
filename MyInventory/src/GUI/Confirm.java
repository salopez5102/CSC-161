package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Confirm extends JOptionPane {
public boolean keepmoving;

    public Confirm() {
        //JDialog.setDefaultLookAndFeelDecorated(true);                 // changes theme of panel when not blocked
        int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {                        // if user click no boolean is changed
            keepmoving = false;
        }
        if (response ==JOptionPane.YES_NO_OPTION){                      // if user click yes changes boolean
            keepmoving = true;

        }


    }
    public boolean getkeepmoving(){
        return keepmoving;
    }               //send back boolean
}

