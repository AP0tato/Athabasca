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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientList extends JFrame {
    private DefaultTableModel model;
    ClientList(){
        setLayout(new GridBagLayout());
        GridBagUtil gbc = new GridBagUtil(0, 0);
        FormattedPanel pnlActions = new FormattedPanel();
        model = new DefaultTableModel();
        JTable tblClients = new JTable(model);
        JScrollPane scr = new JScrollPane(tblClients);
        scr.setSize(new Dimension(500,500));
        JPanel pnl = new JPanel();
        pnl.setLayout(new GridBagLayout());

        JComboBox<String> bxCategories = new JComboBox<String>(Client.Categories);
        GeneralInput fldSearch = new GeneralInput(20, new Dimension(100,20));

        JButton btnSearch = new JButton("Search");

        JComponent[][] elemsAction = {{new JLabel("Search: "),new JLabel("by:"),bxCategories, fldSearch,btnSearch}};

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Client[] found;
                String[] toSort = new String[Clients.clients.size()];
               int category = bxCategories.getSelectedIndex();
               for(int i = 0; i < Clients.clients.size();i++){
                String[] categories = Clients.clients.get(i).toString().split("\\|"); // Remove the 2 backslashes and I will find you
                toSort[i] = categories[category];
               }
               SearchHelper searcher = new SearchHelper();
               int[] indices = searcher.originalIndicesBinary(toSort, fldSearch.getText().trim());
               found = new Client[indices.length];
               for(int i = 0; i < found.length;i++){
                found[i] = Clients.clients.get(indices[i]);
               }
               if(found.length == 0){
                fldSearch.setText("No client found");
                return;
               }
               else{
                updateTable(found);
               }

            }
            
        });
        
        pnlActions.addElements(elemsAction);

        model.setColumnIdentifiers(Client.Categories);

        Clients.loadClients(this::callback);

        add(new JLabel("Client List"),gbc);
        gbc.nextY();
        add(pnlActions,gbc);
        gbc.nextY();
        add(scr, gbc);
        //ScalePanel pnlScale = new ScalePanel(pnl,1.5);
        pack();
        setVisible(true);
    }

    private void updateTable(Client[] clients){
        model.setRowCount(0);
        for(Client client : clients){
            String[] clientData = client.toString().split("\\|"); // Remove the backslashes and I will find you
            model.addRow(clientData);
        }
    }
<<<<<<< Updated upstream

    private void callback(ArrayList<Client> clients) {
        model.setRowCount(0);
        for (Client client : clients) {
            model.addRow(new Object[]{
                client.getFirstName(),
                client.getLastName(),
                client.getPhoneNumber(),
                client.getAddress(),
                client.getDateJoined()
            });
        }
=======
    @Override
    public String toString() {
        return "Client List";
>>>>>>> Stashed changes
    }
}
