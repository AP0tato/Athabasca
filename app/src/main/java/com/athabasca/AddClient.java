package com.athabasca;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class AddClient extends JFrame{
    AddClient(){
        setLayout(new GridBagLayout());
        FormattedPanel panel = new FormattedPanel();
        GridBagUtil constraints = new GridBagUtil(0, 0);
        Dimension dimflds = new Dimension(100,20);

        // DateSelector dateJoined = new DateSelector();
        // dateJoined.GUI();

        JTextField Fname = new TextInput(15, dimflds);
        JTextField Lname = new TextInput(15, dimflds);
        //JTextField dateJoined = new IntegerInput(15, dimflds);
        JTextField phone = new TextInput(15, dimflds);
        JTextField address = new GeneralInput(15, dimflds);
        JComponent[][] elements ={{},{},{},{},{}};
    }
}
