package com.athabasca;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class TestFrame extends JFrame {
    public TestFrame()
    {
        JLabel lbl = new JLabel("<html>");
        JButton btn = new JButton();
        JScrollPane scrl = new JScrollPane(lbl);

        Clients.loadClients();

        for(Client client : Clients.clients)
        {
            lbl.setText(lbl.getText()+client.toString()+"<br>");
        }

        add(btn);
        add(scrl);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
