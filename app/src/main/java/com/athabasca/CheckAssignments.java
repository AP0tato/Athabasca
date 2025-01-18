package com.athabasca;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class CheckAssignments extends JFrame {
    DefaultTableModel model;
    @SuppressWarnings("Convert2Lambda")
    CheckAssignments(){
        setLayout(new GridBagLayout());
        GridBagUtil gbc = new GridBagUtil(0, 0);
        JButton btnRefresh = new JButton("Refresh");
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable tblClients = new JTable(model);
        JScrollPane scr = new JScrollPane(tblClients);
        scr.setSize(new Dimension(500,500));
        model.setColumnIdentifiers(Client.Categories);
        Session.update(this::updateTable);
        btnRefresh.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {Session.update(CheckAssignments.this::updateTable);}});
        add(new JLabel("Check your assignements"),gbc);
        gbc.nextY();
        add(btnRefresh,gbc);
        gbc.nextY();
        add(scr,gbc);
        pack();
        
    }
    @Override
    public String toString() {
        return "Check Assignments";
    }
    private void updateTable(ArrayList<String> assignedClients) {
        model.setRowCount(0);
        System.out.println("Updating Table");
        System.out.println("Assigned Clients: " + assignedClients);
        for (String clientEmail : assignedClients) {
            for (Client client : Clients.clients) {
                if (client.getEmail().equals(clientEmail)) {
                    model.addRow(new Object[]{
                        client.getFirstName(),
                        client.getLastName(),
                        client.getPhoneNumber(),
                        client.getAddress(),
                        client.getDateJoined(),
                        client.getEmail()
                    });
                }
            }
        }
    }
}
