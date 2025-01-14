package com.athabasca;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AssignClient extends JFrame{
    FormattedPanel panel = new FormattedPanel();
    GridBagUtlity constraints = new GridBagUtlity(0, 0);

    JTextField idInput = new JTextField();
    JTextField assignment = new JTextField();

    JComponent[][] elements = {{new JLabel("Rep ID: "), idInput}, {new JLabel("Assignment: "), assignment}};
    JButton assign = new JButton("Assign");

    AssignClient(){
        panel.addElements(elements);
        add(panel);
        setVisible(true);
    }
    

    
}
