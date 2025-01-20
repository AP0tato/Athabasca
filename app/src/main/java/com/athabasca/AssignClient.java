package com.athabasca;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AssignClient extends JFrame {
    // Constructor for the AssignClient window
    AssignClient() {
        // Set the title of the window
        setTitle("Client Assignment");
        
        // Set the layout manager
        setLayout(new GridBagLayout());
        
        // Create a formatted panel and grid constraints
        FormattedPanel panel = new FormattedPanel();
        GridBagUtil constraints = new GridBagUtil(0, 0);
        Dimension dimflds = new Dimension(100, 20);

        // Create input fields for Rep Email and Assignment Email
        JTextField idInput = new GeneralInput(Integer.MAX_VALUE, dimflds);
        JTextField assignment = new GeneralInput(Integer.MAX_VALUE, dimflds);

        // Add labels and input fields to the panel
        JComponent[][] elements = {
            {new JLabel("Rep Email: "), idInput},
            {new JLabel("Assignment Email: "), assignment}
        };
        panel.addElements(elements);
        panel.setBorder(new EmptyBorder(25, 25, 0, 25)); // Add padding around the panel
        add(panel, constraints); // Add the panel to the frame

        // Create the "Assign" button
        JButton assign = new JButton("Assign");
        constraints.nextY(); // Move to the next row in the grid
        add(assign, constraints); // Add the button to the frame

        // Add an action listener to the "Assign" button
        assign.addActionListener(new ActionListener() {
            @SuppressWarnings("unchecked")
            public void actionPerformed(ActionEvent e) {
                // Get the text inputs and escape periods with double backslashes
                String id = idInput.getText().replaceAll("\\.", "\\\\");
                String assign = assignment.getText().replaceAll("\\.", "\\\\");

                DatabaseUtil db = new DatabaseUtil(); // Create a database utility instance

                // Validate the email inputs using a regular expression
                if (!(Pattern.matches("^[\\w\\\\%+-]+@[\\w\\\\-]+\\\\[a-zA-Z]{2,6}$", id) &&
                      Pattern.matches("^[\\w\\\\%+-]+@[\\w\\\\-]+\\\\[a-zA-Z]{2,6}$", assign))) {
                    JOptionPane.showMessageDialog(null, "Invalid inputs", "Assignment Failed", JOptionPane.ERROR_MESSAGE);
                    return; // Exit if validation fails
                }

                // Read data from the "employee" database
                db.readData("employee", a -> {
                    try {
                        Map<String, Map<String, Object>> loadedData = (Map<String, Map<String, Object>>) a;
                        for (Map.Entry<String, Map<String, Object>> entry : loadedData.entrySet()) {
                            if (entry.getKey().equals(id)) { // Check if the Rep Email exists
                                for (Client c : Clients.clients) {
                                    if (c.getEmail().equals(assign)) { // Check if the Assignment Email exists
                                        // Read employee data for the given ID
                                        db.readEmployee(id, data -> {
                                            try {
                                                // Update the assigned clients list
                                                ArrayList<String> f = data != null ? ((ArrayList<String>) data) : new ArrayList<>();
                                                f.add(assign);
                                                db.writeData("employee/" + id + "/assigned", f, data2 -> {
                                                    System.out.println("Data written? " + data2); // Log success or failure
                                                });
                                            } catch (Exception b) {
                                                System.out.println(b.getMessage() + "\n" + b.getStackTrace()); // Log exceptions
                                            }
                                        });
                                    }
                                }
                            }
                        }
                    } catch (Exception b) {
                        System.err.println(b.getMessage()); // Log errors during processing
                    }
                });

                // Notify the user of successful assignment
                JOptionPane.showMessageDialog(null, "Assigned Client!");
                
                // Hide the window and reset input fields
                setVisible(false);
                idInput.setText("");
                assignment.setText("");
            }
        });

        // Adjust the size of the frame to fit components
        pack();
    }

    @Override
    public String toString() {
        // Override the toString method to return a descriptive string
        return "Assign Client";
    }
}
