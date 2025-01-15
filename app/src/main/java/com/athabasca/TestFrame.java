package com.athabasca;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class TestFrame extends JFrame {
    private JLabel lbl;
    public TestFrame() {
        lbl = new JLabel("<html>");
        JScrollPane scrl = new JScrollPane(lbl);

        // Load clients and update the UI once data is retrieved
        Clients.loadClients(this::updateClients);

        setLayout(new BorderLayout());
        add(scrl, BorderLayout.CENTER);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ensure the frame is disposed of
        setVisible(true); // Ensure the frame is visible
    }

    private void updateClients(ArrayList<Client> clients) {
        SwingUtilities.invokeLater(() -> {
            StringBuilder sb = new StringBuilder("<html>");
            for (Client client : clients) {
                sb.append(client.toString()).append("<br>");
            }
            sb.append("</html>");
            lbl.setText(sb.toString());
            System.out.println("UI updated with clients: " + clients);
        });
    }
}
