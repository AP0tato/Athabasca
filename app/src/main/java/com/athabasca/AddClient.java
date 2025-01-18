package com.athabasca;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class AddClient extends JFrame {
    private File selectedFile = null; // Class-level variable to store the selected file
    AddClient() {
        setTitle("Add Client");
        // Set the layout for the frame
        setLayout(new GridBagLayout());
        
        // Create a formatted panel and set constraints
        FormattedPanel pnl = new FormattedPanel();
        GridBagUtil constraints = new GridBagUtil(0, 0);
        Dimension dimflds = new Dimension(100, 20);

        // Create text fields for client information
        JTextField Fname = new TextInput(15, dimflds);
        JTextField Lname = new TextInput(15, dimflds);
        JTextField phone = new TextInput(15, dimflds);
        JTextField address = new GeneralInput(15, dimflds);
        JTextField email = new TextInput(35, dimflds);
        
        // Create the date picker model and properties
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        
        // Create the date panel and date picker
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        JButton submit = new JButton("Submit");
        // Create a 2D array of components to add to the panel
        JComponent[][] elements = {
            { new JLabel("First Name: "), Fname },
            { new JLabel("Last Name: "), Lname },
            { new JLabel("Phone #: "), phone },
            { new JLabel("Address: "), address },
            {new JLabel("Email: "), email},
            { new JLabel("Date Joined: "), datePicker },// Replace dateJoined text field with datePicker
            {submit}
        };
        
        
        // Add elements to the panel
        pnl.addElements(elements);
        add(pnl, constraints);

        // Increment the grid position for the next set of components
        constraints.nextY();
        FormattedPanel pnl2 = new FormattedPanel();
        // Add a label for adding clients by CSV 
        JLabel byCSV = new JLabel("Or Add Clients By CSV ");
        constraints.nextY();
        System.out.println("byCSVLabel y: "+constraints.gridy);
        add(byCSV, constraints);

        // Add a button to open the file chooser
        JButton openFileChooserButton = new JButton("Open");
        // Add a submit button beside the open button
        JButton submit2 = new JButton("Submit");        
        // File selected display beside the buttons
        JTextField csvInput = new GeneralInput(25, dimflds);
        csvInput.setEditable(false);


        JLabel status = new JLabel();

        JComponent[][] elements2 ={
            {openFileChooserButton, submit, csvInput},
            {status}
        };
        pnl2.addElements(elements2);
        constraints.nextY();
        System.out.println("ByCSV y: "+constraints.gridy);

        add(pnl2, constraints);

        // Add an action listener to the button to open the file chooser
        openFileChooserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                // Add a file filter to only allow CSV files
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Handle the selected file
                    selectedFile = fileChooser.getSelectedFile();
                    if (selectedFile.getName().toLowerCase().endsWith(".csv")) {
                        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                        csvInput.setText(selectedFile.toString());
                    } else {
                        System.out.println("Selected file is not a CSV file");
                        csvInput.setText("");
                    }
                }
            }
        });

        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if( Fname.getText().equals(null)||
                    Lname.getText().equals(null)||
                    phone.getText().equals(null)||
                    address.getText().equals(null)||
                    email.getText().equals(null)
                        
                ){
                    status.setText("Please fill all fields");
                    
                }else if(Pattern.matches("^[\\d\\w]+@(\\w\\.\\w)+$", email.getText())){
                    long phoneNum = Long.parseLong(phone.getText());
                    Client newClient = new Client(Fname.getText(), Lname.getText(), phoneNum, address.getText(), datePicker.getJFormattedTextField().getText(), email.getText());
                    
                }
            }
        });


        submit2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnSelectedFile();

            }
        });

        // Pack the frame to fit the components
        pack();
    }
    private String returnSelectedFile() {
        return selectedFile.toString();
    }

    @Override
    public String toString() {
        return "Add Client";
    }
}