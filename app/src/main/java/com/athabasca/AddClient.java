package com.athabasca;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.Properties;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class AddClient extends JFrame {
    AddClient() {
        // Set the layout for the frame
        setLayout(new GridBagLayout());
        
        // Create a formatted panel and set constraints
        FormattedPanel panel = new FormattedPanel();
        GridBagUtil constraints = new GridBagUtil(0, 0);
        Dimension dimflds = new Dimension(100, 20);

        // Create text fields for client information
        JTextField Fname = new TextInput(15, dimflds);
        JTextField Lname = new TextInput(15, dimflds);
        JTextField phone = new TextInput(15, dimflds);
        JTextField address = new GeneralInput(15, dimflds);
        
        // Create the date picker model and properties
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        
        // Create the date panel and date picker
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        // Create a 2D array of components to add to the panel
        JComponent[][] elements = {
            { new JLabel("First Name: "), Fname },
            { new JLabel("Last Name: "), Lname },
            { new JLabel("Phone #: "), phone },
            { new JLabel("Address: "), address },
            { new JLabel("Date Joined: "), datePicker } // Replace dateJoined text field with datePicker
        };
        
        // Add elements to the panel
        panel.addElements(elements);
        add(panel, constraints);

        // Pack the frame to fit the components
        pack();
        
    }

    public static void main(String[] args) {
        // Create and display the AddClient frame
        new AddClient().setVisible(true);
    }
    @Override
    public String toString() {
        return "Add Client";
    }
}