package com.athabasca;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isPushed;
    private JTable table; // Reference to the table for context

    public ButtonEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table;
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
        String rowData = table.getValueAt(row, 0).toString();
        System.out.println("Button clicked in row " + row + ", column " + column + ": " + rowData);

        // Example: Show a dialog with the row data
        JOptionPane.showMessageDialog(button, "Button clicked for " + rowData);
    }
}