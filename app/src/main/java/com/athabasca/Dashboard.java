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
        JFrame[] toUse;
        JButton[] buttons;

        if(admin){
            toUse = new JFrame[3];
            toUse[0] = new ClientList();
            toUse[1] = new AssignClient();
            toUse[2] = new AddClient();
        }
        else {
            toUse = new JFrame[2];
            toUse[0] = new ClientList();
            toUse[1] = new CheckAssignments(); 
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
            buttons[i] = btnNew;
        }

        JComponent[][] elements = {buttons};
        pnl.addElements(elements);
        if(admin){
            add(new JLabel("Admin Dashboard"),gbc);
        } else {
            add(new JLabel("Rep Dashboard"),gbc);
        }
        gbc.nextY();
        add(pnl,gbc);
        System.out.println("Got to a point");
        pack();
        setVisible(true);
    }
}
