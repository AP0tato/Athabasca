package com.athabasca;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;


public class Dashboard extends JFrame {
    Dashboard(Boolean admin){
        setLayout(new GridBagLayout());
        GridBagUtil gbc = new GridBagUtil(0, 0);
        FormattedPanel pnl = new FormattedPanel();
        JFrame[] adminWindows = {new ClientList(), new AssignClient()//, new AddClient()
        };
        JFrame[] toUse;
        JFrame[] repWindows = {new ClientList(), new CheckAssignments()};
        JButton[] buttons;

        if(admin){
            toUse = adminWindows;
        } else {
            toUse = repWindows;
        }

        for(int i = 0;i<toUse.length; i++){
            
        }

        JComponent[][] elements = {buttons};
        pnl.addElements(elements);
        add(new JLabel("Admin Dashboard"),gbc);
        gbc.nextY();
        add(pnl,gbc);
        pack();
        setVisible(true);
    }
}
