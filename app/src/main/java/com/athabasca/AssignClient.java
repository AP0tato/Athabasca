package com.athabasca;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class AssignClient extends JFrame{
    AssignClient(){
        setLayout(new GridBagLayout());
        FormattedPanel panel = new FormattedPanel();
        GridBagUtlity constraints = new GridBagUtlity(0, 0);
        Dimension dimflds = new Dimension(100,20);

        JTextField idInput = new GeneralInput(20,dimflds);
        JTextField assignment = new GeneralInput(20,dimflds);

        JComponent[][] elements = {{new JLabel("Rep ID: "), idInput}, {new JLabel("Assignment: "), assignment}};
        panel.addElements(elements);
        add(panel, constraints);

        JButton assign = new JButton("Assign");
        constraints.nextY();
        add(assign, constraints);

        assign.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String id = idInput.getText();
                String assign = assignment.getText();
                System.out.println("Rep ID: " + id + " Assignment: " + assign);
            }
        });
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }
}
