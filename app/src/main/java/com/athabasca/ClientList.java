package com.athabasca;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class ClientList extends JFrame {
    private DefaultTableModel model;
    ClientList(){
        setTitle("Client List");
        setLayout(new GridBagLayout());
        GridBagUtil gbc = new GridBagUtil(0, 0);
        FormattedPanel pnlActions = new FormattedPanel();
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tblClients = new JTable(model) {
            @Override
            public java.awt.Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                java.awt.Component c = super.prepareRenderer(renderer, row, column);
                if (c instanceof javax.swing.JComponent) {
                    javax.swing.JComponent jc = (javax.swing.JComponent) c;
                    Object value = getValueAt(row, column);
                    jc.setToolTipText(value == null ? "" : value.toString());
                }
                return c;
            }
        };
        JScrollPane scr = new JScrollPane(tblClients);
        scr.setSize(new Dimension(500,400));
        JPanel pnl = new JPanel();
        pnl.setLayout(new GridBagLayout());
        tblClients.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        tblClients.addMouseMotionListener(new MouseMotionAdapter() {
            
            @SuppressWarnings("unused")
            public void mouseMoved(MouseEvent e) {
                int row = tblClients.rowAtPoint(e.getPoint());
                int column = tblClients.columnAtPoint(e.getPoint());
            }
        });
           

        JComboBox<String> bxCategories = new JComboBox<String>(Client.Categories);
        GeneralInput fldSearch = new GeneralInput(200, new Dimension(100,20));
        JButton btnRefresh = new JButton("Clear Search/Refresh");

        JButton btnSearch = new JButton("Search");

        JComponent[][] elemsAction = {{new JLabel("Search: "),new JLabel("by:"),bxCategories, fldSearch,btnSearch}};
        
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Client[] found;
                String[] toSort = new String[Clients.clients.size()];
               int category = bxCategories.getSelectedIndex();
               for(int i = 0; i < Clients.clients.size();i++){
                String[] categories = Clients.clients.get(i).toString().split("\\|"); // Remove the 2 backslashes and I will find you <--- "Alright, I wont" - Seysha Puttagunta 
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

        btnRefresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable(Clients.clients.toArray(new Client[Clients.clients.size()]));
            }
            
        });

        pnlActions.setBorder(new EmptyBorder(0, 25, 0, 25));

        add(new JLabel("Client List"){{setBorder(new EmptyBorder(25, 25, 0, 25));}},gbc);
        gbc.nextY();
        add(pnlActions,gbc);
        gbc.nextY();
        add(btnRefresh,gbc);
        gbc.nextY();
        add(scr, gbc);
        //ScalePanel pnlScale = new ScalePanel(pnl,1.5);
        pack();
    }

    private void updateTable(Client[] clients){
        model.setRowCount(0);
        for(Client client : clients){
            String[] clientData = client.toString().split("\\|"); // Remove the backslashes and I will find you
            model.addRow(clientData);
        }
    }

    private void callback(ArrayList<Client> clients) {
        model.setRowCount(0);
        for (Client client : clients) {
            model.addRow( client.toString().split("\\|")
            );
        }}
    @Override
    public String toString() {
        return "Client List";
    }
}
