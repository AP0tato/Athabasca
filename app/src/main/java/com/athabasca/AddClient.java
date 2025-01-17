package com.athabasca;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
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
        JTextField phone = new DoubleInput( new Long("10000000000"), dimflds,-1, false);
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

        JLabel title1 = new JLabel("Add Client by Entering Information Below");
        panel.add(title1, constraints);
        add(title1, constraints);
        // Create a 2D array of components to add to the panel
        JComponent[][] elements = {
            { new JLabel("First Name: "), Fname, },
            { new JLabel("Last Name: "), Lname },
            { new JLabel("Phone #: "), phone },
            { new JLabel("Address: "), address },
            { new JLabel("Date Joined: "), datePicker }, // Replace dateJoined text field with datePicker
            {  }
        };
        constraints.nextY();
        
        // Add elements to the panel
        panel.addElements(elements);
        add(panel, constraints);

        // Add a label for adding clients by CSV 
        JLabel byCSV = new JLabel("Or Add Clients By CSV ");
        constraints.nextY();
        add(byCSV, constraints);


        //Add 
        JButton submit = new JButton("Submit");
        // Add a button to open the file chooser
        JButton openFileChooserButton = new JButton("Open");
        // file selected display
        JTextField csvInput = new GeneralInput(25, dimflds);
        

        JComponent[][] elements2 = {{submit, openFileChooserButton, csvInput}};
        constraints.nextY();
        //panel.addElements(elements2);
        add(panel, constraints);


       

        // Add an action listener to the button to open the file chooser
        openFileChooserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Handle the selected file
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    // You can add code here to read and process the CSV file
                    csvInput.setText(selectedFile.toString());

                }
            }
        });


        // Pack the frame to fit the components
        pack();
    }

    @Override
    public String toString() {
        return "Add Client";
    }
}