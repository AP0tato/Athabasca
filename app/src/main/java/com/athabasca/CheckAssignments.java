package com.athabasca;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;


public class CheckAssignments extends JFrame {
    DefaultTableModel model;
    JTable tblClients;
    @SuppressWarnings("Convert2Lambda")
    CheckAssignments(){
        setLayout(new GridBagLayout());
        GridBagUtil gbc = new GridBagUtil(0, 0);
        JButton btnRefresh = new JButton("Refresh");
        model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 6){
                    return true;
                } else {
                    return false;
                }
            }
        };
        tblClients = new JTable(model) {
            @Override
            public java.awt.Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                java.awt.Component c = super.prepareRenderer(renderer, row, column);
                if (c instanceof javax.swing.JComponent) {
                    javax.swing.JComponent jc = (javax.swing.JComponent) c;
                    @SuppressWarnings("unused")
                    Object value = getValueAt(row, column);
                    jc.setToolTipText("Completed");
                }
                return c;
            }
        };
        tblClients.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        tblClients.addMouseMotionListener(new MouseMotionAdapter() {           
            @SuppressWarnings("unused")
            public void mouseMoved(MouseEvent e) {
                int row = tblClients.rowAtPoint(e.getPoint());
                int column = tblClients.columnAtPoint(e.getPoint());
            }
        });
        
        JScrollPane scr = new JScrollPane(tblClients);
        scr.setSize(new Dimension(500,500));
        String[] columnIdentifiers = new String[Client.Categories.length + 1];
        System.arraycopy(Client.Categories, 0, columnIdentifiers, 0, Client.Categories.length);
        columnIdentifiers[Client.Categories.length] = "completed";
        model.setColumnIdentifiers(columnIdentifiers);
        Session.update(this::updateTable);
        tblClients.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        tblClients.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox(),model, tblClients));
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
                if (client.getEmail().equals(clientEmail.replaceAll("\\\\", "\\."))) {
                    model.addRow(new Object[]{
                        client.getFirstName(),
                        client.getLastName(),
                        client.getPhoneNumber(),
                        client.getAddress(),
                        client.getDateJoined(),
                        client.getEmail().replaceAll("\\\\", "\\."),
                        new JButton("Complete")
                    });
                }
            }
        }
        for(int i = 0; i < model.getColumnCount(); i++){
            tblClients.getColumnModel().getColumn(i).setPreferredWidth(100);
        }
    }
}
