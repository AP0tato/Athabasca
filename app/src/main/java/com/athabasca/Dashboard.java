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
        buttons = new JButton[toUse.length];
        for(int i = 0;i<toUse.length; i++){
            JButton btnNew = new JButton(toUse[i].toString());
            final int inner_i = i;
            btnNew.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    toUse[inner_i].setVisible(true);
                }
                
            });
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
