package com.athabasca;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.ArrayList;

public class ClientList extends JFrame {
    ClientList(){
        setLayout(new GridBagLayout());
        GridBagUtlity gbc = new GridBagUtlity(0, 0);
        FormattedPanel pnlActions = new FormattedPanel();
        DefaultTableModel model = new DefaultTableModel();
        JTable tblClients = new JTable(model);
        JScrollPane scr = new JScrollPane(tblClients);
        scr.setSize(new Dimension(500,500));
        ScalePanel pnlScale = new ScalePanel();
        JPanel pnlTitle = new JPanel();

        JComboBox<String> bxCategories = new JComboBox<String>(Client.Categories);
        GeneralInput fldSearch = new GeneralInput(20, new Dimension(100,20));

        JButton btnSearch = new JButton("Search");



        JComponent[][] elemsAction = {{new JLabel("Search: "),new JLabel("by:"),bxCategories, fldSearch,btnSearch}};
        
        pnlActions.addElements(elemsAction);

        model.setColumnIdentifiers(Client.Categories);
        
        updateTable(model, Clients.clients.toArray(new Client[Clients.clients.size()]));

        pnlTitle.add(new JLabel("Client List"));

        pnlScale.add(pnlTitle,gbc);
        gbc.nextY();
        pnlScale.add(pnlActions,gbc);
        gbc.nextY();
        pnlScale.add(scr, gbc);
        pnlScale.setScaleFactor(2);
        add(pnlScale);
        pack();
        setVisible(true);
    }
    private void updateTable(DefaultTableModel model, Client[] clients){
        model.setRowCount(0);
        for(Client client : clients){
            String[] clientData = client.toString().split(getName());
            model.addRow(clientData);
        }
    }
}
