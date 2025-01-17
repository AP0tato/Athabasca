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
        btnRefresh.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {Session.update(CheckAssignments.this::updateTable);}});
        add(new JLabel("Check your assignements"),gbc);
        gbc.nextY();
        add(btnRefresh,gbc);
        gbc.nextY();
        add(scr,gbc);
        
    }
    @Override
    public String toString() {
        return "Check Assignments";
    }
    private void updateTable(ArrayList<String> callback){
        model.setRowCount(0);
        for(int i = 0; i < Session.getAssigned().size(); i++){
            String clientEmail = Session.getAssigned().get(i);
            String[] toSort = new String[Clients.clients.size()];
            for(int j = 0; j < Clients.clients.size();j++){
                toSort[j] = Clients.clients.get(j).getEmail();
            }
            SearchHelper searcher = new SearchHelper();
            int[] found = searcher.originalIndicesBinary(toSort,clientEmail);
            for(int j :found){
                Client client = Clients.clients.get(j);
                String[] clientData = client.toString().split("\\|"); // Remove the backslashes and I will find you
                model.addRow(clientData);
            }

            
        }
    }
}
