package com.athabasca;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
public class AssignClient extends JFrame{
    AssignClient(){
        setTitle("Client Assignement");
        setLayout(new GridBagLayout());
        FormattedPanel panel = new FormattedPanel();
        GridBagUtil constraints = new GridBagUtil(0, 0);
        Dimension dimflds = new Dimension(100,20);

        JTextField idInput = new GeneralInput(20,dimflds);
        JTextField assignment = new GeneralInput(20,dimflds);

        JComponent[][] elements = {{new JLabel("Rep: "), idInput}, {new JLabel("Assignment: "), assignment}};
        panel.addElements(elements);
        panel.setBorder(new EmptyBorder(25, 25, 0, 25));
        add(panel, constraints);

        JButton assign = new JButton("Assign");
        constraints.nextY();
        add(assign, constraints);

        assign.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                HashMap <String,String> assignementSender = new HashMap<String,String>();
                String id = idInput.getText();
                String assign = assignment.getText();
                assignementSender.put("Rep", id);
                assignementSender.put("Client", assign);
            }
        });
        pack();
    }
    @Override
    public String toString() {
        return "Assign Client";
    }
}
