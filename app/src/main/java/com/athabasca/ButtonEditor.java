package com.athabasca;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    @SuppressWarnings("unused")
    private boolean isPushed;
    private JTable table; // Reference to the table for context
    private DefaultTableModel model;

    public ButtonEditor(JCheckBox checkBox,DefaultTableModel model, JTable table) {
        super(checkBox);
        this.table = table;
        this.model = model;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped(); // Stop editing to finalize the click
                performAction();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    private void performAction() {
        // Get the row and column of the clicked button
        int row = table.getSelectedRow();
        int column = table.getSelectedColumn();

        // Perform a unique action based on the row or column
        String rowData = table.getValueAt(row, 5).toString();
        System.out.println("Button clicked in row " + row + ", column " + column + ": " + rowData);

        DatabaseUtil db = new DatabaseUtil();
        List<String> list = Session.getAssigned();
        list.remove(0);
        db.writeData("employee/"+Session.getEmail().replaceAll("\\.", "\\\\")+"/assigned", list, data -> {
            System.out.println("Data written? " + data);
            Session.update(e -> {
                for(byte i = 0; i < 6; i++) {
                    table.setValueAt("", row, i);
                }
            });
        });
       updateTable(Session.getAssigned());
    }
    private void updateTable(List<String> assignedClients) {
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
    }
}