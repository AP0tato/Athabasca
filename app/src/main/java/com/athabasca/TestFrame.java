package com.athabasca;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class TestFrame extends JFrame {
    private JLabel lbl;
    public TestFrame()
    {
        lbl = new JLabel("<html>");
        JScrollPane scrl = new JScrollPane(lbl);

        Clients.loadClients(this::updateClients);

        for(Client client : Clients.clients)
        {
            lbl.setText(lbl.getText()+client.toString()+"<br>");
        }

        add(scrl);
        setSize(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void updateClients(ArrayList<Client> clients) {
        SwingUtilities.invokeLater(() -> {
            StringBuilder sb = new StringBuilder("<html>");
            for (Client client : clients) {
                sb.append(client.toString()).append("<br>");
            }
            sb.append("</html>");
            lbl.setText(sb.toString());
        });
    }
}
